package thundra.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "folder")
public class Folder extends BaseComponent {

    @OneToMany(mappedBy = "parentFolder",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Folder> folders;

    @OneToMany(mappedBy = "parentFolder",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> files;

    public Folder(String name, LocalDateTime creationDate, Folder parentFolder) {
        this.name = name;
        this.creationDate = creationDate;
        this.parentFolder = parentFolder;
    }
}
