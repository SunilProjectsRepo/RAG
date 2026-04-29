package org.nexacorp.ai.vectorstore;

import org.junit.jupiter.api.Test;
import org.nexacorp.ai.chunking.ChunkingOrchestrator;
import org.nexacorp.ai.chunking.ChunkingOrchestratorTest;
import org.nexacorp.ai.chunking.model.Chunk;
import org.nexacorp.ai.ingestion.IngestionOrchestrator;
import org.nexacorp.ai.ingestion.model.IngestedDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ChunkVectorStoreServiceTest {
    private static final Logger log = LoggerFactory.getLogger(ChunkVectorStoreServiceTest.class);

    @Autowired
    private IngestionOrchestrator ingestionOrchestrator;

    @Autowired
    private ChunkingOrchestrator chunkingOrchestrator;

    @Autowired
    private ChunkVectorStoreService chunkVectorStoreService;

    @Test
    void testVectorStore() throws Exception {
        List<IngestedDocument> documents = ingestionOrchestrator.ingestAll();

        List<Chunk> chunksToStore = new ArrayList<>();

        for(IngestedDocument document : documents){
            List<Chunk> chunks = chunkingOrchestrator.chunk(document);
            chunksToStore.addAll(chunks);
        }

        chunkVectorStoreService.store(chunksToStore);
    }
}
