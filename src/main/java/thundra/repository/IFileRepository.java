package thundra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thundra.model.File;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface IFileRepository extends JpaRepository<File, Long> {
    List<File> findALlByParentFolder(File parentFile);
}
