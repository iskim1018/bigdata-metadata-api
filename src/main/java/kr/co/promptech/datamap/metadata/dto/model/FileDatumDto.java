package kr.co.promptech.datamap.metadata.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileDatumDto {
    private Long id;
    private String title;
    private String description;
    private String filename;
    private String accessService;
    private String accessUrl;
    private String downloadUrl;
    private String compressFormat;
    private String mediaType;
    private String packageFormat;
    private String format;
    private String rights;
    private String mimeType;
    private String ext;
    private Long byteSize;
    private String issued;
    private String modified;
    private String spatialResolutionInMeters;
    private String temporalResolution;
    private String accessRights;
    private String license;
    private String version;
    private String versionDescription;
    private String priceInfo;
    private String qualityInfo;
}
