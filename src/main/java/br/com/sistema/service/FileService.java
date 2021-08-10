package br.com.sistema.service;

import br.com.sistema.model.Image;

import java.util.List;

public interface FileService {
    List<Image> findAllByProjectId (long project_id);
    void deleteAllById(long id);
}
