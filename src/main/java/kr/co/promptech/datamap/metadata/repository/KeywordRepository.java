package kr.co.promptech.datamap.metadata.repository;

import kr.co.promptech.datamap.metadata.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}
