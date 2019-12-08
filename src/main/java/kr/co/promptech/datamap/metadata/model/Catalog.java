package kr.co.promptech.datamap.metadata.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name="catalogs")
public class Catalog {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    @Type(type = "text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="platform_id")
    private Platform platform;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="catalog_id")
    private Set<Dataset> datasets;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="catalog_id")
    private Set<ApiDatum> apiData;
}
