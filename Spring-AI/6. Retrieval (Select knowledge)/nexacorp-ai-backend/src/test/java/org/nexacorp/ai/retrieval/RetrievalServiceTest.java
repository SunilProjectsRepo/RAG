package org.nexacorp.ai.retrieval;

import org.junit.jupiter.api.Test;
import org.nexacorp.ai.chunking.ChunkingOrchestratorTest;
import org.nexacorp.ai.chunking.model.Chunk;
import org.nexacorp.ai.retrieval.model.RetrievalResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RetrievalServiceTest {

    private static final Logger log = LoggerFactory.getLogger(RetrievalServiceTest.class);

    @Autowired
    private RetrievalService retrievalService;

    @Test
    void retrieve_test(){
        RetrievalResult result = retrievalService.retrieve(("NexaCorp's leave policy"));

        log.info("Retrieval result - chunks found : {}", result.getChunks().size());
        for(Chunk chunk: result.getChunks()){
            log.info("Metadata      : {}", chunk.getMetadata());
            log.info("Content       : {}", chunk.getContent());
        }
    }
}
