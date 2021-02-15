package thundra.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import thundra.model.ComponentType;
import thundra.model.dto.FileDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@Transactional
class FileServiceTest {

    @Autowired
    private IFileService fileService;

    @Test
    void getFileById() {
        Long fileId = 1L;
        String name = "simpleFile1";
        String ext = "txt";
        FileDto fileDto = fileService.getFileById(fileId);

        assertNotNull(fileDto);
        assertEquals(1L, fileDto.getId());
        assertEquals(name, fileDto.getName());
        assertEquals(ext, fileDto.getExtension());
        assertEquals(ComponentType.FILE, fileDto.getType());
    }

    @Test
    void updateFile() {
        Long fileId = 2L;
        String name = "Simple File2";

        FileDto fileDto = new FileDto();
        fileDto.setName(name);
        fileDto.setParentFolderId(102L);

        FileDto updatedFileDto = fileService.updateFile(fileId, fileDto);

        assertNotNull(updatedFileDto);
        assertEquals(name, updatedFileDto.getName());
        assertEquals(102L, updatedFileDto.getParentFolderId());
    }
}
