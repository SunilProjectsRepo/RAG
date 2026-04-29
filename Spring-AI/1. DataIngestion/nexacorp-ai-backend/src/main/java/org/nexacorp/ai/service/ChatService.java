package org.nexacorp.ai.service;

import lombok.RequiredArgsConstructor;
import org.nexacorp.ai.dto.ChatRequest;
import org.nexacorp.ai.dto.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatClient chatClient;

    public ChatResponse chat(ChatRequest request){
        String aiResponse =  chatClient.prompt()
                .user(request.getMessage())
                .call().content();
        return new ChatResponse(aiResponse);
    }

}
