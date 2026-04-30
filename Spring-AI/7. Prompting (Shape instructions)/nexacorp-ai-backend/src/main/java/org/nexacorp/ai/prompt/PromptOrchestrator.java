package org.nexacorp.ai.prompt;

import lombok.RequiredArgsConstructor;
import org.nexacorp.ai.prompt.model.ChatPrompt;
import org.nexacorp.ai.prompt.model.PromptContext;
import org.nexacorp.ai.prompt.model.SystemInstructions;
import org.nexacorp.ai.retrieval.RetrievalService;
import org.nexacorp.ai.retrieval.model.RetrievalResult;
import org.springframework.stereotype.Service;

/*
 It includes all PROMPT constructions -
    - Retrieval
    - Context construction
    - grounding rules
    - system behavior
*/
@Service
@RequiredArgsConstructor
public class PromptOrchestrator {
    private final RetrievalService retrievalService;
    private final ContextBuilder contextBuilder = new ContextBuilder();
    private final SystemPromptLoader systemPromptLoader = new SystemPromptLoader();
    private final GroundingPolicy groundingPolicy = new GroundingPolicy();
    public ChatPrompt build(String question){
        RetrievalResult retrievalResult = retrievalService.retrieve(question);

        PromptContext promptContext = contextBuilder.build(retrievalResult);

        String rule = groundingPolicy.groundingRules(promptContext);

        SystemInstructions systemInstructions = systemPromptLoader.load();

        return new ChatPrompt(systemInstructions, promptContext, rule);

    }
}
