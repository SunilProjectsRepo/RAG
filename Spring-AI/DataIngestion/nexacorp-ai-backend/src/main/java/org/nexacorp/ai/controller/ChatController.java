package org.nexacorp.ai.controller;

import lombok.RequiredArgsConstructor;
import org.nexacorp.ai.dto.ChatRequest;
import org.nexacorp.ai.dto.ChatResponse;
import org.nexacorp.ai.service.ChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request){
        return chatService.chat(request);
    }

}
