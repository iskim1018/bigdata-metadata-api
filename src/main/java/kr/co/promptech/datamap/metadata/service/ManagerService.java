package kr.co.promptech.datamap.metadata.service;

import kr.co.promptech.datamap.metadata.model.Manager;
import kr.co.promptech.datamap.metadata.repository.ManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    public Manager findOrCreate(Manager m) {
        Manager manager = null;

        if (StringUtils.isNotBlank(m.getEmail())) {
            manager = managerRepository.findByEmail(m.getEmail());
        } else if (StringUtils.isNotBlank(m.getPhone())) {
            manager = managerRepository.findByPhone(m.getPhone());
        }

        if (manager == null) {
            manager = managerRepository.save(m);
        }

        return manager;
    }
}
