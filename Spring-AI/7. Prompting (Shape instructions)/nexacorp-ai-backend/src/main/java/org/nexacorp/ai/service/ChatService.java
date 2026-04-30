package org.nexacorp.ai.service;

import lombok.RequiredArgsConstructor;
import org.nexacorp.ai.chunking.model.Chunk;
import org.nexacorp.ai.dto.ChatRequest;
import org.nexacorp.ai.dto.ChatResponse;
import org.nexacorp.ai.prompt.PromptOrchestrator;
import org.nexacorp.ai.prompt.model.ChatPrompt;
import org.nexacorp.ai.retrieval.RetrievalService;
import org.nexacorp.ai.retrieval.model.RetrievalResult;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatClient chatClient;

    private final PromptOrchestrator promptOrchestrator;

    public ChatResponse chat(ChatRequest request){
        String userMessage = request.getMessage();

        ChatPrompt chatPrompt = promptOrchestrator.build(userMessage);

        String llmInput = chatPrompt.getGroundingRule()
                + "\n\n"
                + chatPrompt.getContext().getContextText()
                + "\n\nUser Question: \n"
                + userMessage;

        String aiResponse =  chatClient.prompt()
                .system(chatPrompt.getSystemInstructions().getInstructions())
                .user(llmInput)
                .call().content();

        return new ChatResponse(aiResponse);
    }



}
