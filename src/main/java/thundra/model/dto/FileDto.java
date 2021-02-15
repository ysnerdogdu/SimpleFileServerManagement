package thundra.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import thundra.model.File;

@AllArgsConstructor
@NoArgsConstructor
public class FileDto extends BaseComponentDto {

    @Setter
    @Getter
    private String extension;

    @Setter
    @Getter
    private String content;


    public FileDto(File file) {
        this.id = file.getId();
        this.name = file.getName();
        this.extension = file.getExtension();
        this.content = file.getContent();
        this.size = file.getSize();
        this.type = file.getType();
        this.creationDate = file.getCreationDate();
        this.lastModificationDate = file.getLastModificationDate();
        this.parentFolderId = file.getParentFolder() != null ? file.getParentFolder().getId() : -1;
    }

    public Long getSize() {
        return this.size;
    }
}
