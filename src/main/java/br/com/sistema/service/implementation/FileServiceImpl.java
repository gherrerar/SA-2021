package br.com.sistema.service.implementation;

import br.com.sistema.model.Image;
import br.com.sistema.repository.FileRepository;
import br.com.sistema.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    @Override
    public Image save(Image image) {
            return fileRepository.save(image);
    }

    @Override
    public List<Image> findAllByProjectId(long projectId) { return fileRepository.findByProjectId(projectId); }

    @Override
    public Boolean deleteFilesInFolder(long id) {
        List<Image> images = this.findAllByProjectId(id);
        images.forEach(image -> {
                try {
                    Files.deleteIfExists(Paths.get("src/main/upload/images/" + image.getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
        return true;
    }

    @Override
    public void deleteAllById(long id) {
        List<Image> images = this.findAllByProjectId(id);
        images.forEach(image -> {
            fileRepository.deleteById(image.id);
        });
    }

    @Override
    public List<Image> findAll() {
        return fileRepository.findAll();
    }
}
