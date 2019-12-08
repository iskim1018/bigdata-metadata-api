package kr.co.promptech.datamap.metadata.dto.dcat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class RdfDate {
    @JacksonXmlProperty(namespace = "rdf", localName = "datatype", isAttribute = true)
    private String datatype;

    @JacksonXmlText
    private String value;
}
