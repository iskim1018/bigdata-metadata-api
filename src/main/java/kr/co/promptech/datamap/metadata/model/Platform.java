package kr.co.promptech.datamap.metadata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name="platforms")
@ToString
public class Platform {
    public static final String CODE_FINANCE = "finance";
    public static final String CODE_ENVIRONMENT = "environment";
    public static final String CODE_CULTURE = "culture";
    public static final String CODE_TRAFFIC = "traffic";
    public static final String CODE_HEALTH = "health";
    public static final String CODE_DISTRIBUTION = "distribution";
    public static final String CODE_COMMUNICATION = "communication";
    public static final String CODE_ECONOMY = "economy";
    public static final String CODE_SMALL_BUSINESS = "small_business";
    public static final String CODE_FOREST = "forest";
    public static final String CODE_TEST = "test";

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String code;
    private String apiUrl;
    private String apiKey;
    private String token;
    private String lastFetched;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manager_id")
    private Manager manager;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="platform_id")
    private Set<Center> centers;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="platform_id")
    private Set<Catalog> catalogs;

    @OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL}, mappedBy = "platform", orphanRemoval=true)
    private Set<Dataset> datasets;

    @OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL}, mappedBy = "platform", orphanRemoval=true)
    private Set<ApiDatum> apiData;
}
