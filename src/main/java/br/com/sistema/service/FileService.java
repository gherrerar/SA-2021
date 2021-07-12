package br.com.sistema.service;

import br.com.sistema.model.Image;

import java.util.List;


public interface FileService {
    Image save(Image image);
    List<Image> findAll();
    List<Image> findAllByProjectId (long projectId);
}
