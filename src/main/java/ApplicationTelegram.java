import utils.OnUpdateInterface;
import utils.Telegram_Bot_fast;
import data.TgBot;
import lombok.Setter;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ApplicationTelegram extends Thread {

    @Setter
    private AtomicBoolean works = new AtomicBoolean(true);

    @Override
    public void run() {
        List<TgBot> tgBots = TgBot.getListTgBots();

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            for (TgBot bot : tgBots) {
                Telegram_Bot_fast telegramKVS1C_bot = new Telegram_Bot_fast(bot.getName(),bot.getToken());
                Class<?> clazz = Class.forName("bots.KVS_Bot_UpdateReceived");
                telegramKVS1C_bot.setOnUpdateInterface((OnUpdateInterface)clazz.newInstance());
                telegramBotsApi.registerBot(telegramKVS1C_bot);
                System.out.println("Bot " + bot.getName() + " is started!!");

                telegramKVS1C_bot.execute(new SendMessage("552286993","Bot " + bot.getName() + " is started!!"));
            }
            while (works.get()){
                sleep(1000);
            }
        } catch (TelegramApiException | InterruptedException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
