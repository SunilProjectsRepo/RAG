package org.nexacorp.ai.chunking;

import lombok.RequiredArgsConstructor;
import org.nexacorp.ai.chunking.model.Chunk;
import org.nexacorp.ai.ingestion.model.IngestedDocument;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChunkingOrchestrator {
    private final WikiSemanticChunker wikiSemanticChunker;
    private final PDFPragmaticChunker pdfPragmaticChunker;
    private final DatabaseChunker databaseChunker;

    public List<Chunk>  chunk(IngestedDocument document) throws Exception{
        return switch(document.getSource()) {
            case "WIKI" -> wikiSemanticChunker.chunk(document);
            case "PDF" -> pdfPragmaticChunker.chunk(document);
            case "DB" -> databaseChunker.chunk(document);
            default -> throw new IllegalArgumentException("Unsupported source type: "+document.getSource());
        };
    }
}
