import utils.OnUpdateInterface;
import utils.Telegram_Bot_fast;
import data.TgBot;
import lombok.Setter;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class ApplicationTelegram extends Thread {

    private transient TelegramBotsApi telegramBotsApi = null;

    @Setter
    private AtomicBoolean works = new AtomicBoolean(true);

    @Override
    public void run() {
        List<TgBot> tgBots = TgBot.getListTgBots();

        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            for (TgBot bot : tgBots) {
                Telegram_Bot_fast telegramKVS1C_bot = new Telegram_Bot_fast(bot.getName(), bot.getToken());
                if (bot.getActivate()) {
                    Class<OnUpdateInterface> clazz = (Class<OnUpdateInterface>) Class.forName("bots." + bot.getNameReaction());
                    OnUpdateInterface listener = (OnUpdateInterface) clazz.newInstance();
                    telegramKVS1C_bot.setOnUpdateInterface(listener);
                    telegramBotsApi.registerBot(telegramKVS1C_bot);

                    System.out.println("Bot " + bot.getName() + " is started!!");
                    if (!bot.getBaseChatId().isEmpty()) {
                        telegramKVS1C_bot.execute(new SendMessage(bot.getBaseChatId(), "Bot " + bot.getName() + " is started!!"));
                        listener.beforeCreateObject(telegramKVS1C_bot);
                    }

                }
            }
            while (works.get()) {
                Thread.sleep(1000);
                Scanner scanner = new Scanner(System.in);
                System.out.println("Что бы отключить введите Y");
                if (scanner.next().equals("y")){
                    works.set(false);
                }
            }
        } catch (TelegramApiException | InterruptedException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
