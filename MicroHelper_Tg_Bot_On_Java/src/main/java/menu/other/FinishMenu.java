package menu.other;

import menu.main.MyTelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class FinishMenu {

    private final MyTelegramBot bot;

    public FinishMenu(MyTelegramBot bot) {
        this.bot = bot;
    }

    public void sendFinishMenu(long chatId) {

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("\uD83D\uDCA8Как только курьер освободится, он свяжется с вами для согласования всех деталей. Пожалуйста, обсудите с ним удобное время и место для получения микронаушника, а также для его возврата.\n \n" +
                "\uD83D\uDCAAКоманда MicroHelper искренне благодарит вас за оказанное доверие! Мы рады, что вы с нами, и спешим поделиться: мы активно развиваемся и работаем над тем, чтобы наш сервис становился ещё лучше и удобнее для вас.\n \n" +
                "\uD83D\uDCA1Делитесь этим сервисом с друзьями и оставайтесь с нами — впереди много интересного! \n" +"До скорой встречи!" );
        message.setDisableWebPagePreview(true);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Оставить отзыв");
        button1.setCallbackData("feedback");
        row1.add(button1);
        keyboard.add(row1);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Назад в главное меню");
        button2.setCallbackData("back");
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
