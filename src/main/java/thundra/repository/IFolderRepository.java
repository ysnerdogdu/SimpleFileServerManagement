package thundra.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import thundra.model.Folder;

import java.util.List;

@Transactional
public interface IFolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findALlByParentFolder(Folder parentFolder);
}
