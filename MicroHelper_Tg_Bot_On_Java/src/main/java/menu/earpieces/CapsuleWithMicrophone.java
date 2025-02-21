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

public class CapsuleWithMicrophone {

    private final MyTelegramBot bot;

    public CapsuleWithMicrophone(MyTelegramBot bot) {
        this.bot = bot;
    }

    public void sendCapsuleWithMicrophoneMenu(long chatId) {
        GoogleParser.goParsCount();
        Integer count = GoogleParser.sendCount("Капсульные с микрофоном");
        SendPhoto photo = new SendPhoto();
        photo.setChatId(chatId);
        InputStream inputStream = Capsule.class.getResourceAsStream("/caps_dop.jpg");
        if (inputStream != null) {
            InputFile inputFile = new InputFile(inputStream, "caps_dop.jpg");
            photo.setPhoto(inputFile);
        } else {
            System.out.println("Image not found");
        }
        photo.setCaption("Вы выбрали модель: Капсульный с выносным микрофоном.\n\n" +
                "💰Стоимость аренды: 550р/день.\n\n" +
                "🔋Время работы одной батарейки: 3-4 часа.\n\n" +
                "📦Комплект: капсульный микронаушник с выносным микрофоном (1 шт.), Bluetooth-гарнитура, USB-провод, батарейка (1 шт.), магнитный извлекатель микронаушника из уха.\n \n" + "В наличие : " + count);

        InlineKeyboardButton orderButton = new InlineKeyboardButton();
        orderButton.setText("Заказать");
        orderButton.setCallbackData("caps_micro_pay");

        InlineKeyboardButton order_plus_Button = new InlineKeyboardButton();
        order_plus_Button.setText("Заказать + 1 батарейка");
        order_plus_Button.setCallbackData("caps_micro_pay_plus");

        InlineKeyboardButton backButton = new InlineKeyboardButton();
        backButton.setText("Назад");
        backButton.setCallbackData("back_to_micro_menu");


        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(orderButton);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(order_plus_Button);

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(backButton);


        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);


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
