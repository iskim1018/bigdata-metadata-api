package kr.co.promptech.datamap.metadata.controller.client;

import kr.co.promptech.datamap.metadata.dto.mapper.PlatformMapper;
import kr.co.promptech.datamap.metadata.dto.model.PlatformDto;
import kr.co.promptech.datamap.metadata.model.Platform;
import kr.co.promptech.datamap.metadata.service.PlatformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Slf4j
@Controller
@RequestMapping("/client")
public class ClientController {
    private static final String TOKEN = "test-token";
    private static final String API_URL = "http://localhost:8080/api/metadata";

    @Autowired
    private PlatformService platformService;

    @GetMapping("/initTest")
    @ResponseStatus(value = HttpStatus.OK)
    public void test() {
        Platform platform = platformService.findByToken(String.format("Bearer %s", TOKEN));
        platform = platformService.findWithMetadata(platform.getId());

        PlatformDto platformDto = PlatformMapper.toPlatformDto(platform);

        SpringTemplateEngine templateEngine = springTemplateEngine();
        Context ctx = new Context();
        ctx.setVariable("platform", platformDto);
        final String dcatResult = templateEngine.process("dcat/index", ctx);
        log.info("generated DCAT ================================================");
        log.info(dcatResult);

        final String url = String.format("%s/%s", API_URL, "init");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", TOKEN));
        HttpEntity<String> entity = new HttpEntity<>(dcatResult, headers);
        String response = restTemplate.postForObject(url, entity, String.class);

        log.info("API response ================================================");
        log.info(response);

    }

    private SpringTemplateEngine springTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".xml");
        templateResolver.setTemplateMode("XML");

        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;
    }
}
