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
public class WikiSemanticChunkerTest {
    private static final Logger log = LoggerFactory.getLogger(WikiSemanticChunkerTest.class);

    @Autowired
    private IngestionOrchestrator ingestionOrchestrator;

    @Autowired
    private FixedSizeChunkingWithOverlap chunker;

    @Autowired
    private WikiSemanticChunker wikiSemanticChunker;

    @Test
    void testChunker() throws Exception {
        List<IngestedDocument> documents = ingestionOrchestrator.ingestAll();

        // Pick a WILI document
        IngestedDocument wikiDoc = documents.stream()
                .filter(d -> d.getSource().contains("WIKI"))
                .findFirst()
                .orElseThrow();

        log.info("================== FIXED SIZE CHUNKING ===============");
        List<Chunk> fixedChunks = chunker.chunk(wikiDoc, 500);
        printChunks(wikiDoc, fixedChunks);

        log.info("============== WITH OVERLAP (100 chars) ===============");
        List<Chunk> overlapChunks = chunker.chunk(wikiDoc, 500, 100);
        printChunks(wikiDoc, overlapChunks);

        log.info("============== SEMANTIC (WIKI) CHUNKING ===============");
        List<Chunk> semanticWikiChunks = wikiSemanticChunker.chunk(wikiDoc);
        printChunks(wikiDoc, semanticWikiChunks);
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
