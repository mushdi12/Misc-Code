package menu.main;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {

    private final MyTelegramBot bot;

    public Keyboard(MyTelegramBot bot) {
        this.bot = bot;
    }

    public void sendRestartKeyboard(long chatId, String username) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        //message.setText("Приветствуем вас, "+firstName +"\uD83E\uDD1D");
        message.setText("Никнейм сессии : " + username);
        //message.setText("\u200B");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton("Перезапуск"));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }
    }
}
