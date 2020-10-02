package com.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Component
public class Bot extends TelegramLongPollingBot {

    private final BotCRUD botCRUD;
    private final String name;
    private final String token;
    private Map<Integer, String> curses = new HashMap<>();

    @Autowired
    public Bot(BotCRUD botCRUD) {
        this.botCRUD = botCRUD;

        name = getBotUsername();
        token = getBotToken();
        curses.put(0, "Ты пидор");
        curses.put(1, "Ублюдок, мать твою");
        curses.put(2, "А ну иди сюда, говно собачье");
        curses.put(3, "Решил ко мне лезть?");
        curses.put(4, "Ты, засранец вонючий, мать твою, а?");
        curses.put(5, "Ну иди сюда, попробуй меня трахнуть, я тебя сам трахну");
        curses.put(6, "Онанист чертов");
        curses.put(7, "Будь ты проклят");
        curses.put(8, "Иди идиот, трахать тебя и всю семью");
        curses.put(9, "Говно собачье");
        curses.put(10, "Жлоб вонючий");
        curses.put(11, "Дерьмо");
        curses.put(12, "Падла");
        curses.put(13, "Иди сюда, мерзавец");
        curses.put(14, "Негодяй");
        curses.put(15, "Гад");
        curses.put(16, "Иди сюда, ты — говно");
        curses.put(17, "Жопа");
        curses.put(18, "Пидор");
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();

        Optional<Chat> chatData = botCRUD.findById(chatId);
        String currentCurse = "Ты пидор";
        String reverseCurse = reverseCurse(message);

        if (reverseCurse == null) {
            if (chatData.isPresent()) {
                Chat chatEntity = chatData.get();
                Integer seq = chatEntity.getSeq();
                currentCurse = curses.get(seq);
                send(chatId, currentCurse);
                chatEntity.setSeq(++seq % curses.size());
                botCRUD.save(chatEntity);
            } else {
                Chat chat = new Chat();
                send(chatId, curses.get(0));
                chat.setId(chatId);
                chat.setSeq(1);
                botCRUD.save(chat);
            }
        } else {
            send(update.getMessage().getChatId().toString(), reverseCurse);
        }

    }

    private String reverseCurse(String message) {
        String checkedMsg = null;
        message = message.replaceAll(",", "");
        if (message.toLowerCase().equals("нет ты") ||
                message.toLowerCase().equals("ты пидор") ||
                message.toLowerCase().equals("нет ты пидор")) {
            checkedMsg = "Нет ты пидор";
        }
        return checkedMsg;
    }

    @Override
    public String getBotUsername() {
        return "MaggieQTestBot+";
    }

    @Override
    public String getBotToken() {
        return "1086101621:AAGI8uIpfwJXUaXeZUaozlH3jZ1Az5mSdbI";
    }

    public synchronized void send(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new IllegalStateException(e);
        }
    }


}
