package bots_utils;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class KVS_Bot_ButtonUtils {

    public static void getElementByGuidAPI(TelegramLongPollingBot telegramLongPollingBot) {
        SendMessage message = new SendMessage();
        message.setChatId("552286993");
        message.setText("¬ведите GUID?");
        try {
            telegramLongPollingBot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void getDataFrom1C(String text) {

    }

}
