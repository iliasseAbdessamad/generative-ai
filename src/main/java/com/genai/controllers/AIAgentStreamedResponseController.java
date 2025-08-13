package com.genai.controllers;


import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import reactor.core.publisher.Flux;

@RestController
public class AIAgentStreamedResponseController {
    private ChatClient chatClient;

    public AIAgentStreamedResponseController(ChatClient.Builder builder, ChatMemory memory){

        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .build();
    }

    @GetMapping(value = "/chat_stream",  produces = MediaType.TEXT_PLAIN_VALUE)
    public Flux<String> askLLM(String query){
        return this.chatClient.prompt()
                .user(query)
                .stream()
                .content();
    }
}
