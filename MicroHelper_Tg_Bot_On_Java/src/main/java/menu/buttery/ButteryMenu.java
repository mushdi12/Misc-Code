package menu.buttery;


import menu.main.MyTelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class ButteryMenu {

    private final MyTelegramBot bot;

    public ButteryMenu(MyTelegramBot bot) {
        this.bot = bot;
    }

    public void sendButteryMenu(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выбери желаемое кол-во батареек:");
        message.setDisableWebPagePreview(true);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("1 шт. 200 ₽");
        button1.setCallbackData("but_1");
        row1.add(button1);
        keyboard.add(row1);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("2 шт. 380 ₽");
        button2.setCallbackData("but_2");
        row2.add(button2);
        keyboard.add(row2);

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("3 шт. 550 ₽");
        button3.setCallbackData("but_3");
        row3.add(button3);
        keyboard.add(row3);

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button4.setText("Назад");
        button4.setCallbackData("back");
        row4.add(button4);
        keyboard.add(row4);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            bot.execute(message);

        } catch (TelegramApiException e) {
            e.printStackTrace();

        }
    }
}
