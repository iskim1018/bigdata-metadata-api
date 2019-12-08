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
public class FoafOrganization {
    private RdfResource homepage;
    private String name;

    @JacksonXmlProperty(namespace = "foaf", localName = "mbox")
    private String email;

    private String phone;
}
