package com.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.logging.Level;

@Component
public class Bot extends TelegramLongPollingBot {

    private final String name;
    private final String token;

    public Bot(){
        name = getBotUsername();
        token = getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("MAPE onUpdateReceived" + update);
        String message = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), message);
    }

    @Override
    public String getBotUsername() {
        return "MaggieQTestBot+";
    }

    @Override
    public String getBotToken() {
        return "1086101621:AAGI8uIpfwJXUaXeZUaozlH3jZ1Az5mSdbI";
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
       // sendMessage.setText(s);
        sendMessage.setText("Ты пидр");
        try {
            System.out.println("MAPE sendMessage chat = " + chatId + "msg = " + s);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            //bad practice
        }
    }
}
