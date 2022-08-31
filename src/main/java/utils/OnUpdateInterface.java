package utils;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface OnUpdateInterface {

    void onUpdateReceived(Update update, TelegramLongPollingBot telegramLongPollingBot);
    void beforeCreateObject(TelegramLongPollingBot telegramLongPollingBot);

}
