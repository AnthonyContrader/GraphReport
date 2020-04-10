package it.contrader.graphreport.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.contrader.graphreport.web.rest.TestUtil;

public class DatasetTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dataset.class);
        Dataset dataset1 = new Dataset();
        dataset1.setId(1L);
        Dataset dataset2 = new Dataset();
        dataset2.setId(dataset1.getId());
        assertThat(dataset1).isEqualTo(dataset2);
        dataset2.setId(2L);
        assertThat(dataset1).isNotEqualTo(dataset2);
        dataset1.setId(null);
        assertThat(dataset1).isNotEqualTo(dataset2);
    }
}
