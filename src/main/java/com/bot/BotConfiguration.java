package com.bot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@Configuration
@EnableJpaRepositories(basePackages = "com.bot")
public class BotConfiguration {

    static {
        ApiContextInitializer.init();
    }

    @Bean
    public TelegramBotsApi botsApi(Bot bot) {
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiRequestException ex) {
            throw new IllegalStateException(ex);
        }
        return botsApi;
    }

}
