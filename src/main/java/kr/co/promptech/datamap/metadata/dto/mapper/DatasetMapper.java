package kr.co.promptech.datamap.metadata.dto.mapper;

import kr.co.promptech.datamap.metadata.dto.dcat.DcatDataset;
import kr.co.promptech.datamap.metadata.dto.dcat.FoafOrganization;
import kr.co.promptech.datamap.metadata.dto.dcat.VcardIndividual;
import kr.co.promptech.datamap.metadata.model.*;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DatasetMapper {
    public static Dataset dcatToEntity(DcatDataset dcatDataset, Platform platform) {
        Dataset dataset = new ModelMapper().map(dcatDataset, Dataset.class);
        dataset.setPlatform(platform);
        String language = dcatDataset.getLanguage().getResource();
        language = language.replace("http://id.loc.gov/vocabulary/iso639-1/", "");
        if (StringUtils.isBlank(language)) {
            language = "ko";
        }
        dataset.setLanguage(language);
        dataset.setLandingPage(dcatDataset.getLandingPage().getResource());
        dataset.setIssued(dcatDataset.getIssued().getValue());
        dataset.setModified(dcatDataset.getModified().getValue());

        if (dcatDataset.getContactPoint() != null && dcatDataset.getContactPoint().getIndividual() != null) {
            VcardIndividual indivisual = dcatDataset.getContactPoint().getIndividual();
            Manager contactPoint = new Manager();
            contactPoint.setName(indivisual.getName());
            contactPoint.setEmail(indivisual.getEmail().getResource().replace("mailto:", ""));
            contactPoint.setPhone(indivisual.getPhone().getResource().replace("tel:", ""));

            dataset.setContactPoint(contactPoint);
        }

        if (dcatDataset.getCreator() != null && dcatDataset.getCreator().getOrganization() != null) {
            FoafOrganization foafCreator = dcatDataset.getCreator().getOrganization();
            Center creator = new ModelMapper().map(foafCreator, Center.class);
            creator.setPlatform(platform);
            creator.setHomepage(foafCreator.getHomepage().getResource());

            dataset.setCreator(creator);
        }

        if (dcatDataset.getPublisher() != null && dcatDataset.getPublisher().getOrganization() != null) {
            FoafOrganization foafPublisher = dcatDataset.getPublisher().getOrganization();
            Center publisher = new ModelMapper().map(foafPublisher, Center.class);
            publisher.setPlatform(platform);
            publisher.setHomepage(foafPublisher.getHomepage().getResource());

            dataset.setPublisher(publisher);
        }

        if (dcatDataset.getKeywords() != null && dcatDataset.getKeywords().length > 0) {
            Set<Keyword> keywordSet = new HashSet<>();
            for(String k : dcatDataset.getKeywords()) {
                Keyword keyword = new Keyword();
                keyword.setDataType(Keyword.TYPE_DATASET);
                keyword.setKeyword(k);
                keyword.setDataset(dataset);

                keywordSet.add(keyword);
            }
            dataset.setKeywords(keywordSet);
        }

        if (dcatDataset.getDistribution() != null && dcatDataset.getDistribution().getDistributions() != null) {
            Set<FileDatum> fileData = new HashSet<>();
            dcatDataset.getDistribution().getDistributions().forEach(distribution -> {
                FileDatum fileDatum = new ModelMapper().map(distribution, FileDatum.class);
                fileDatum.setIssued(distribution.getIssued().getValue());
                fileDatum.setModified(distribution.getModified().getValue());
                fileDatum.setAccessUrl(distribution.getAccessUrl().getResource());
                fileDatum.setDownloadUrl(distribution.getDownloadUrl().getResource());
                fileDatum.setDataset(dataset);

                fileData.add(fileDatum);
            });
            dataset.setFileData(fileData);
        }

        return dataset;
    }
}
