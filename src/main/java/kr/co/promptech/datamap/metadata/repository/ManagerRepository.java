package kr.co.promptech.datamap.metadata.repository;

import kr.co.promptech.datamap.metadata.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByEmail(String email);

    Manager findByPhone(String phone);
}
