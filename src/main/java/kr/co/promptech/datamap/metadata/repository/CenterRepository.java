package kr.co.promptech.datamap.metadata.repository;

import kr.co.promptech.datamap.metadata.model.Center;
import kr.co.promptech.datamap.metadata.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterRepository extends JpaRepository<Center, Long> {
    Center findByCode(String code);

    Center findByHomepage(String homepage);
    Center findByEmail(String email);
    Center findByPhone(String phone);

    long deleteByPlatform(Platform platform);
}
