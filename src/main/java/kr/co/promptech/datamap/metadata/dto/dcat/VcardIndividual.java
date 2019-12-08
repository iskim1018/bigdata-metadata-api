package kr.co.promptech.datamap.metadata.dto.dcat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class VcardIndividual {
    @JacksonXmlProperty(namespace = "vcard", localName = "fn")
    private String name;

    @JacksonXmlProperty(namespace = "vcard", localName = "hasEmail")
    private RdfResource email;

    @JacksonXmlProperty(namespace = "vcard", localName = "hasTelephone")
    private RdfResource phone;
}
