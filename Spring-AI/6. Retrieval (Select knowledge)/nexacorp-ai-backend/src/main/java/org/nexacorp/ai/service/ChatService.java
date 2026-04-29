package org.nexacorp.ai.service;

import lombok.RequiredArgsConstructor;
import org.nexacorp.ai.chunking.model.Chunk;
import org.nexacorp.ai.dto.ChatRequest;
import org.nexacorp.ai.dto.ChatResponse;
import org.nexacorp.ai.retrieval.RetrievalService;
import org.nexacorp.ai.retrieval.model.RetrievalResult;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatClient chatClient;
    private final RetrievalService retrievalService;

    public ChatResponse chat(ChatRequest request){
        String userMessage = request.getMessage();

        RetrievalResult retrievalResult = retrievalService.retrieve(userMessage);
        String context = buildContext(retrievalResult);

        String aiResponse =  chatClient.prompt()
                .system(context)
                .user(request.getMessage())
                .call().content();
        return new ChatResponse(aiResponse);
    }

    private String buildContext(RetrievalResult retrievalResult){
        StringBuilder contextBuilder = new StringBuilder();
        for(Chunk chunk : retrievalResult.getChunks()){
            contextBuilder.append(chunk.getContent()).append("\n\n");
        }

        return contextBuilder.toString();
    }

}
