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
    public Image save(Image image) {
            return fileRepository.save(image);
    }

    @Override
    public List<Image> findAllByProjectId(long projectId) { return fileRepository.findByProjectId(projectId); }

    @Override
    public List<Image> findAll() {
        return fileRepository.findAll();
    }
}
