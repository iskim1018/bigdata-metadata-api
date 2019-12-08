package kr.co.promptech.datamap.metadata.dto.dcat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JacksonXmlRootElement(localName = "Distribution")
public class DcatDistribution {
    private String accessService;

    @JacksonXmlProperty(namespace = "dcat", localName = "accessURL")
    private RdfResource accessUrl;

    private Long byteSize;
    private String compressFormat;

    @JacksonXmlProperty(namespace = "dcat", localName = "downloadURL")
    private RdfResource downloadUrl;

    private String mediaType;
    private String format;
    private String packageFormat;
    private String spatialResolutionInMeters;
    private String temporalResolution;
    private String accessRights;
    private String description;
    private RdfDate issued;
    private RdfDate modified;
    private String license;
    private String rights;
    private String title;
    private String version;
    private String versionDescription;
    private String priceInfo;
    private String qualityInfo;
}
