package com.genai.controllers;


import com.genai.outputs.Cin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class MultiModalController {
    private ChatClient chatClient;

    @Value("classpath:/images/cin.png")
    private Resource image;

    public MultiModalController(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    @GetMapping("/describe_img")
    public Cin describeImage(){
        return this.chatClient.prompt()
                .system("Donnez une description de l'image fournie")
                .user(u ->
                        u.text("Décrire cette image")
                                .media(MediaType.IMAGE_PNG, this.image)
                )
                .call()
                .entity(Cin.class);
    }

    @PostMapping(value="/ask_about_img", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String askAboutImage(@RequestParam(name = "file") MultipartFile file, String question) throws Exception{
        byte[] bytes = file.getBytes();

        return this.chatClient.prompt()
                .system("Répondez à la question de l'utilisateur à propos de l'image fournie")
                .user(u ->
                        u.text(question)
                                .media(MediaType.IMAGE_PNG, new ByteArrayResource(bytes))
                )
                .call().content();
    }
}
