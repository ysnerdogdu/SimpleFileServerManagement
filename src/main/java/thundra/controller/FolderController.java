package thundra.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thundra.model.dto.FolderDto;
import thundra.service.IFolderService;


@RestController
@CrossOrigin
@RequestMapping(value = "/folders")
public class FolderController {

    private final IFolderService folderService;

    @Autowired
    public FolderController(IFolderService folderService) {
        this.folderService = folderService;
    }

    /**
     * Fetch folder info by given id
     * @param id database if of folder
     * @return FolderDto
     */
    @GetMapping()
    public ResponseEntity<FolderDto> getFolderById(@RequestParam Long id) {

        try {
            FolderDto folderDto;

            // -1 id means that client wants to fetch root folders and files
            if (id == -1L) {
                folderDto = folderService.getRootComponents();
            } else {
                folderDto = folderService.getFolderById(id);
            }

            if (folderDto != null) {
                return new ResponseEntity<>(folderDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creat new Folder entity in db
     * @param folderDto includes properties of Folder entity
     * @return FolderDto object of created Folder entity
     */
    @PostMapping()
    public ResponseEntity<FolderDto> createFolder(@RequestBody FolderDto folderDto) {

        try {
            FolderDto createdFolder = folderService.createFolder(folderDto.getName(), folderDto.getParentFolderId());

            if (createdFolder != null) {
                return new ResponseEntity<>(createdFolder, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update name and parentFolderId of folder by given id
     * @param id database id of folder
     * @param name updated name of folder
     * @param parentFolderId updated parent folder database id
     * @return updated FolderDto
     */
    @PutMapping()
    public ResponseEntity<FolderDto> updateFolder(@RequestParam Long id,
                                                  @RequestParam String name,
                                                  @RequestParam Long parentFolderId) {

        try {
            FolderDto folderDto = folderService.updateFolder(id, name, parentFolderId);

            if (folderDto != null) {
                return new ResponseEntity<>(folderDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete folder from db by given id
     * @param id database id folder
     * @return HttpStatus result
     */
    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteFolder(@RequestParam Long id) {

        try {
            folderService.deleteFolder(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
