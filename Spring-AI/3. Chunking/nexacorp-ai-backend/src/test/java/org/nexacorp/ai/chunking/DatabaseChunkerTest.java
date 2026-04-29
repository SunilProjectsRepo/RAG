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
public class DatabaseChunkerTest {

    private static final Logger log = LoggerFactory.getLogger(DatabaseChunkerTest.class);

    @Autowired
    private IngestionOrchestrator ingestionOrchestrator;

    @Autowired
    private DatabaseChunker databaseChunker;

    @Test
    void testDatabaseChunker() throws Exception{
        List<IngestedDocument> documents = ingestionOrchestrator.ingestAll();

        List<IngestedDocument> dbDocuments = documents.stream()
                .filter(doc -> "DB".equals(doc.getSource()))
                .toList();

        for(IngestedDocument dbDoc : dbDocuments){
            List<Chunk> chunks = databaseChunker.chunk(dbDoc);
            Chunk chunk = chunks.get(0);

            log.info("==== DB Chunk ====");
            log.info("Source         : {}", chunk.getSource());
            log.info("Chunk Index    : {}", chunk.getChunkIndex());
            log.info("Metadata       : {}", chunk.getMetadata());
            log.info("Content        : {}", chunk.getContent());

        }
    }
}
