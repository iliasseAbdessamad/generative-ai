package com.genai.controllers;

import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import java.util.List;


@RestController
public class AIAgentWithFewShotPromptController {
    private ChatClient chatClient;

    public AIAgentWithFewShotPromptController(ChatClient.Builder builder, ChatMemory memory){

        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .build();
    }

    @GetMapping("/chat_with_system_msg_and_one_shot_prompt")
    public String askLLM(String query){

        List<Message> examples = List.of(
                new UserMessage("20/10"),
                new AssistantMessage("Le résultat est : 2")
        );

        return this.chatClient.prompt()
                .system("Répond toujours en majuscule")
                .messages(examples)
                .user(query)
                .call()
                .content();
    }
}
