package thundra.service;
import thundra.model.dto.FileDto;

public interface IFileService {

    FileDto getFileById(Long fileId);

    FileDto updateFile(Long fileId, FileDto newFile);

    FileDto createFile(String fileName, String fileExt, Long parentFolderId);

    void deleteFile(Long fileId);
}
