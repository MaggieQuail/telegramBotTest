package com.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class BotApplication {

    public static void main(String[] args) {
		ApiContextInitializer.init();
        SpringApplication.run(BotApplication.class, args);
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            Bot myBot = new Bot();
            System.out.println("MAPE START " + myBot.getBotToken() + "  " + myBot.getBotUsername());
            telegramBotsApi.registerBot(myBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

}
