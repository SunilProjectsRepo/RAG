package org.nexacorp.ai.prompt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
/*
    Retrieval - Finding information
    Context  - Provides Boundaries
    Grounding - Enforces behavior
 */

@Data
@AllArgsConstructor
public class ChatPrompt {
    // How the model will behave
    private final SystemInstructions systemInstructions;
    // What knowledge does the model allowed to use
    private final PromptContext context;
    // How the model should work with the context
    private final String groundingRule;
}
