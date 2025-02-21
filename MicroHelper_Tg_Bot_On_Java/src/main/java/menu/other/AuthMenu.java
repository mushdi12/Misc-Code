package menu.other;

import menu.main.MyTelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class AuthMenu {

    private final MyTelegramBot bot;

    public AuthMenu(MyTelegramBot bot) {
        this.bot = bot;
    }

    public void sendAuthMenu(long chatId,String username) {

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Пройдите аутентификацию по ссылке снизу: https://docs.google.com/forms/d/e/1FAIpQLSdq0x-7ODJ1MyPzgP2VK8yARYfH9lKLXfYuu3ggbbYlia3Nmw/viewform?usp=pp_url&entry.369405530="+username);
        message.setDisableWebPagePreview(true);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Прошёл");
        button1.setCallbackData("auth_ready");
        row1.add(button1);
        keyboard.add(row1);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Назад");
        button2.setCallbackData("auth_back");
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
