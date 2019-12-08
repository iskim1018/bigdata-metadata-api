package kr.co.promptech.datamap.metadata.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatasetDto {
    private Long id;
    private String title;
    private String description;
    private String identifier;
    private String theme;
    private String license;
    private String accessRights;
    private String language;
    private String landingPage;
    private String rights;
    private String accrualPeriodicity;
    private String issued;
    private String modified;
    private String spatial;
    private String spatialResolutionInMeters;
    private String temporal;
    private String temporalResolution;
    private String version;
    private String versionDescription;
    private String priceInfo;
    private String qualityInfo;

    @JsonProperty("contactPoint")
    private ManagerDto contactPoint;

    @JsonProperty("creator")
    private CenterDto creator;

    @JsonProperty("publisher")
    private CenterDto publisher;

    @JsonProperty("distributions")
    private Set<FileDatumDto> fileData;

    @JsonProperty("keywords")
    private Set<String> keywords;
}
