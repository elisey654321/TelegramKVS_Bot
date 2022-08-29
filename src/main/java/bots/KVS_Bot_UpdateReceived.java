package bots;

import bots_utils.KVS_Bot_ButtonUtils;
import utils.OnUpdateInterface;
import lombok.Getter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Getter
public class KVS_Bot_UpdateReceived implements OnUpdateInterface {

    private String answer;
    private final InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

    @Override
    public void onUpdateReceived(Update update, TelegramLongPollingBot telegramLongPollingBot) {
        if (update.hasCallbackQuery()) {
            clickedButton(update, telegramLongPollingBot);
        } else {
            enterText(update, telegramLongPollingBot);
        }
    }

    private void enterText(Update update, TelegramLongPollingBot telegramLongPollingBot) {
        String text = update.getMessage().getText();
        if (text.length() == 36 || text.indexOf("-") > 0){
            KVS_Bot_ButtonUtils.getDataFrom1C(text);
        }else {
            answer = "Введите корректные данные!";
            fillInlineKeyboardMarkup(text);
            defaultResult(telegramLongPollingBot);
        }
    }

    private void clickedButton(Update update, TelegramLongPollingBot telegramLongPollingBot) {
        String text = update.getCallbackQuery().getData();
        switch (text) {
            case ("getElementByGuidAPI"):
                KVS_Bot_ButtonUtils.getElementByGuidAPI(telegramLongPollingBot);
                break;
            case ("getErrorsAPI"):
                KVS_Bot_ButtonUtils.getElementByGuidAPI(telegramLongPollingBot);
                break;
            case ("getDataLastBackupAPI"):
                KVS_Bot_ButtonUtils.getDataLastBackupAPI(telegramLongPollingBot);
                break;
            case ("checkExchangeAPI"):
                KVS_Bot_ButtonUtils.checkExchangeAPI(telegramLongPollingBot);
                break;
            default:
                defaultResult(telegramLongPollingBot);
                break;
        }
    }

    private void defaultResult(TelegramLongPollingBot telegramLongPollingBot) {
        SendMessage message = new SendMessage();
        message.setChatId("552286993");
        message.setText(getAnswer());
        message.setReplyMarkup(getInlineKeyboardMarkup());
        try {
            telegramLongPollingBot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void fillInlineKeyboardMarkup(String text) {
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(new InlineKeyboardButtonSpeed("Получить последние ошибки", "getErrorsAPI"));
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(new InlineKeyboardButtonSpeed("Дата последнего бэкапа", "getDataLastBackupAPI"));
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(new InlineKeyboardButtonSpeed("Проверить работу обмена", "checkExchangeAPI"));
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        keyboardButtonsRow4.add(new InlineKeyboardButtonSpeed("Получить элемент по GUID", "getElementByGuidAPI"));

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);
        inlineKeyboardMarkup.setKeyboard(rowList);
    }

    private static class InlineKeyboardButtonSpeed extends InlineKeyboardButton {

        public InlineKeyboardButtonSpeed(String textButton, String textCommand) {
            this.setText(textButton);
            this.setCallbackData(textCommand);
        }

    }
}
