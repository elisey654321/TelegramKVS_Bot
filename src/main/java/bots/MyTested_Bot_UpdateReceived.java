package bots;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import utils.OnUpdateInterface;

public class MyTested_Bot_UpdateReceived implements OnUpdateInterface {
    @Override
    public void onUpdateReceived(Update update, TelegramLongPollingBot telegramLongPollingBot) {
        try {
            SendMessage sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), String.valueOf(update.getMessage().getChatId()));
            telegramLongPollingBot.execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
