package org.nexacorp.ai.ingestion.pdf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PDFIngestionServiceTest {

    @Autowired
    PDFIngestionService pdfIngestionService;

    @Test
    void ingestPDFs_forLearnings() throws Exception {
        pdfIngestionService.ingestPDFs();
    }
}
