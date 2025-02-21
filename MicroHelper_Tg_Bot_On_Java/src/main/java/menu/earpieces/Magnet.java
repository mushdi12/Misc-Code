package menu.earpieces;

import menu.main.GoogleParser;
import menu.main.MyTelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Magnet {

    private final MyTelegramBot bot;

    public Magnet(MyTelegramBot bot) {
        this.bot = bot;
    }

    public void sendMagnetMenu(long chatId) {
        GoogleParser.goParsCount();
        Integer count = GoogleParser.sendCount("Магнитные");
        SendPhoto photo = new SendPhoto();
        photo.setChatId(chatId);
        InputStream inputStream = Capsule.class.getResourceAsStream("/magnet.jpg");
        if (inputStream != null) {
            InputFile inputFile = new InputFile(inputStream, "magnet.jpg");
            photo.setPhoto(inputFile);
        } else {
            System.out.println("Image not found");
        }
        photo.setCaption(" Вы выбрали модель: Магнитный.\n \n 💰Стоимость аренды: 500р/день.\n \n 📦Комплект: магнитный микронаушник(4 шт), Bluetooth-гарнитура, батарейка(1 шт.), " +
                "магнитный извлекатель микронаушника из уха, usb-провод.\n \n" + "В наличие : " + count);
        
        InlineKeyboardButton orderButton = new InlineKeyboardButton();
        orderButton.setText("Заказать");
        orderButton.setCallbackData("magnet_pay");

        InlineKeyboardButton backButton = new InlineKeyboardButton();
        backButton.setText("Назад");
        backButton.setCallbackData("back_to_micro_menu");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(orderButton);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(backButton);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);
        photo.setReplyMarkup(keyboardMarkup);

        try {
            bot.execute(photo);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
