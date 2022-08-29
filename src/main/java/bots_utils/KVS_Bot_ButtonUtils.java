package bots_utils;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.TreeSet;

public abstract class KVS_Bot_ButtonUtils {

    public static void getElementByGuidAPI(TelegramLongPollingBot telegramLongPollingBot) {
        SendMessage message = new SendMessage();
        message.setChatId("552286993");
        message.setText("������� GUID?");
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
            StringBuilder result = new StringBuilder("���� ��������� �������\n");
            result.append("������������ : " + getLastModifiedInCatalog("F:\\upn_work\\weekly") + "\n");
            result.append("���������� : " + getLastModifiedInCatalog("F:\\upn_work\\dailly") + "\n");
            result.append("������ 4 ���� : " + getLastModifiedInCatalog("F:\\upn_work\\hourly") + "\n");
            result.append("������ 30 ����� : " + getLastModifiedInCatalog("F:\\upn_work\\difference") + "\n");

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
}
