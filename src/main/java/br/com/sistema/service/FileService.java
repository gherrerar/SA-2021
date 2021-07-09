package br.com.sistema.service;

import br.com.sistema.model.File;
import br.com.sistema.model.Project;

import java.util.List;


public interface FileService {
    File save(File file);
    List<File> findAll();
    List<File> findAllByProjectId (long projectId);
}
