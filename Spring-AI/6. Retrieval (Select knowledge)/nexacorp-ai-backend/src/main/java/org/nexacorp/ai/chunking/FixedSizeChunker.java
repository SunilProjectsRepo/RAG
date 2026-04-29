package org.nexacorp.ai.chunking;

import org.nexacorp.ai.chunking.model.Chunk;
import org.nexacorp.ai.ingestion.model.IngestedDocument;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Most Naive Chunking Strategy */

@Service
public class FixedSizeChunker {

    public List<Chunk> chunk(IngestedDocument document, int chunkSize){
        List<Chunk> chunks = new ArrayList<>();

        String content = document.getContent();
        int start = 0, chunkIndex = 0;

        while(start < content.length()){
            int end = Math.min(start + chunkSize, content.length());
            String chunkText = content.substring(start, end);

            Map<String, Object> chunkMetadata = new HashMap<>(document.getMetadata());
            chunkMetadata.put("chunkIndex", chunkIndex);

            chunks.add(new Chunk(
                    document.getSource(),
                    chunkText,
                    chunkMetadata,
                    chunkIndex
            ));

            chunkIndex++;
            start = end;
        }
        return chunks;
    }
}
