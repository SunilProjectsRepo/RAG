package org.nexacorp.ai.chunking;

import org.nexacorp.ai.chunking.model.Chunk;
import org.nexacorp.ai.ingestion.model.IngestedDocument;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class DatabaseChunker {

    public List<Chunk> chunk(IngestedDocument document){
        return List.of(
                new Chunk(
                        document.getSource(),
                        document.getContent(),
                        document.getMetadata(),
                        0
                )
        );
    }

}
