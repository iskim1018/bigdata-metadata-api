package kr.co.promptech.datamap.metadata.dto.dcat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JacksonXmlRootElement(localName = "landingPage", namespace = "dcat")
public class DcatLandingPage {
    @JacksonXmlProperty(namespace = "rdf", localName = "resource", isAttribute = true)
    private String resource;

    @JacksonXmlText
    private String value;
}