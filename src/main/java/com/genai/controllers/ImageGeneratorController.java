package com.genai.controllers;


import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class ImageGeneratorController {
    private OpenAiImageModel imageModel;

    public ImageGeneratorController(OpenAiImageModel imageModel){
        this.imageModel = imageModel;
    }

    @GetMapping("/generate_image")
    public String generateImage(String prompt){
        ImageOptions imageOptions = OpenAiImageOptions.builder()
                .quality("hd")
                .model("dall-e-3")
                .build();

        ImagePrompt imgPrompt = new ImagePrompt(prompt, imageOptions);
        String url = this.imageModel.call(imgPrompt).getResult().getOutput().getUrl();

        return url;
    }
}
