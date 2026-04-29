package org.nexacorp.ai.ingestion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class IngestedDocument {
    private String source; // PDF, Wiki, DB
    private String content;
    private Map<String, Object> metadata;
}
