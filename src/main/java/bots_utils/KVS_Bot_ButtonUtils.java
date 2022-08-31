package bots_utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.TreeSet;

public abstract class KVS_Bot_ButtonUtils {

    public static void getElementByGuidAPI(TelegramLongPollingBot telegramLongPollingBot) {
        SendMessage message = new SendMessage();
        message.setChatId("552286993");
        message.setText("Введите GUID?");
        try {
            telegramLongPollingBot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void getDataFrom1C(String text) {

    }

    public static void getDataLastBackupAPI(TelegramLongPollingBot telegramLongPollingBot) {
        try {
            StringBuilder result = new StringBuilder("Даты последних бэкапов\n");
            result.append("Еженедельный : " + getLastModifiedInCatalog("F:\\upn_work\\weekly") + "\n");
            result.append("Ежедневный : " + getLastModifiedInCatalog("F:\\upn_work\\dailly") + "\n");
            result.append("Каждые 4 часа : " + getLastModifiedInCatalog("F:\\upn_work\\hourly") + "\n");
            result.append("Каждые 30 минут : " + getLastModifiedInCatalog("F:\\upn_work\\difference") + "\n");

            SendMessage message = new SendMessage("552286993",result.toString());

            telegramLongPollingBot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static Date getLastModifiedInCatalog(String catalogS) {
        Date result = null;
        try {
            File catalog = new File(catalogS);
            File[] listFiles = catalog.listFiles();
            TreeSet<Date> dateTreeSet = new TreeSet<>();
            if (listFiles != null) {
                for (File file : listFiles) {
                    dateTreeSet.add(new Date(file.lastModified()));
                }
                if(!dateTreeSet.isEmpty()) result = dateTreeSet.last();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void checkExchangeAPI(TelegramLongPollingBot telegramLongPollingBot) {
        try {
            activatedExchangePost();
            SendMessage message = new SendMessage("552286993","Обмен включен");
            telegramLongPollingBot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void activatedExchangePost(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://srv-g9-mail:8080/post");
        try {
            HttpResponse response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void beforeCreateObject(TelegramLongPollingBot telegramLongPollingBot){
        Runnable runnable = ()->{
            try {
                activatedExchangePost();
                telegramLongPollingBot.execute(new SendMessage("552286993","Обмен включен"));
                Thread.sleep(3600*4);
            } catch (InterruptedException | TelegramApiException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

}
