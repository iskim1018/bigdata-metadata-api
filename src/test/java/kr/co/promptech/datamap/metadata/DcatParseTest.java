package kr.co.promptech.datamap.metadata;

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
import kr.co.promptech.datamap.metadata.repository.PlatformRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
public class DcatParseTest {
    @Autowired
    private PlatformRepository platformRepository;

    @Test
    void parseTest() {
        try {
            Platform platform = platformRepository.getOne(11L); // Test용 Platform 조회

            String readContent = new String(Files.readAllBytes(Paths.get("src","test","resources", "dcat", "sample.xml")));
            System.out.println(readContent);

            JacksonXmlModule jacksonXmlModule = new JacksonXmlModule();
            jacksonXmlModule.setDefaultUseWrapper(false);

            XmlMapper xmlMapper = new XmlMapper(jacksonXmlModule);
            xmlMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
            xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            xmlMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            try {
                DcatRoot rootRdf = xmlMapper.readValue(readContent, DcatRoot.class);
                for(DcatDataset d : rootRdf.getDatasets()) {
                    Dataset dataset = DatasetMapper.dcatToEntity(d, platform);
                    System.out.println("DcatDatasetDto ==============================");
                    System.out.println(d.toString());
                    System.out.println("Dataset Entity ==============================");
                    System.out.println(dataset.toString());
                }
                for(DcatDataservice ds : rootRdf.getDataservices()) {
                    ApiDatum apiDatum = ApiDatumMapper.dcatToEntity(ds, platform);
                    System.out.println("DcatDataserviceDto ==============================");
                    System.out.println(ds.toString());
                    System.out.println("Dataservice(ApiDatum) Entity ==============================");
                    System.out.println(apiDatum.toString());
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
