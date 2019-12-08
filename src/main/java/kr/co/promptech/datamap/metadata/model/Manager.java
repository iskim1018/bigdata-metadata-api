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
@Table(name="managers")
@ToString
public class Manager {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="manager_id")
    private Set<Platform> platforms;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="contact_point_id")
    private Set<Dataset> datasets;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="contact_point_id")
    private Set<ApiDatum> apiData;
}
