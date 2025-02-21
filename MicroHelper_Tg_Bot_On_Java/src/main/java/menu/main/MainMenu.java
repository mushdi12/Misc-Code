package menu.main;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {

    private final MyTelegramBot bot;

    public MainMenu(MyTelegramBot bot) {
        this.bot = bot;
    }

    public void sendMainMenu(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("\uD83E\uDD18 Команда MicroHelper рада видеть вас здесь!");

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Аренда Микронаушников");
        button1.setCallbackData("rent");
        row1.add(button1);
        keyboard.add(row1);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Покупка Батареек");
        button2.setCallbackData("order");
        row2.add(button2);
        keyboard.add(row2);

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("Аутентификация");
        button3.setCallbackData("auth");
        row3.add(button3);
        keyboard.add(row3);

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button4.setText("Тех. Поддержка");
        button4.setCallbackData("tech_support");
        row4.add(button4);
        keyboard.add(row4);


        List<InlineKeyboardButton> row5 = new ArrayList<>();
        InlineKeyboardButton button5 = new InlineKeyboardButton();
        button5.setText("Инструкция");
        button5.setCallbackData("manual");
        row5.add(button5);
        keyboard.add(row5);

        List<InlineKeyboardButton> row6 = new ArrayList<>();
        InlineKeyboardButton button6 = new InlineKeyboardButton();
        button6.setText("Правила");
        button6.setCallbackData("rules");
        row6.add(button6);
        keyboard.add(row6);


        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
