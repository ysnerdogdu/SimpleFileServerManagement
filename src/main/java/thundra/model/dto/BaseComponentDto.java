package thundra.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import thundra.model.BaseComponent;
import thundra.model.ComponentType;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseComponentDto {

    protected Long id;

    @Setter
    protected String name;

    @Setter
    protected Long size;

    protected LocalDateTime creationDate;

    @Setter
    protected LocalDateTime lastModificationDate;

    protected ComponentType type;

    @Setter
    protected Long parentFolderId;


    public BaseComponentDto(BaseComponent baseComponent) {
        this.id = baseComponent.getId();
        this.name = baseComponent.getName();
        this.size = baseComponent.getSize();
        this.creationDate = baseComponent.getCreationDate();
        this.lastModificationDate = baseComponent.getLastModificationDate();
        this.type = baseComponent.getType();
        this.parentFolderId = baseComponent.getParentFolder() != null ? baseComponent.getParentFolder().getId() : -1;
    }

}
