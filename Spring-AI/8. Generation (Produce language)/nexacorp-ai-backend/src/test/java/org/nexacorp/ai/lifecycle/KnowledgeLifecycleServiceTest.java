package org.nexacorp.ai.lifecycle;

import org.junit.jupiter.api.Test;
import org.nexacorp.ai.lifecycle.model.KnowledgeRequest;
import org.nexacorp.ai.lifecycle.model.SourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KnowledgeLifecycleServiceTest {
    private static final Logger log = LoggerFactory.getLogger(KnowledgeIdentityTest.class);

    @Autowired
    KnowledgeLifecycleService knowledgeLifecycleService;

    @Test
    void testIngestPDF() throws Exception{
        KnowledgeRequest request = KnowledgeRequest.builder()
                .sourceType(SourceType.PDF)
                .name("HR_Leave_Policy.pdf")
                .build();
        knowledgeLifecycleService.ingest(request);
    }

    @Test
    void testIngestAll() throws Exception{
        knowledgeLifecycleService.ingestAll();
    }
    @Test
    void testDeleteAll(){
        knowledgeLifecycleService.deleteAll();
    }
}
