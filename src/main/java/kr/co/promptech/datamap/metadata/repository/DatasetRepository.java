package kr.co.promptech.datamap.metadata.repository;

import kr.co.promptech.datamap.metadata.model.Dataset;
import kr.co.promptech.datamap.metadata.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatasetRepository extends JpaRepository<Dataset, Long> {
    Dataset findOneByIdentifier(String identifier);

    long deleteByIdentifierAndPlatform(String identifier, Platform platform);
}
