package kr.co.promptech.datamap.metadata.model;

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
@Table(name="centers")
@ToString
public class Center {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String email;
    private String phone;
    private String homepage;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="platform_id")
    private Platform platform;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="creator_id")
    private Set<Dataset> datasets;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="publisher_id")
    private Set<Dataset> publishedDatasets;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="creator_id")
    private Set<ApiDatum> apiData;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="publisher_id")
    private Set<ApiDatum> publishedApiData;
}
