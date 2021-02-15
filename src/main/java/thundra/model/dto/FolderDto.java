package thundra.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import thundra.model.Folder;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FolderDto extends BaseComponentDto {

    @Setter
    private List<FolderDto> folders;

    @Setter
    private List<FileDto> files;

    public FolderDto(Folder folder) {
        this.id = folder.getId();
        this.name = folder.getName();
        this.type = folder.getType();
        this.creationDate = folder.getCreationDate();
        this.lastModificationDate = folder.getLastModificationDate();
        this.folders = folder.getFolders().stream()
                .map(FolderDto::new)
                .collect(Collectors.toList());
        this.files = folder.getFiles().stream()
                .map(FileDto::new)
                .collect(Collectors.toList());
        this.size = this.getSize();
        this.parentFolderId = folder.getParentFolder() != null ? folder.getParentFolder().getId() : -1;
    }

    @Override
    public Long getSize() {
        long folderSum = folders.stream().mapToLong(FolderDto::getSize).sum();
        long fileSum = files.stream().mapToLong(FileDto::getSize).sum();
        return folderSum + fileSum;
    }
}
