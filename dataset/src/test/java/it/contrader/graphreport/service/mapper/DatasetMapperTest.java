package it.contrader.graphreport.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DatasetMapperTest {

    private DatasetMapper datasetMapper;

    @BeforeEach
    public void setUp() {
        datasetMapper = new DatasetMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(datasetMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(datasetMapper.fromId(null)).isNull();
    }
}
