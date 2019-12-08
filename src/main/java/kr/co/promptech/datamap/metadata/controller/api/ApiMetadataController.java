package kr.co.promptech.datamap.metadata.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import kr.co.promptech.datamap.metadata.dto.dcat.DcatDataservice;
import kr.co.promptech.datamap.metadata.dto.dcat.DcatDataset;
import kr.co.promptech.datamap.metadata.dto.dcat.DcatRoot;
import kr.co.promptech.datamap.metadata.dto.mapper.ApiDatumMapper;
import kr.co.promptech.datamap.metadata.dto.mapper.DatasetMapper;
import kr.co.promptech.datamap.metadata.model.ApiDatum;
import kr.co.promptech.datamap.metadata.model.Dataset;
import kr.co.promptech.datamap.metadata.model.Platform;
import kr.co.promptech.datamap.metadata.service.ApiDatumService;
import kr.co.promptech.datamap.metadata.service.DatasetService;
import kr.co.promptech.datamap.metadata.service.PlatformService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/metadata")
public class ApiMetadataController {
    private static final String TAG = "[ApiMetadataController] ";

    @Autowired
    private PlatformService platformService;

    @Autowired
    private DatasetService datasetService;

    @Autowired
    private ApiDatumService apiDatumService;

    @PostMapping(value = "/init", produces = MediaType.APPLICATION_XML_VALUE)
    public String init(@RequestHeader(value = PlatformService.HEADER_STRING, required = false) String token,
                           @RequestBody String dcatStr) {
        Platform platform = platformService.findByToken(token);
        if (platform == null) {
            return "errors/401.xml";
        }

        platformService.clearMetadata(platform);
        createOrUpdateList(platform, dcatStr);

        return "success/200.xml";
    }

    @PostMapping(value = "/upsert", produces = MediaType.APPLICATION_XML_VALUE)
    public String upsert(@RequestHeader(value = PlatformService.HEADER_STRING, required = false) String token,
                             @RequestBody String dcatStr) {
        Platform platform = platformService.findByToken(token);
        if (platform == null) {
            return "errors/401.xml";
        }

        createOrUpdateList(platform, dcatStr);

        return "success/200.xml";
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_XML_VALUE)
    public String delete(@RequestHeader(value = PlatformService.HEADER_STRING, required = false) String token,
                         @RequestBody String dcatStr) {
       Platform platform = platformService.findByToken(token);

        if (platform == null) {
            return "errors/401.xml";
        }

        deleteList(platform, dcatStr);

        return "success/200.xml";
    }

    private void createOrUpdateList(Platform platform, String dcatStr) {
        try {
            DcatRoot rootRdf = getXmlMapper().readValue(dcatStr, DcatRoot.class);
            for(DcatDataset d : rootRdf.getDatasets()) {
                Dataset dataset = DatasetMapper.dcatToEntity(d, platform);
                datasetService.createOrUpdate(dataset);
            }
            for(DcatDataservice ds : rootRdf.getDataservices()) {
                ApiDatum apiDatum = ApiDatumMapper.dcatToEntity(ds, platform);
                apiDatumService.createOrUpdate(apiDatum);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void deleteList(Platform platform, String dcatStr) {
        try {
            DcatRoot rootRdf = getXmlMapper().readValue(dcatStr, DcatRoot.class);
            for(DcatDataset d : rootRdf.getDatasets()) {
                if (StringUtils.isNotBlank(d.getIdentifier())) {
                    Dataset dataset = new Dataset();
                    dataset.setPlatform(platform);
                    dataset.setIdentifier(d.getIdentifier());

                    datasetService.deleteByIdentifier(dataset);
                }
            }
            for(DcatDataservice ds : rootRdf.getDataservices()) {
                if (StringUtils.isNotBlank(ds.getIdentifier())) {
                    ApiDatum apiDatum = new ApiDatum();
                    apiDatum.setPlatform(platform);
                    apiDatum.setIdentifier(ds.getIdentifier());

                    apiDatumService.deleteByIdentifier(apiDatum);
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private XmlMapper getXmlMapper() {
        JacksonXmlModule jacksonXmlModule = new JacksonXmlModule();
        jacksonXmlModule.setDefaultUseWrapper(false);

        XmlMapper xmlMapper = new XmlMapper(jacksonXmlModule);
        xmlMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        return xmlMapper;
    }
}
