package com.genai.controllers;

import com.genai.outputs.MovieList;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;

@RestController
public class AIAgentWithStructuredResponseController {
    private ChatClient chatClient;

    public AIAgentWithStructuredResponseController(ChatClient.Builder builder, ChatMemory memory){

        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .build();
    }

    @GetMapping("/chat_structured_response")
    public MovieList askLLM(String query){
        String systemMessage = """
                Vous êtes un spécialiste dans le domaine du cinema.
                Répondez à la question de l'utilisateur à ce propos.
                """;

        return this.chatClient.prompt()
                .system(systemMessage)
                .user(query)
                .call()
                .entity(MovieList.class);
    }
}
