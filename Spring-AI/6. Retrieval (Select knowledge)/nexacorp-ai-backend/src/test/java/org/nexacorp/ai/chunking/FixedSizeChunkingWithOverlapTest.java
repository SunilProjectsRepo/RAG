package org.nexacorp.ai.chunking;

import org.junit.jupiter.api.Test;
import org.nexacorp.ai.chunking.model.Chunk;
import org.nexacorp.ai.ingestion.IngestionOrchestrator;
import org.nexacorp.ai.ingestion.model.IngestedDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FixedSizeChunkingWithOverlapTest {
    private static final Logger log = LoggerFactory.getLogger(FixedSizeChunkerTest.class);

    @Autowired
    private IngestionOrchestrator ingestionOrchestrator;

    @Autowired
    private FixedSizeChunkingWithOverlap chunker;

    @Test
    void chunkerTest() throws Exception{
        List<IngestedDocument> documents = ingestionOrchestrator.ingestAll();

        IngestedDocument document = documents.get(0);

        log.info("============== NO OVERLAP ===============");
        List<Chunk> chunks = chunker.chunk(document, 500);
        printChunks(document, chunks);

        log.info("============== WITH OVERLAP (100 chars) ===============");
        List<Chunk> overlapChunks = chunker.chunk(document, 500, 100);
        printChunks(document, overlapChunks);
    }

    private static void printChunks(IngestedDocument document, List<Chunk> chunks) {
        log.info("Source: {}", document.getSource());
        log.info("Original length: {}", document.getContent().length());
        log.info("Total chunk: {}", chunks.size());

        for(Chunk chunk: chunks){
            log.info("---  Chunk {} ---", chunk.getChunkIndex());
            log.info(chunk.getContent());
        }
    }
}
