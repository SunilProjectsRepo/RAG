package com.nexacorp.ai.lifecycle;

import com.nexacorp.ai.chunking.ChunkingOrchestrator;
import com.nexacorp.ai.chunking.model.Chunk;
import com.nexacorp.ai.ingestion.IngestionOrchestrator;
import com.nexacorp.ai.ingestion.model.IngestedDocument;
import com.nexacorp.ai.lifecycle.model.KnowledgeRequest;
import com.nexacorp.ai.vectorstore.ChunkVectorStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KnowledgeLifecycleService {
    private final ChunkVectorStoreService vectorStoreService;
    private final IngestionOrchestrator ingestionOrchestrator;
    private final ChunkingOrchestrator chunkingOrchestrator;

    public void ingest(KnowledgeRequest request) throws Exception {
        String identity = KnowledgeIdentity.from(request);
        vectorStoreService.deleteByIdentity(identity);

        List<IngestedDocument> documents = ingestionOrchestrator.ingest(request);
        List<Chunk> chunksToStore = new ArrayList<>();
        for (IngestedDocument document : documents) {
            List<Chunk> chunks = chunkingOrchestrator.chunk(document);
            chunksToStore.addAll(chunks);
        }
        vectorStoreService.store(chunksToStore);
    }

    public void delete(KnowledgeRequest request) {
        String identity = KnowledgeIdentity.from(request);
        vectorStoreService.deleteByIdentity(identity);
    }

    public void ingestAll() throws Exception {
        deleteAll();

        List<IngestedDocument> documents = ingestionOrchestrator.ingestAll();
        List<Chunk> chunksToStore = new ArrayList<>();
        for (IngestedDocument document : documents) {
            List<Chunk> chunks = chunkingOrchestrator.chunk(document);
            chunksToStore.addAll(chunks);
        }
        vectorStoreService.store(chunksToStore);
    }

    public void deleteAll() {
        vectorStoreService.deleteAll();
    }

}
