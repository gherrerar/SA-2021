package br.com.sistema.service;

import br.com.sistema.model.File;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FileService {
    File save(File file);
    List<File> findAllByProjectId (long projectId);
}
