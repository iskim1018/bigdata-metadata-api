package kr.co.promptech.datamap.metadata.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name="datasets")
@ToString
public class Dataset {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    @Type(type = "text")
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

    @Lob
    private String spatial;

    private String spatialResolutionInMeters;

    @Lob
    private String temporal;
    private String temporalResolution;
    private String version;
    private String versionDescription;

    @Column(columnDefinition = "TEXT")
    @Type(type = "text")
    private String priceInfo;

    @Column(columnDefinition = "TEXT")
    @Type(type = "text")
    private String qualityInfo;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="platform_id")
    private Platform platform;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="catalog_id")
    private Catalog catalog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contact_point_id")
    private Manager contactPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="creator_id")
    private Center creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="publisher_id")
    private Center publisher;

    @OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL}, mappedBy = "dataset", orphanRemoval=true)
    private Set<FileDatum> fileData;

    @OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL}, mappedBy = "dataset", orphanRemoval=true)
    @Where(clause = "data_type = 'dataset'")
    private Set<Keyword> keywords;
}
