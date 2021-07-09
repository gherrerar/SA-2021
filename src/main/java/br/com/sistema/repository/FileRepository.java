package br.com.sistema.repository;

import br.com.sistema.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByProjectId(long project_id);
}
