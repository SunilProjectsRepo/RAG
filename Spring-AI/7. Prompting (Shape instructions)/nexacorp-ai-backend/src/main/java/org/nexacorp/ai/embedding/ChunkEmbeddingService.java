package org.nexacorp.ai.embedding;

import lombok.RequiredArgsConstructor;
import org.nexacorp.ai.chunking.model.Chunk;
import org.nexacorp.ai.embedding.model.EmbeddedChunk;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChunkEmbeddingService {

    private final EmbeddingModel embeddingModel;
    public EmbeddedChunk embed(Chunk chunk){
        float[] vector = embeddingModel.embed(chunk.getContent());
        return new EmbeddedChunk(chunk, vector);
    }
}
