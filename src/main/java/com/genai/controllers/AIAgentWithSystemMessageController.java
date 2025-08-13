package com.genai.controllers;


import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;


@RestController
public class AIAgentWithSystemMessageController {
    private ChatClient chatClient;

    public AIAgentWithSystemMessageController(ChatClient.Builder builder, ChatMemory memory){

        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .build();
    }

    @GetMapping("/chat_with_system_msg")
    public String askLLM(String query){
        return this.chatClient.prompt()
                .system("Répond toujours en majuscule")
                .user(query)
                .call()
                .content();
    }
}
