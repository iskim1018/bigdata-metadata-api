package kr.co.promptech.datamap.metadata.controller.dcat;

import kr.co.promptech.datamap.metadata.dto.mapper.PlatformMapper;
import kr.co.promptech.datamap.metadata.dto.model.PlatformDto;
import kr.co.promptech.datamap.metadata.model.Platform;
import kr.co.promptech.datamap.metadata.service.PlatformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/dcat")
public class DcatController {
    private static final String TAG = "[DcatController] ";

    @Autowired
    private PlatformService platformService;

    @GetMapping(value ="/metadata", produces = MediaType.APPLICATION_XML_VALUE)
    public String index(@RequestHeader(PlatformService.HEADER_STRING) String token,
                              Model model) {
        Platform platform = platformService.findByToken(token);
        if (platform == null) {
            return "errors/401.xml";
        }

        platform = platformService.findWithMetadata(platform.getId());
        PlatformDto platformDto = PlatformMapper.toPlatformDto(platform);

        model.addAttribute("platform", platformDto);

        return "dcat/index.xml";
    }
}
