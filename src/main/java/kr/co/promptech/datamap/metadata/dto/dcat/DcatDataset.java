package kr.co.promptech.datamap.metadata.dto.dcat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DcatDataset {
    private String accessRights;
    private Vcard contactPoint;
    private Foaf creator;
    private String description;
    private String title;
    private RdfDate issued;
    private RdfDate modified;
    private RdfResource language;
    private Foaf publisher;
    private String identifier;
    private String theme;
    private String type;

    @JacksonXmlProperty(namespace = "dcat", localName = "keyword")
    private String[] keywords;

    private RdfResource landingPage;
    private String license;
    private String rights;
    private String spatialResolutionInMeters;
    private String temporalResolution;
    private String accrualPeriodicity;
    private String spatial;
    private String temporal;
    private String version;
    private String versionDescription;
    private String priceInfo;
    private String qualityInfo;
    private DcatDistributionWrap distribution;
}

