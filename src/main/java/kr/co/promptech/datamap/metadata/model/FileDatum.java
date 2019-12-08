package kr.co.promptech.datamap.metadata.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name="file_data")
public class FileDatum {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    @Type(type = "text")
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

    @Column(columnDefinition = "TEXT")
    @Type(type = "text")
    private String priceInfo;

    @Column(columnDefinition = "TEXT")
    @Type(type = "text")
    private String qualityInfo;

    @ManyToOne
    @JoinColumn(name = "dataset_id")
    private Dataset dataset;
}
