package thundra.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "name")
    protected String name;

    @Column(name = "size")
    protected Long size;

    @Column(name = "creation_date")
    protected LocalDateTime creationDate;

    @Column(name = "last_modification_date")
    protected LocalDateTime lastModificationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    protected ComponentType type;

    @ManyToOne
    @JoinColumn(name = "parent_folder_id", referencedColumnName = "id")
    protected Folder parentFolder;

}
