package com.genai.controllers;


import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.client.ChatClient;



@RestController
public class AIAgentController {

    private ChatClient chatClient;

    public AIAgentController(ChatClient.Builder builder){
        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @GetMapping("/chat")
    public String askLLM(String query){
        return this.chatClient.prompt()
                .user(query)
                .call()
                .content();
    }
}
