package kr.co.promptech.datamap.metadata.service;

import kr.co.promptech.datamap.metadata.model.Platform;
import kr.co.promptech.datamap.metadata.repository.PlatformRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PlatformService {
    @Autowired
    private PlatformRepository platformRepository;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public Platform find(Long id) {
        return platformRepository.findById(id).orElse(null);
    }

    public Platform findWithMetadata(Long id) {
        return platformRepository.findWithMetadata(id);
    }

    public Platform findByToken(String token) {
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            token = token.replace(TOKEN_PREFIX, "");

            return platformRepository.findByToken(token);
        }

        return null;
    }

    public void clearMetadata(Platform platform) {
        platform.getDatasets().clear();
        platform.getApiData().clear();

        platformRepository.save(platform);
    }
}
