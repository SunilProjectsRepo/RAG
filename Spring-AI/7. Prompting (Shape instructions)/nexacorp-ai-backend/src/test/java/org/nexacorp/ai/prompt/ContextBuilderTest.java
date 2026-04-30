package org.nexacorp.ai.prompt;

import org.junit.jupiter.api.Test;
import org.nexacorp.ai.prompt.model.PromptContext;
import org.nexacorp.ai.prompt.model.SystemInstructions;
import org.nexacorp.ai.retrieval.RetrievalService;
import org.nexacorp.ai.retrieval.model.RetrievalResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContextBuilderTest {
    private static final Logger log = LoggerFactory.getLogger(ContextBuilderTest.class);

    @Autowired
    private RetrievalService retrievalService;

    @Test
    void testContextBuilder(){
        RetrievalResult result = retrievalService.retrieve("What is the work from home policy");
        PromptContext promptContext = new ContextBuilder().build(result);

        log.info("=== Loaded Context ===");
        log.info("\n {}",promptContext.getContextText());

    }
}
