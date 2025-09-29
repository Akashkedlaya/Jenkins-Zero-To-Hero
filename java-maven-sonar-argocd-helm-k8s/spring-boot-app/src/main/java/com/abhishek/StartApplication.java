package com.abhishek;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Entry point of the Spring Boot Application.
 * Demonstrates layered architecture (Controller + Service),
 * logging, and externalized configuration.
 */
@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}

// ==================== Controller ====================

@Slf4j
@Controller
@RequiredArgsConstructor
class HomeController {

    private final MessageService messageService;

    @GetMapping("/")
    public String index(Model model) {
        log.info("Serving home page...");

        model.addAttribute("title", messageService.getTitleMessage());
        model.addAttribute("msg", messageService.getBodyMessage());

        return "index"; // thymeleaf template (index.html)
    }
}

// ==================== Service ====================

@Service
class MessageService {

    // You could also use @Value("${app.message.title}") to load from application.properties
    public String getTitleMessage() {
        return "✅ Successfully built a Spring Boot application using Maven";
    }

    public String getBodyMessage() {
        return "🚀 This application is deployed to Kubernetes using Argo CD";
    }
}
