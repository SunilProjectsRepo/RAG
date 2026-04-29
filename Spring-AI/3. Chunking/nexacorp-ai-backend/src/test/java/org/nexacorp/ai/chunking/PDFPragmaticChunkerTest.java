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
public class PDFPragmaticChunkerTest {

    private static final Logger log = LoggerFactory.getLogger(WikiSemanticChunkerTest.class);

    @Autowired
    private IngestionOrchestrator ingestionOrchestrator;

    @Autowired
    private PDFPragmaticChunker pdfPragmaticChunker;

    @Test
    void chunk_pdf_with_pragamatic_chunker() throws Exception{
        List<IngestedDocument> documents = ingestionOrchestrator.ingestAll();

        IngestedDocument pdfDoc = documents.stream()
                .filter(d -> d.getSource().equals("PDF"))
                .findFirst()
                .orElseThrow();

        List<Chunk> chunks = pdfPragmaticChunker.chunk(pdfDoc);
        log.info("PDF Source: {}", pdfDoc.getSource());
        log.info("Total chunks: {}", chunks.size());

        for(Chunk chunk: chunks){
            log.info("---- Chunk {} ----", chunk.getChunkIndex());
            log.info("Metadata: {}", chunk.getMetadata());
            log.info(chunk.getContent());
        }

    }
}
