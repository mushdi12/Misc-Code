package menu.other;

import menu.main.MyTelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Rules {

    private final MyTelegramBot bot;

    public Rules(MyTelegramBot bot) {
        this.bot = bot;
    }

    public void sendRulesMenu(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберете информацию которую хотите написать");

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Пользовательское соглашение");
        button1.setCallbackData("agreements");
        row1.add(button1);
        keyboard.add(row1);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Политика конфиденциальности");
        button2.setCallbackData("politics");
        row2.add(button2);
        keyboard.add(row2);

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("Назад");
        button3.setCallbackData("back");
        row3.add(button3);
        keyboard.add(row3);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }
    }

    public void sendAgreements(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("""
                Пользовательское соглашение
                
                Дата публикации: 22.08.2024
                
                1. Общие положения
                
                1.1. Настоящее Пользовательское соглашение регулирует порядок использования сервиса аренды микронаушников, предоставляемого через телеграм-бот MicroHelper, а также права и обязанности пользователей и администратора сервиса @microhelpersupport.
                
                1.2. Использование Бота означает согласие Пользователя с условиями данного Соглашения.
                
                1.3. Администратор оставляет за собой право вносить изменения в данное Соглашение.
                
                2. Услуги
                
                2.1. Бот предоставляет Пользователю возможность аренды микронаушников на определённый срок.
                
                2.2. Услуги предоставляются на платной основе. Подробная информация о стоимости и условиях аренды размещена в интерфейсе Бота.
                
                2.3. Администратор вправе изменять условия предоставления услуг, включая тарифы, уведомляя об этом Пользователей через официальный телеграм-канал MicroHelper.
                
                3. Обязанности Пользователя
                
                3.1. Пользователь обязан предоставлять достоверные данные при регистрации и использовании Бота.
                
                3.2. Пользователь обязуется использовать арендованные устройства по назначению и в соответствии с инструкциями, предоставленными Администратором.
                
                3.3. Пользователь несёт полную ответственность за сохранность арендованного оборудования и обязуется вернуть его в надлежащем состоянии.
                
                3.4. В случае утраты или повреждения арендованного оборудования Пользователь обязуется возместить ущерб в размере, определённом Администратором.
                
                4. Ответственность
                
                4.1. Администратор не несет ответственности за любые убытки, понесенные Пользователем в результате использования услуг Бота.
                
                4.2. Администратор не несет ответственности за технические сбои, прерывания в работе Бота или задержки в предоставлении услуг, вызванные обстоятельствами, не зависящими от Администратора.
                
                5. Конфиденциальность и защита данных
                
                5.1. Администратор обязуется обеспечивать конфиденциальность данных Пользователя и не передавать их третьим лицам.
                
                5.2. Пользователь соглашается на обработку своих персональных данных Администратором в целях предоставления услуг.
                
                6. Разрешение споров
                
                6.1. Все споры и разногласия, возникающие в связи с исполнением данного Соглашения, решаются путём переговоров.
                
                7. Заключительные положения
                
                7.1. Настоящее Соглашение вступает в силу с момента его акцепта Пользователем и действует в течение неопределенного срока.
                
                7.2. Администратор вправе прекратить предоставление услуг Пользователю в случае нарушения последним условий данного Соглашения.""");
        message.setDisableWebPagePreview(true);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Назад в главное меню");
        button1.setCallbackData("back");
        row1.add(button1);
        keyboard.add(row1);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }
    }

    public void sendPolitics(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("""
                Политика конфиденциальности
                
                Дата публикации: 22.08.2024
                
                1. Сбор информации
                
                Мы собираем и обрабатываем следующие категории персональных данных:
                
                - ФИО: Для идентификации пользователей.
                - Никнейм в Telegram: Для связи и взаимодействия в рамках сервиса.
                - Фотографии документов: Паспорт, студенческий билет, пропуск в общежитие, водительские права — для подтверждения личности и предоставления соответствующих услуг.
                
                2. Цели обработки данных
                
                Собранные данные используются для:
                
                - Подтверждения личности пользователей.
                - Предоставления доступа к услугам и сервисам, требующим идентификации.
                - Поддержки связи с пользователями через Telegram.
                
                3. Хранение данных
                
                Персональные данные хранятся в защищенной базе данных с ограниченным доступом.
                
                4. Изменения в политике конфиденциальности
                
                Мы оставляем за собой право изменять данную политику конфиденциальности. Обо всех изменениях будет сообщено в нашем официальном телеграм-канале MicroHelper.
                
                5. Контактная информация
                
                Если у вас возникли вопросы или замечания по поводу нашей политики конфиденциальности, пожалуйста, свяжитесь с нами по адресу: microhelperceo@gmail.com""");
        message.setDisableWebPagePreview(true);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Назад в главное меню");
        button1.setCallbackData("back");
        row1.add(button1);
        keyboard.add(row1);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }
    }

}
