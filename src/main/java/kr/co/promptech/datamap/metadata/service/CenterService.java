package kr.co.promptech.datamap.metadata.service;

import kr.co.promptech.datamap.metadata.model.Center;
import kr.co.promptech.datamap.metadata.model.Platform;
import kr.co.promptech.datamap.metadata.repository.CenterRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CenterService {
    @Autowired
    private CenterRepository centerRepository;

    public Center findOrCreateCenter(Center c) {
        Center center = null;

        if (StringUtils.isNotBlank(c.getEmail())) {
            center = centerRepository.findByEmail(c.getEmail());
        } else if (StringUtils.isNotBlank(c.getPhone())) {
            center = centerRepository.findByPhone(c.getPhone());
        }

        if (center == null) {
            center = centerRepository.save(c);
        }

        return center;
    }

    public Center findOrCreateCenter(Platform platform, String name, String phone, String email, String homepage) {
        Center center = new Center();
        center.setPlatform(platform);
        center.setName(name);
        center.setPhone(phone);
        center.setEmail(email);
        center.setHomepage(homepage);

        return findOrCreateCenter(center);
    }
}
