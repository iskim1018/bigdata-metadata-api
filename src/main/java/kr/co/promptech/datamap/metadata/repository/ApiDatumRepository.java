package kr.co.promptech.datamap.metadata.repository;

import kr.co.promptech.datamap.metadata.model.ApiDatum;
import kr.co.promptech.datamap.metadata.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiDatumRepository extends JpaRepository<ApiDatum, Long> {
    ApiDatum findOneByIdentifier(String identifier);

    long deleteByIdentifierAndPlatform(String identifier, Platform platform);
}
