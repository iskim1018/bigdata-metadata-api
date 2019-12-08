package kr.co.promptech.datamap.metadata.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name="keywords")
public class Keyword {
    public static final String TYPE_DATASET = "dataset";
    public static final String TYPE_API = "dataservice";

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    private String dataType;

    @ManyToOne
    private Dataset dataset;

    @ManyToOne
    private ApiDatum apiDatum;
}
