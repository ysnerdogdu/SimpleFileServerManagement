package thundra.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thundra.model.BaseComponent;
import thundra.model.ComponentType;
import thundra.model.File;
import thundra.model.Folder;
import thundra.model.dto.FileDto;
import thundra.model.dto.FolderDto;
import thundra.repository.IFileRepository;
import thundra.repository.IFolderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class to handle requests about folder components
 */
@Service
@Slf4j
public class FolderService implements IFolderService {

    private final IFolderRepository folderRepository;

    private final IFileRepository fileRepository;

    @Autowired
    public FolderService(IFolderRepository folderRepository,
                         IFileRepository fileRepository){
        this.folderRepository = folderRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    public FolderDto getFolderById(Long folderId) {

        Optional<Folder> folderOptional = folderRepository.findById(folderId);

        if (folderOptional.isEmpty()) {
            log.error("Folder is not found with given id={}", folderId);
            return null;
        }

        return new FolderDto(folderOptional.get());
    }

    @Override
    public FolderDto getRootComponents() {
        List<Folder> rootFolderList = folderRepository.findALlByParentFolder(null);
        List<File> rootFileList = fileRepository.findALlByParentFolder(null);

        List<FolderDto> folderDtoList = rootFolderList.stream()
                .map(FolderDto::new)
                .collect(Collectors.toList());

        List<FileDto> fileDtoList = rootFileList.stream()
                .map(FileDto::new)
                .collect(Collectors.toList());

        FolderDto folderDto = new FolderDto();
        folderDto.setFolders(folderDtoList);
        folderDto.setFiles(fileDtoList);

        return folderDto;
    }


    @Override
    public FolderDto createFolder(String name, Long parentFolderId) {

        Optional<Folder> folderOptional = folderRepository.findById(parentFolderId);

        if (folderOptional.isPresent()) {
            Folder newFolder = new Folder(name, LocalDateTime.now(), folderOptional.get());

            Folder createdFolder = folderRepository.save(newFolder);
            return new FolderDto(createdFolder);

        } else {
            log.info("Folder creation is failed. Parent folder is not found by Id={} ", parentFolderId);
        }

        return null;
    }

    @Override
    public FolderDto updateFolder(Long folderId, String name, Long parentFolderId) {
        Optional<Folder> folderOptional = folderRepository.findById(folderId);

        if (folderOptional.isPresent()) {

            Folder folder = folderOptional.get();
            folder.setName(name);
            folder.setLastModificationDate(LocalDateTime.now());

            if (parentFolderId == -1L) {
                folder.setParentFolder(null);
            } else {
                Optional<Folder> destinationOptional = folderRepository.findById(parentFolderId);
                if (destinationOptional.isPresent()) {

                    Folder destinationFolder = destinationOptional.get();
                    folder.setParentFolder(destinationFolder);

                } else {
                    log.error("Folder parent is not updated. Destination folder is not found by id={}", parentFolderId);
                }
            }

            Folder updatedFolder = folderRepository.save(folder);
            return new FolderDto(updatedFolder);

        } else {
            log.info("Folder update is failed. Folder is not found by id={}", folderId);
        }

        return null;
    }

    @Override
    public void deleteAllComponentsInFolder(Long folderId) {

        Optional<Folder> folderOptional = folderRepository.findById(folderId);

        if (folderOptional.isPresent()) {

            Folder folder = folderOptional.get();
            deleteRecursivelyComponentsInFolder(folder);

        } else {
            log.error("Folder is not found id={}", folderId);
        }

    }

    @Override
    public void deleteFolder(Long folderId) {
        // firstly, deleted sub folders and sub files
        deleteAllComponentsInFolder(folderId);
        // after deleting sub components successfully, delete folder itself
        folderRepository.deleteById(folderId);

        log.info("Folder is deleted with id={}", folderId);
    }

    /**
     * Delete sub components of given component recursively.
     * @param baseComponent BaseComponent
     */
    private void deleteRecursivelyComponentsInFolder(BaseComponent baseComponent) {

        if (baseComponent.getType() == ComponentType.FILE) {
            fileRepository.deleteById(baseComponent.getId());
            log.info("File is deleted with id={} name={}", baseComponent.getId(), baseComponent.getName());
            return;
        }

        Folder folder = (Folder) baseComponent;

        List<File> files = folder.getFiles();
        for (File file : files) {
            deleteRecursivelyComponentsInFolder(file);
        }

        for (BaseComponent component : folder.getFolders()) {
            deleteRecursivelyComponentsInFolder(component);

            folderRepository.deleteById(baseComponent.getId());
            log.info("Folder is deleted with id={} name={}", baseComponent.getId(), baseComponent.getName());
        }
    }
}
