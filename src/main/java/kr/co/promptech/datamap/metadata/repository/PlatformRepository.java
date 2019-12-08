package kr.co.promptech.datamap.metadata.repository;

import kr.co.promptech.datamap.metadata.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
    Platform findByCode(String code);

    Platform findByToken(String token);

    @Query("SELECT DISTINCT p FROM Platform p " +
            "LEFT JOIN FETCH p.datasets d " +
            "LEFT JOIN FETCH d.creator " +
            "LEFT JOIN FETCH d.publisher " +
            "LEFT JOIN FETCH d.contactPoint " +
            "LEFT JOIN FETCH d.fileData fd " +
            "LEFT JOIN FETCH d.keywords dk " +
            "LEFT JOIN FETCH p.apiData a " +
            "LEFT JOIN FETCH a.creator " +
            "LEFT JOIN FETCH a.publisher " +
            "LEFT JOIN FETCH a.contactPoint " +
            "LEFT JOIN FETCH a.keywords ak " +
            "WHERE p.id = :id")
    Platform findWithMetadata(@Param("id") Long id);
}
