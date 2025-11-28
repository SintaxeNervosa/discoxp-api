package com.github.sintaxenervosa.discoxp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.github.sintaxenervosa.discoxp.security.DotenvInitializer;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class DiscoxpApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(DiscoxpApplication.class);
    
    public static void main(String[] args) {

            SpringApplication app = new SpringApplication(DiscoxpApplication.class);
            app.addInitializers(new DotenvInitializer());
            
            ConfigurableEnvironment env = app.run(args).getEnvironment();
            logApplicationStartup(env);
    }

    private static void logApplicationStartup(Environment env) {
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        
        String serverPort = env.getProperty("server.port", "8080");
        String contextPath = env.getProperty("server.servlet.context-path", "");
        if (contextPath.isBlank()) {
            contextPath = "/";
        }
        
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.warn("O nome do host não pôde ser determinado, usando `localhost` como fallback");
        }
        
        logger.info(
            "\n----------------------------------------------------------\n\t" +
            "Aplicação {}!\n\t" +
            "URL de acesso: \t{}://localhost:{}{}\n\t" +
            "URL externa: \t{}://{}:{}{}\n\t" +
            "🥳Está rodando com Sucesso🙌!\n----------------------------------------------------------",
            env.getProperty("spring.application.name", "discoxp"),
            protocol, serverPort, contextPath,
            protocol, hostAddress, serverPort, contextPath
        );
    }
}
