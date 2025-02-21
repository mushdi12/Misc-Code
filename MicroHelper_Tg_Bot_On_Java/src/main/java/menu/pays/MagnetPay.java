package menu.pays;

import menu.main.MyTelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MagnetPay {

    private final MyTelegramBot bot;

    public MagnetPay(MyTelegramBot bot) {
        this.bot = bot;
    }

    public void sendPaysMagnetMenu(long chatId,String username) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Оплатите по ссылке снизу\nhttps://t.me/tribute/app?startapp=d6Q1\nУкажите тг никнейм -> " + username+ " в комментариях для автора");
        message.setDisableWebPagePreview(true);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Я оплатил");
        button1.setCallbackData("finish_magnet");
        row1.add(button1);
        keyboard.add(row1);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Назад");
        button2.setCallbackData("back_to_micro_menu");
        row2.add(button2);
        keyboard.add(row2);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
