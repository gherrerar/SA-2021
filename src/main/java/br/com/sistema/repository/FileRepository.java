package br.com.sistema.repository;

import br.com.sistema.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<Image, Long> {
    List<Image> findByProjectId(long project_id);
}
