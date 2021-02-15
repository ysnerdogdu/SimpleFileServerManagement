package thundra.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import thundra.model.dto.FolderDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@Transactional
class FolderServiceTest {

    @Autowired
    private IFolderService folderService;

    @Test
    void getFolderById() {
        Long folderId = 102L;
        FolderDto result = folderService.getFolderById(folderId);

        assertNotNull(result);
        assertEquals(2, result.getFolders().size());
        assertEquals(1, result.getFiles().size());
    }

    @Test
    void getRootComponents() {
        FolderDto folderDto = folderService.getRootComponents();

        assertNotNull(folderDto);
        assertEquals(2, folderDto.getFolders().size());
        assertEquals(1, folderDto.getFiles().size());
    }

    @Test
    void updateFolder() {

        Long folderId = 104L;
        String newName = "Simple Folder2";
        Long newParentFolderId = 101L;

        FolderDto folderDto = folderService.updateFolder(folderId, newName, newParentFolderId);

        assertNotNull(folderDto);
        assertEquals(newName, folderDto.getName());
        assertEquals(newParentFolderId, folderDto.getParentFolderId());

    }
}
