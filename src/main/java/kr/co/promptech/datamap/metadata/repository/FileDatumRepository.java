package kr.co.promptech.datamap.metadata.repository;

import kr.co.promptech.datamap.metadata.model.FileDatum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDatumRepository extends JpaRepository<FileDatum, Long> {
}
