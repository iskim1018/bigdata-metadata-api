package kr.co.promptech.datamap.metadata.dto.mapper;

import kr.co.promptech.datamap.metadata.dto.dcat.DcatDataservice;
import kr.co.promptech.datamap.metadata.dto.dcat.FoafOrganization;
import kr.co.promptech.datamap.metadata.dto.dcat.VcardIndividual;
import kr.co.promptech.datamap.metadata.model.*;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ApiDatumMapper {
    public static ApiDatum dcatToEntity(DcatDataservice dcatDataservice, Platform platform) {
        ApiDatum apiDatum = new ModelMapper().map(dcatDataservice, ApiDatum.class);
        apiDatum.setPlatform(platform);
        String language = dcatDataservice.getLanguage().getResource();
        language = language.replace("http://id.loc.gov/vocabulary/iso639-1/", "");
        if (StringUtils.isBlank(language)) {
            language = "ko";
        }
        apiDatum.setLanguage(language);
        apiDatum.setLandingPage(dcatDataservice.getLandingPage().getResource());
        apiDatum.setIssued(dcatDataservice.getIssued().getValue());
        apiDatum.setModified(dcatDataservice.getModified().getValue());
        apiDatum.setEndpointUrl(dcatDataservice.getEndpointUrl().getResource());

        if (dcatDataservice.getContactPoint() != null && dcatDataservice.getContactPoint().getIndividual() != null) {
            VcardIndividual indivisual = dcatDataservice.getContactPoint().getIndividual();
            Manager contactPoint = new Manager();
            contactPoint.setName(indivisual.getName());
            contactPoint.setEmail(indivisual.getEmail().getResource().replace("mailto:", ""));
            contactPoint.setPhone(indivisual.getPhone().getResource().replace("tel:", ""));

            apiDatum.setContactPoint(contactPoint);
        }

        if (dcatDataservice.getCreator() != null && dcatDataservice.getCreator().getOrganization() != null) {
            FoafOrganization foafCreator = dcatDataservice.getCreator().getOrganization();
            Center creator = new ModelMapper().map(foafCreator, Center.class);
            creator.setPlatform(platform);

            apiDatum.setCreator(creator);
        }

        if (dcatDataservice.getPublisher() != null && dcatDataservice.getPublisher().getOrganization() != null) {
            FoafOrganization foafPublisher = dcatDataservice.getPublisher().getOrganization();
            Center publisher = new ModelMapper().map(foafPublisher, Center.class);
            publisher.setPlatform(platform);

            apiDatum.setPublisher(publisher);
        }

        if (dcatDataservice.getKeywords() != null && dcatDataservice.getKeywords().length > 0) {
            Set<Keyword> keywordSet = new HashSet<>();
            for(String k : dcatDataservice.getKeywords()) {
                Keyword keyword = new Keyword();
                keyword.setDataType(Keyword.TYPE_API);
                keyword.setKeyword(k);
                keyword.setApiDatum(apiDatum);

                keywordSet.add(keyword);
            }
            apiDatum.setKeywords(keywordSet);
        }

        return apiDatum;
    }
}
