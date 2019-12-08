package kr.co.promptech.datamap.metadata.dto.dcat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class DcatContactPoint {
    @NoArgsConstructor
    @Getter
    @ToString
    public static class LandingPage {
        @JacksonXmlProperty(namespace = "rdf", localName = "resource", isAttribute = true)
        private String resource;
    }

}
