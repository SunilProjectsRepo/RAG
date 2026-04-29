package org.nexacorp.ai.embedding.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.nexacorp.ai.chunking.model.Chunk;

@Getter
@AllArgsConstructor
public class EmbeddedChunk {
    private final Chunk chunk;
    private final float[] vector;
}
