package utils;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@NoArgsConstructor
public class Telegram_Bot_fast extends TelegramLongPollingBot {

    private String userName;
    private String token;
    @Setter
    private OnUpdateInterface onUpdateInterface;

    public Telegram_Bot_fast(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    @Override
    public String getBotUsername() {
        return this.userName;
    }

    @Override
    public String getBotToken() {
        return this.token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        onUpdateInterface.onUpdateReceived(update,this);
    }
}
