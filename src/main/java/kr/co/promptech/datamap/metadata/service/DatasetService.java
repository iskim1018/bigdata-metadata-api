package kr.co.promptech.datamap.metadata.service;

import kr.co.promptech.datamap.metadata.model.Dataset;
import kr.co.promptech.datamap.metadata.repository.DatasetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class DatasetService {
    @Autowired
    private ManagerService managerService;

    @Autowired
    private CenterService centerService;

    @Autowired
    private DatasetRepository datasetRepository;

    @Transactional
    public void createOrUpdate(Dataset dataset) {
        Dataset existDataset = datasetRepository.findOneByIdentifier(dataset.getIdentifier());
        if (existDataset != null) {
            dataset.setId(existDataset.getId());
        }

        if (dataset.getContactPoint() != null) {
            dataset.setContactPoint(managerService.findOrCreate(dataset.getContactPoint()));
        }

        if (dataset.getCreator() != null) {
            dataset.setCreator(centerService.findOrCreateCenter(dataset.getCreator()));
        }

        if (dataset.getPublisher() != null) {
            dataset.setPublisher(centerService.findOrCreateCenter(dataset.getPublisher()));
        }

        datasetRepository.save(dataset);
    }

    @Transactional
    public void deleteByIdentifier(Dataset dataset) {
        datasetRepository.deleteByIdentifierAndPlatform(dataset.getIdentifier(), dataset.getPlatform());
    }
}
