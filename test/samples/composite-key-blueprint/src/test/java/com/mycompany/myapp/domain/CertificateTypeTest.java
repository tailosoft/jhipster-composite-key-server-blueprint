package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class CertificateTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CertificateType.class);
        CertificateType certificateType1 = new CertificateType();
        certificateType1.setId(1L);
        CertificateType certificateType2 = new CertificateType();
        certificateType2.setId(1L);
        assertThat(certificateType1).isEqualTo(certificateType2);
        certificateType2.setId(2L);
        assertThat(certificateType1).isNotEqualTo(certificateType2);
        certificateType1.setId(null);
        assertThat(certificateType1).isNotEqualTo(certificateType2);
    }
}
