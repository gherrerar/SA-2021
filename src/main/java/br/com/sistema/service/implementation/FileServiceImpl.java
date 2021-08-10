package br.com.sistema.service.implementation;

import br.com.sistema.model.Image;
import br.com.sistema.repository.FileRepository;
import br.com.sistema.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    @Override
    public List<Image> findAllByProjectId(long projectId) { return fileRepository.findByProjectId(projectId); }

    @Override
    public void deleteAllById(long id) {
        List<Image> images = this.findAllByProjectId(id);
        images.forEach(image -> {
            fileRepository.deleteById(image.id);
        });
    }

}
