package menu.other;

import menu.main.MyTelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Manual {

    private final MyTelegramBot bot;

    public Manual(MyTelegramBot bot) {
        this.bot = bot;
    }

    public void sendManualMenu(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Инструкция по использованию ...");
        message.setDisableWebPagePreview(true);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("капсульного микронаушника");
        button1.setCallbackData("in_1");
        row1.add(button1);
        keyboard.add(row1);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("магнитного микронаушника");
        button2.setCallbackData("in_2");
        row2.add(button2);
        keyboard.add(row2);

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("микронаушника с выносным микрофоном");
        button3.setCallbackData("in_3");
        row3.add(button3);
        keyboard.add(row3);

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button4.setText("капсульного микронаушника MAX");
        button4.setCallbackData("in_4");
        row4.add(button4);
        keyboard.add(row4);

        List<InlineKeyboardButton> row5 = new ArrayList<>();
        InlineKeyboardButton button5 = new InlineKeyboardButton();
        button5.setText("Назад");
        button5.setCallbackData("back");
        row5.add(button5);
        keyboard.add(row5);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }
    }

    public void sendManual1Menu(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("""
                Инструкция по использованию капсульного микронаушника
                
                Комплект:
                - Капсульный микронаушник (1 шт.)
                - Bluetooth-гарнитура
                - Батарейка (1 шт.)
                - USB-провод
                
                Шаг 1: Подготовка устройства
                1. Проверка комплектации: Убедитесь, что в комплекте есть все необходимые элементы: капсульный микронаушник, Bluetooth-гарнитура, батарейка и USB-провод.
                2. Проверка уровня заряда Bluetooth-гарнитуры:\s
                    - Подключите Bluetooth-гарнитуру к зарядному устройству с помощью USB-провода.
                    - Убедитесь, что гарнитура заряжена. Если уровень заряда низкий, подождите, пока устройство полностью зарядится.
                
                Шаг 2: Подготовка капсульного микронаушника
                1. Разборка микронаушника:
                    - Осторожно раскрутите капсульный микронаушник, чтобы получить доступ к отсеку для батарейки.
                2. Установка батарейки:
                    - Вставьте батарейку в микронаушник, соблюдая полярность.
                    - Аккуратно закрутите микронаушник обратно.
                
                Шаг 3: Использование микронаушника
                1. Вставка микронаушника:
                    - Вставьте капсульный микронаушник в ухо так, чтобы он был удобно размещен и не вызывал дискомфорт.
                2. Активизация Bluetooth-гарнитуры:
                    - Нажмите и удерживайте кнопку на Bluetooth-гарнитуре, пока не начнет мигать светодиод.
                3. Подключение к Bluetooth:
                    - Откройте настройки Bluetooth на вашем устройстве (смартфон, планшет и т.д.).
                    - Найдите в списке доступных устройств вашу Bluetooth-гарнитуру и выберите её для подключения.
                4. Подтверждение подключения:
                    - Дождитесь, пока в микронаушнике не прозвучит голосовое сообщение о подключении.
                
                Полезные советы:
                1. Соблюдайте гигиену: Перед использованием и после него обязательно протирайте микронаушник, чтобы предотвратить накопление ушной серы и избежать инфекций.
                2. Хранение: Храните микронаушник и гарнитуру в специальном футляре, чтобы защитить их от повреждений и пыли.
                3. Не допускайте перегрева: Избегайте длительного воздействия высоких температур на устройство.
                
                Теперь ваш капсульный микронаушник готов к использованию.
                
                *подробная инструкция находится в комплекте""");
        message.setDisableWebPagePreview(true);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row5 = new ArrayList<>();
        InlineKeyboardButton button5 = new InlineKeyboardButton();
        button5.setText("Назад в главное меню");
        button5.setCallbackData("back");
        row5.add(button5);
        keyboard.add(row5);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }
    }

    public void sendManual2Menu(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("""
                Инструкция по использованию магнитного микронаушника
                
                Комплект:
                - магнитный микронаушник(4 шт)
                - Bluetooth-гарнитура
                - батарейка(1 шт.)
                - магнитный извлекатель микронаушника из уха
                - usb-провод.
                
                Шаг 1: Подготовка устройства
                1. Проверка комплектации: Убедитесь, что в комплекте есть все необходимые компоненты: магнитный микронаушник(4 шт), Bluetooth-гарнитура, батарейка(1 шт.), магнитный изчвлекатель микронаушника из уха, usb-провод.
                2. Зарядка устройства:
                   - Подключите USB-кабель к Bluetooth-гарнитуре.
                   - Убедитесь, что гарнитура заряжена. Если уровень заряда низкий, подождите, пока устройство полностью зарядится.
                
                Шаг 2: Использование магнитного микронаушника
                1. Размещение петли:
                   - Наденьте Bluetooth-гарнитуру на шею так, чтобы она была удобно расположена и не мешала.
                   - Убедитесь, что петля не перекручена и правильно лежит вокруг шеи.
                2. Вставка микронаушника:
                   - Аккуратно вставьте магнитный микронаушник в слуховой проход. Для этого используйте специальную палочку или антенну, которая прилагается в комплекте.
                   - Убедитесь, что микронаушник правильно размещен и не вызывает дискомфорт.
                3. Подключение Bluetooth:
                   - Включите Bluetooth на вашем устройстве (смартфон, планшет).
                - Нажмите и удерживайте кнопку на Bluetooth-гарнитуре, пока не появится характерный сигнал.
                   - Найдите Bluetooth-гарнитуру в списке доступных устройств и подключитесь к ней.
                   - После успешного подключения на устройстве появится подтверждающее сообщение, а в микронаушнике будет слышен характерный сигнал.
                
                Шаг 3: Полезные советы
                1. Соблюдайте гигиену: Перед использованием и после него обязательно протирайте микронаушник, чтобы предотвратить накопление ушной серы и избежать инфекций.
                2. Хранение: Храните микронаушник в специальном футляре, который защищает его от повреждений и пыли.
                3. Не допускайте перегрева: Избегайте длительного воздействия высоких температур на устройство, чтобы не повредить его.
                
                Шаг 4: Завершение использования
                1. Удаление микронаушника:
                   - После завершения использования аккуратно извлеките микронаушник с помощью специальной палочки или антенны.
                2. Выключение устройства:
                   - Отключите устройство от Bluetooth и уберите петлю и микронаушник в футляр.
                
                Теперь ваш магнитный микронаушник готов к использованию!
                
                *подробная инструкция находится в комплекте""");
        message.setDisableWebPagePreview(true);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row5 = new ArrayList<>();
        InlineKeyboardButton button5 = new InlineKeyboardButton();
        button5.setText("Назад в главное меню");
        button5.setCallbackData("back");
        row5.add(button5);
        keyboard.add(row5);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }
    }

    public void sendManual3Menu(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("""
                Инструкция по использованию микронаушника с выносным микрофоном
                
                Комплект:
                - капсульный микронаушник с выносным микрофоном(1 шт.)
                - Bluetooth-гарнитура
                - USB-провод
                - батарейка (1 шт.)
                - магнитный извлекатель микронаушника из уха
                
                Шаг 1: Подготовка устройства
                1. Проверка комплектации: Убедитесь, что в комплекте есть все необходимые компоненты: капсульный микронаушник с выносным микрофоном(1 шт.), Bluetooth-гарнитура, USB-провод, батарейка (1 шт.), магнитный извлекатель микронаушника из уха.
                2. Зарядка Bluetooth-гарнитуры:
                   - Подключите Bluetooth-гарнитуру к зарядному устройству с помощью USB-провода.
                   -  Убедитесь, что гарнитура заряжена. Если уровень заряда низкий, подождите, пока устройство полностью зарядится.
                
                Шаг 2: Использование микронаушника
                1. Установка батарейки:
                   - Вставьте батарейку в гарнитуру с соблюдением полярности.
                2. Вставка микронаушника:
                   - Аккуратно вставьте микронаушник в слуховой проход, убедившись, что он правильно расположен и не вызывает дискомфорта.
                3. Подключение Bluetooth:
                   - Включите Bluetooth на вашем устройстве (смартфон, планшет).
                   - Нажмите и удерживайте кнопку на Bluetooth-гарнитуре, пока не появится характерный сигнал.
                   - Найдите вашу Bluetooth-гарнитуру с выносным микрофоном в списке доступных устройств и подключитесь к ней.
                   - После успешного подключения на устройстве появится подтверждающее сообщение, а в микронаушнике будет слышен характерный сигнал.
                
                Шаг 3: Использование выносного микрофона
                1. Расположение микрофона:
                   - Убедитесь, что выносной микрофон расположен рядом с вашим ртом для оптимального захвата звука.
                   - Проверьте, чтобы микрофон не касался одежды и не создавал посторонних шумов.
                  \s
                Шаг 4: Завершение использования
                1. Удаление микронаушника:
                   - После завершения использования аккуратно извлеките микронаушник с помощью магнитного извлекателя.
                2. Выключение устройства:
                   - Отключите устройство от Bluetooth и уберите микронаушник и гарнитуру в защитный футляр.
                
                Полезные советы:
                1. Соблюдайте гигиену: Перед использованием и после него обязательно протирайте микронаушник, чтобы предотвратить накопление ушной серы и избежать инфекций.
                2. Хранение: Храните микронаушник и гарнитуру в специальном футляре, чтобы защитить их от повреждений и пыли.
                3. Не допускайте перегрева: Избегайте длительного воздействия высоких температур на устройство.
                
                Теперь ваш микронаушник с выносным микрофоном готов к использованию!
                
                *подробная инструкция находится в комплекте""");
        message.setDisableWebPagePreview(true);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row5 = new ArrayList<>();
        InlineKeyboardButton button5 = new InlineKeyboardButton();
        button5.setText("Назад в главное меню");
        button5.setCallbackData("back");
        row5.add(button5);
        keyboard.add(row5);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }
    }

    public void sendManual4Menu(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("""
                Инструкция по использованию капсульного микронаушника MAX
                
                Комплект:
                - капсульный микронаушник (1 шт.)
                - выносной микрофон с кнопкой-пищалкой (1 шт.)
                - Bluetooth-гарнитура, провод Type-C
                - батарейка (1 шт.)
                - магнитный извлекатель микронаушника из уха
                
                Шаг 1: Подготовка устройства
                1. Проверка комплектации: Убедитесь, что в комплекте есть все необходимые компоненты: капсульный микронаушник (1 шт.), выносной микрофон с кнопкой-пищалкой (1 шт.), Bluetooth-гарнитура, провод Type-C, батарейка (1 шт.), магнитный извлекатель микронаушника из уха.
                2. Зарядка Bluetooth-гарнитуры:
                   - Подключите Bluetooth-гарнитуру к зарядному устройству с помощью Type-С провода
                   - Убедитесь, что гарнитура заряжена. Если уровень заряда низкий, подождите, пока устройство полностью зарядится.
                
                Шаг 2: Использование капсульного микронаушника MAX
                1. Установка батарейки:
                   - Вставьте батарейку в капсульный микронаушник, соблюдая полярность.
                2. Вставка микронаушника:
                   - Аккуратно вставьте капсульный микронаушник в слуховой проход. Убедитесь, что он удобно размещен и не вызывает дискомфорта.
                3. Подключение Bluetooth:
                   - Включите Bluetooth на вашем устройстве (смартфон, планшет).
                   - Нажмите и удерживайте кнопку на Bluetooth-гарнитуре, пока не появится характерный сигнал.
                   - Найдите вашу Bluetooth-гарнитуру в списке доступных устройств и подключитесь к ней.
                   - После успешного подключения на устройстве появится подтверждающее сообщение, а в микронаушнике будет слышен голос, подтверждающий подключение.
                
                Дополнительно: выносной микрофон подключается напрямую к Bluetooth-гарнитуре через разъем "Type-C".
                
                Шаг 3: Завершение использования
                1. Удаление микронаушника:
                   - После завершения использования аккуратно извлеките капсульный микронаушник с помощью магнитного извлекателя.
                2. Выключение устройства:
                   - Отключите устройство от Bluetooth и уберите микронаушник и гарнитуру в защитный футляр.
                
                Полезные советы:
                1. Соблюдайте гигиену: Перед использованием и после него обязательно протирайте капсульный микронаушник, чтобы предотвратить накопление ушной серы и избежать инфекций.
                2. Хранение: Храните микронаушник и гарнитуру в специальном футляре, чтобы защитить их от повреждений и пыли.
                3. Не допускайте перегрева: Избегайте длительного воздействия высоких температур на устройство.
                
                Теперь ваш капсульный микронаушник MAX готов к использованию!
                
                *подробная инструкция находится в комплекте""");
        message.setDisableWebPagePreview(true);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row5 = new ArrayList<>();
        InlineKeyboardButton button5 = new InlineKeyboardButton();
        button5.setText("Назад в главное меню");
        button5.setCallbackData("back");
        row5.add(button5);
        keyboard.add(row5);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }
    }
}
