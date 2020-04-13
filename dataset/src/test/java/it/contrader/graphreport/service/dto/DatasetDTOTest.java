package it.contrader.graphreport.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.contrader.graphreport.web.rest.TestUtil;

public class DatasetDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DatasetDTO.class);
        DatasetDTO datasetDTO1 = new DatasetDTO();
        datasetDTO1.setId(1L);
        DatasetDTO datasetDTO2 = new DatasetDTO();
        assertThat(datasetDTO1).isNotEqualTo(datasetDTO2);
        datasetDTO2.setId(datasetDTO1.getId());
        assertThat(datasetDTO1).isEqualTo(datasetDTO2);
        datasetDTO2.setId(2L);
        assertThat(datasetDTO1).isNotEqualTo(datasetDTO2);
        datasetDTO1.setId(null);
        assertThat(datasetDTO1).isNotEqualTo(datasetDTO2);
    }
}
