package thundra.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thundra.model.File;
import thundra.model.Folder;
import thundra.model.dto.FileDto;
import thundra.repository.IFileRepository;
import thundra.repository.IFolderRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class FileService implements IFileService {

    private final IFileRepository fileRepository;
    private final IFolderRepository folderRepository;

    @Autowired
    public FileService(IFileRepository fileRepository,
                       IFolderRepository folderRepository) {
        this.fileRepository = fileRepository;
        this.folderRepository = folderRepository;
    }

    @Override
    public FileDto getFileById(Long fileId) {

        Optional<File> fileOptional = fileRepository.findById(fileId);

        if (fileOptional.isEmpty()) {
            log.error("File is not found with given id={}", fileId);
            return null;
        }

        return new FileDto(fileOptional.get());

    }

    @Override
    public FileDto updateFile(Long fileId, FileDto fileDto) {

        Optional<File> fileOpt = fileRepository.findById(fileId);

        if (fileOpt.isPresent()) {
            File file = fileOpt.get();
            file.setName(fileDto.getName());
            file.setExtension(fileDto.getExtension());
            file.setContent(fileDto.getContent());
            file.setSize(fileDto.getSize());
            file.setLastModificationDate(LocalDateTime.now());

            if (fileDto.getParentFolderId() == -1) { // It means that it wanted to be move to root
                file.setParentFolder(null);
            } else {
                Optional<Folder> newParentFolderOpt = folderRepository.findById(fileDto.getParentFolderId());
                if (newParentFolderOpt.isPresent()) {
                    file.setParentFolder(newParentFolderOpt.get());
                } else {
                    log.error("File new parent folder is not found with fileId={} parentFolderId={}", fileId, fileDto.getParentFolderId());
                }
                newParentFolderOpt.ifPresent(file::setParentFolder);
            }

            File savedFile = fileRepository.save(file);
            return new FileDto(savedFile);
        } else {
            log.error("File update is failed. File not found with id={}", fileId);
        }

        return null;
    }

    @Override
    public FileDto createFile(String fileName, String fileExt, Long parentFolderId) {

        Optional<Folder> folderOptional = folderRepository.findById(parentFolderId);

        if (folderOptional.isPresent()) {
            File newFile = new File(fileName, fileExt, folderOptional.get(), LocalDateTime.now());

            File createdFile = fileRepository.save(newFile);
            return new FileDto(createdFile);
        }

        log.info("File creation is failed. Parent file is not found by Id={} ", parentFolderId);
        return null;
    }

    @Override
    public void deleteFile(Long fileId) {
        fileRepository.deleteById(fileId);
    }
}
