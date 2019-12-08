package kr.co.promptech.datamap.metadata.service;

import kr.co.promptech.datamap.metadata.model.ApiDatum;
import kr.co.promptech.datamap.metadata.repository.ApiDatumRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class ApiDatumService {
    @Autowired
    private ManagerService managerService;

    @Autowired
    private CenterService centerService;

    @Autowired
    private ApiDatumRepository apiDatumRepository;

    @Transactional
    public void createOrUpdate(ApiDatum apiDatum) {
        ApiDatum existApiDatum = apiDatumRepository.findOneByIdentifier(apiDatum.getIdentifier());
        if (existApiDatum != null) {
            apiDatum.setId(existApiDatum.getId());
        }

        if (apiDatum.getContactPoint() != null) {
            apiDatum.setContactPoint(managerService.findOrCreate(apiDatum.getContactPoint()));
        }

        if (apiDatum.getCreator() != null) {
            apiDatum.setCreator(centerService.findOrCreateCenter(apiDatum.getCreator()));
        }

        if (apiDatum.getPublisher() != null) {
            apiDatum.setPublisher(centerService.findOrCreateCenter(apiDatum.getPublisher()));
        }

        apiDatumRepository.save(apiDatum);
    }

    @Transactional
    public void deleteByIdentifier(ApiDatum apiDatum) {
        apiDatumRepository.deleteByIdentifierAndPlatform(apiDatum.getIdentifier(), apiDatum.getPlatform());
    }
}
