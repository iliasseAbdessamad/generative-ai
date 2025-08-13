# 🤖 Generative AI (Using Spring Boot, and Spring AI)

The goal of this maven project is to leverage the use and integration of 
LLMs (Large Language Models) in Spring Boot–based projects, 
through the Spring AI module.

## 📦 Project dependencies : 

- java (jdk 21)
- Maven
- Spring Boot (version 3.5.3)
- Spring Web
- OpenAi (Spring AI)
- Ollama (Spring AI)

(Other dependencies in pom.xml (Spring Data JPA, H2, ...) are not yet used at this stage of the application, and 
are conserved for the upcoming new version)

## 🧠 Project's Overview :

Each Controller in `com.genai.controller` is a REST controller and 
serves as a gateway between the application and an LLM (Large Language Model) 
via an object provided by Spring AI.

Also each controller between those controllers allows us either to communicate with a specific 
type of LLM that can be a **Text-To-Text model**, **A MultiModal Model** or an **Image Generator Model**, 
or to leverage configurations for the same type of an LLM. 
For example : `AIAgentController` and `AIAgentWithMemoryController` are both **Text-To-Text models** 
but `AIAgentWithMemoryController` allows us to communicate with an LLM that can memorize a determined
number of the previous prompts.

**Bellow is a simple description of each controller**

<table>
    <thead>
        <tr>
            <th>Name</th>
            <th>Allows us to Communicate with a</th>
            <th>Functionalities</th>
        </tr>
    </thead>
    <thead>
        <tr>
            <td>AIAgentController</td>
            <th>Text-To-Text model</th>
            <th>
                Can communicate with the end user (just like chat-gpt), but is a **state less model** 
                which means, it can not memorise the previous prompts or outputs
            </th>
        </tr>
        <tr>
            <td>AIAgentWithMemoryController</td>
            <th>Text-To-Text model</th>
            <th>
                Can communicate with the end user just (just like the LLM provided by the AIController), 
                but it can also memorise a specific number of previous prompts/outputs
            </th>
        </tr>
        <tr>
            <td>AIAgentWithSystemMessageController</td>
            <th>Text-To-Text model</th>
            <th>
                Just like the previous one (it memorise the previous prompts/outputs), 
                and is guided (or attached to a specific domaine), for this purpose we must 
                add a **system message** as a part of the prompt. 
            </th>
        </tr>
        <tr>
            <td>AIAgentWithFewShotPromptController</td>
            <th>Text-To-Text model</th>
            <th>
                Here we added an example which as a part of the prompt so that the LLM can 
                be more specific.
            </th>
        </tr>
        <tr>
            <td>AIAgentWithStructuredResponseController</td>
            <th>Text-To-Text model</th>
            <th>
                The LLM respond with a structured data, this functionality is so useful, we can 
                insert data in a database just after retrieving it from the LLM, or ...
            </th>
        </tr>
        <tr>
            <td>AIAgentStreamedResponseController</td>
            <th>Text-To-Text model</th>
            <th>
                Instead of responding all at once (after it has finished thinking), 
                with this configuration, the LLM can respond in a streaming manner, 
                generating its reply while still 
               "thinking" about what comes next—just like a human would 
                (This means that your application does not remain blocked waiting for the complete response, 
                but receives data progressively.)
            </th>
        </tr>
        <tr>
            <td>MultiModalController</td>
            <th>Multi-Modal model</th>
            <th>
                Can take **image + text**, and generate a text, this allows the end user 
                to query a description of the given image ...
            </th>
        </tr>
        <tr>
            <td>ImageGeneratorController</td>
            <th>Image-Generator model</th>
            <th>
                Allows the end user to generate an image from the given prompt
            </th>
        </tr>
    </thead>
</table>

## 🚀 Endpoints : 

- **GET /chat**  : provided by `AIAgentController`, to interact with an LLM
- **GET /chat_with_memory**  : this action is provided by `AIAgentWithMemoryController`, to interact with an LLM which memorize the previous prompts/outputs
- **GET /chat_structured_response** : provided by `AIAgentWithStructuredController` to interact with an LLM which respond by a structured data
- **GET /chat_with_system_msg** : provided by `AIAgentWithSystemMessageController`, to interact with an LLM which is guided by a **system message**, for mor sophisticated responses (outputs)
- **GET /chat_with_system_msg_and_one_shot_prompt** : provided by `AIAgentWithFewShotPromptController`, an LLM which is guided by a **system message**, and a one example as a part of the prompt, for the best possible responses 
- **GET /describe_img** : provided by `MultiModalController`, to describe a given image (you will find this image in `src/main/resources/images`)
- **POST /ask_about_img** : provided by `MultiModalController`, and allows the end user to post an **image + text** as a prompt, the user can ask anything about the given image
- **Get /generate_image** : provided by `ImageGeneratorController`, to generate an image, based on the prompt

## 🔧 How you can try it :

1 - Clone the repository : 
```
git clone https://github.com/iliasseAbdessamad/generative-ai.git
```

2 - Rename **application.properties.example** to **application.properties**

3 - In **application.properties** replace `YOUR_OpenAI_KEY` by **your OpenAi API KEY**

4 - Run the application

5 - Use **Postman** or a simple browser like **Chrome** to reach the different endpoints 
exp : 
`http://localhos:8888/chat?query=Bonjour`

<br />
<hr/>
<br />

- **🎓 Created by :** Iliasse Abdessamad
- **📚 Subject :** Discovering LLMs (Large Language Models) with Spring AI
- **📅 year :** 2024 - 2025

