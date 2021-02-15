package thundra.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thundra.model.dto.BaseComponentDto;
import thundra.model.dto.FileDto;
import thundra.service.IFileService;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/files")
public class FileController {

    private final IFileService fileService;

    @Autowired
    public FileController(IFileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Fetch file info from db by given id
     * @param id database id of file
     * @return FileDto object File entity
     */
    @GetMapping()
    public ResponseEntity<FileDto> getFileById(@RequestParam Long id) {

        try {
            FileDto fileDto = fileService.getFileById(id);

            if (fileDto != null) {
                return new ResponseEntity<>(fileDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Create new File entity by given properties of FileDto instance
     * @param fileDto includes properties of File
     * @return FileDto object of created File entity
     */
    @PostMapping()
    public ResponseEntity<FileDto> createFile(@RequestBody FileDto fileDto) {

        try {
            FileDto createdFile = fileService.createFile(fileDto.getName(), fileDto.getExtension(), fileDto.getParentFolderId());

            if (createdFile != null) {
                return new ResponseEntity<>(createdFile, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Update file entity which corresponds given id with fileDto object properties
     * @param id database id of file
     * @param fileDto includes new values
     * @return fileDto object of updated file
     */
    @PutMapping()
    public ResponseEntity<FileDto> updateFile(@RequestParam Long id,
                                              @RequestBody FileDto fileDto) {

        try {
            FileDto updatedFile = fileService.updateFile(id, fileDto);

            if (fileDto != null) {
                return new ResponseEntity<>(updatedFile, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    /**
     * Delete file from db by given id
     * @param id database id of file
     * @return HttpStatus result
     */
    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteFile(@RequestParam Long id) {

        try {
            fileService.deleteFile(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
