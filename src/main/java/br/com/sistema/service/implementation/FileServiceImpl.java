package br.com.sistema.service.implementation;

import br.com.sistema.model.File;
import br.com.sistema.repository.FileRepository;
import br.com.sistema.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    public File save(File file) {
            return fileRepository.save(file);
    }

    @Override
    public List<File> findAllByProjectId(long projectId) {
        return fileRepository.findByProjectId(projectId);
    }
}
