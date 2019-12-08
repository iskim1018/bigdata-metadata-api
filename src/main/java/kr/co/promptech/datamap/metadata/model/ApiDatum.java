package kr.co.promptech.datamap.metadata.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name="api_data")
public class ApiDatum {
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
    private String landingPage;
    private String language;
    private String rights;
    private String version;
    private String versionDescription;
    private String endpointUrl;

    @Column(columnDefinition = "TEXT")
    @Type(type = "text")
    private String endpointDesc;

    private String issued;
    private String modified;

    @Column(columnDefinition = "TEXT")
    @Type(type = "text")
    private String priceInfo;

    @Column(columnDefinition = "TEXT")
    @Type(type = "text")
    private String qualityInfo;

    @Column(columnDefinition = "TEXT")
    @Type(type = "text")
    private String servesDataset;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL}, mappedBy = "apiDatum", orphanRemoval=true)
    @Where(clause = "data_type = 'dataservice'")
    private Set<Keyword> keywords;
}
