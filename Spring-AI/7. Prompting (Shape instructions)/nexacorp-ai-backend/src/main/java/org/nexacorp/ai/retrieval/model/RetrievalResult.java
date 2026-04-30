package org.nexacorp.ai.retrieval.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nexacorp.ai.chunking.model.Chunk;

import java.util.List;

/* chunks of internal knowledge that are eligible to use it as context. We can also have
status flags, scores, confidence score
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetrievalResult {
    private List<Chunk> chunks;
}
