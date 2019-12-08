package kr.co.promptech.datamap.metadata.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiDatumDto {
    private Long id;
    private String title;
    private String description;
    private String identifier;
    private String theme;
    private String license;
    private String accessRights;
    private String landingPage;
    private String language;
    private String rights;
    private String version;
    private String versionDescription;
    private String endpointUrl;
    private String endpointDesc;
    private String issued;
    private String modified;
    private String priceInfo;
    private String qualityInfo;
    private String servesDataset;

    @JsonProperty("contactPoint")
    private ManagerDto contactPoint;

    @JsonProperty("creator")
    private CenterDto creator;

    @JsonProperty("publisher")
    private CenterDto publisher;

    @JsonProperty("keywords")
    private Set<String> keywords;
}
