package thundra.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="file")
public class File extends BaseComponent {

    @Column(name = "extension")
    private String extension;

    @Column(name = "content")
    private String content;

    public File(String name, String extension, Folder parentFolder, LocalDateTime creationDate) {
        this.name = name;
        this.extension = extension;
        this.parentFolder = parentFolder;
        this.creationDate = creationDate;
    }
}
