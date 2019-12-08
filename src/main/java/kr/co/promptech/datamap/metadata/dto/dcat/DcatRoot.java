package kr.co.promptech.datamap.metadata.dto.dcat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JacksonXmlRootElement(namespace = "rdf", localName = "RDF")
public class DcatRoot {
    @JacksonXmlProperty(namespace = "dct", localName = "Dataset")
    private List<DcatDataset> datasets;

    @JacksonXmlProperty(namespace = "dct", localName = "DataService")
    private List<DcatDataservice> dataservices;
}