package kr.co.promptech.datamap.metadata.dto.mapper;

import kr.co.promptech.datamap.metadata.dto.model.*;
import kr.co.promptech.datamap.metadata.model.Platform;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PlatformMapper {
    public static PlatformDto toPlatformDto(Platform platform) {
        PlatformDto platformDto = new PlatformDto();
        platformDto
                .setId(platform.getId())
                .setTitle(platform.getTitle());

        if (platform.getDatasets() != null) {
            Set<DatasetDto> datasetDtoSet = new HashSet<>();

            platform.getDatasets().forEach(dataset -> {
                DatasetDto datasetDto = new ModelMapper().map(dataset, DatasetDto.class);

                if (dataset.getContactPoint() != null) {
                    datasetDto.setContactPoint(new ModelMapper().map(dataset.getContactPoint(), ManagerDto.class));
                }

                if (dataset.getCreator() != null) {
                    datasetDto.setCreator(
                            new ModelMapper().map(dataset.getCreator(), CenterDto.class)
                    );
                }

                if (dataset.getPublisher() != null) {
                    datasetDto.setPublisher(
                            new ModelMapper().map(dataset.getPublisher(), CenterDto.class)
                    );
                }

                Set<String> keywordDtoSet = new HashSet<>();
                dataset.getKeywords().forEach(keyword -> {
                    keywordDtoSet.add(keyword.getKeyword());
                });
                datasetDto.setKeywords(keywordDtoSet);

                Set<FileDatumDto> fileDatumDtoSet = new HashSet<>();
                dataset.getFileData().forEach(fileDatum -> {
                    FileDatumDto fileDatumDto = new ModelMapper().map(fileDatum, FileDatumDto.class);
                    fileDatumDtoSet.add(fileDatumDto);
                });
                datasetDto.setFileData(fileDatumDtoSet);

                datasetDtoSet.add(datasetDto);
            });

            platformDto.setDatasets(datasetDtoSet);
        }

        if (platform.getApiData() != null) {
            Set<ApiDatumDto> apiDatumDtoSet = new HashSet<>();

            platform.getApiData().forEach(apiDatum -> {
                ApiDatumDto apiDatumDto = new ModelMapper().map(apiDatum, ApiDatumDto.class);

                if (apiDatum.getContactPoint() != null) {
                    apiDatumDto.setContactPoint(new ModelMapper().map(apiDatum.getContactPoint(), ManagerDto.class));
                }

                if (apiDatum.getCreator() != null) {
                    apiDatumDto.setCreator(
                            new ModelMapper().map(apiDatum.getCreator(), CenterDto.class)
                    );
                }

                if (apiDatum.getPublisher() != null) {
                    apiDatumDto.setPublisher(
                            new ModelMapper().map(apiDatum.getPublisher(), CenterDto.class)
                    );
                }

                Set<String> keywordDtoSet = new HashSet<>();
                apiDatum.getKeywords().forEach(keyword -> {
                    keywordDtoSet.add(keyword.getKeyword());
                });
                apiDatumDto.setKeywords(keywordDtoSet);

                apiDatumDtoSet.add(apiDatumDto);
            });

            platformDto.setApiData(apiDatumDtoSet);
        }

        return platformDto;
    }
}
