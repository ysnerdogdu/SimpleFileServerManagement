package thundra.service;

import thundra.model.dto.FolderDto;

public interface IFolderService {

    FolderDto getFolderById(Long folderId);

    FolderDto getRootComponents();

    FolderDto createFolder(String name, Long parentFolderId);

    FolderDto updateFolder(Long folderId, String name, Long parentFolderId);

    void deleteAllComponentsInFolder(Long folderId);

    void deleteFolder(Long folderId);
}
