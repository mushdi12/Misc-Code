package menu.main;

import menu.buttery.ButteryMenu;
import menu.buttery.Buttery1;
import menu.buttery.Buttery2;
import menu.buttery.Buttery3;
import menu.earpieces.*;
import menu.other.*;
import menu.pays.CapsuleMaxPay;
import menu.pays.CapsulePay;
import menu.pays.CapsuleWithMicrophonePay;
import menu.pays.MagnetPay;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

import static menu.main.GoogleParser.checkAuth;

public class MyTelegramBot extends TelegramLongPollingBot {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private final Keyboard keyboard = new Keyboard(this);

    private final Magnet magnet = new Magnet(this);

    private final Capsule capsule = new Capsule(this);

    private final CapsuleWithMicrophone capsuleWithMicrophone = new CapsuleWithMicrophone(this);

    private final CapsuleMax capsuleMax = new CapsuleMax(this);

    private final MainMenu mainMenu = new MainMenu(this);

    private final ButteryMenu buttery = new ButteryMenu(this);

    private final AuthMenu authMenu = new AuthMenu(this);

    private final ChangeMcnMenu changeMcnMenu = new ChangeMcnMenu(this);

    private final MagnetPay magnetPay = new MagnetPay(this);

    private final CapsulePay capsulePay = new CapsulePay(this);

    private final CapsuleWithMicrophonePay capsuleWithMicrophonePay = new CapsuleWithMicrophonePay(this);

    private final CapsuleMaxPay capsuleMaxPay = new CapsuleMaxPay(this);

    private final FinishMenu finishMenu = new FinishMenu(this);

    private final Buttery1 buttery1 = new Buttery1(this);

    private final Buttery2 buttery2 = new Buttery2(this);

    private final Buttery3 buttery3 = new Buttery3(this);

    private final SupportMenu supportMenu = new SupportMenu(this);

    private final FeedbackMenu feedbackMenu = new FeedbackMenu(this);

    private final Manual manual = new Manual(this);

    private final Rules rules = new Rules(this);

    private boolean support_flag = false;

    private final long support_chat = -1002231285606L;

    private final long orders_chat = -1002247152457L;

    private int support_messageId;

    private int hello_messageId;

    private boolean hello_flag;


    @Override
    public void onUpdateReceived(Update update) {
        executorService.submit(() -> handleUpdate(update));
    }

    private void handleUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            User user = update.getMessage().getFrom();
            String username = "@" + user.getUserName();
            Integer messageId = update.getMessage().getMessageId();
            String firstName = user.getFirstName();

            if (messageText.equals("/start") || messageText.equals("Перезапуск")) {
                hello_messageId = sendSpecialMessage(chatId, "Приветствуем вас, " + firstName + "\uD83E\uDD1D");
                hello_flag = true;
                keyboard.sendRestartKeyboard(chatId, username);
                mainMenu.sendMainMenu(chatId);
            } else if (support_flag || messageText.equals("/empty")) {
                supportManager(messageText, username, chatId, messageId);
            }
        } else if (update.hasCallbackQuery()) {
            handleCallbackQuery(update.getCallbackQuery());
        }
    }

    private void supportManager(String messageText, String username, long chatId, Integer messageId) {
        support_flag = false;
        String modifiedText;
        if (messageText.equals("/empty")) {
            modifiedText = "Ваша заявка отменена.❌";
        } else {
            modifiedText = "Ваша заявка на рассмотрении.✅\nС вами скоро свяжется Тех. Поддержка.";
            sendMessage(support_chat, "От пользователя: " + username + "\n" + "Обращение пользователя: \n" + messageText);
        }
        deleteMessage(chatId, support_messageId);
        deleteMessage(chatId, messageId);
        supportMenu.sendSupportMenu(chatId, modifiedText);
    }


    private void handleCallbackQuery(CallbackQuery callbackQuery) {
        String callbackData = callbackQuery.getData();
        long chatId = callbackQuery.getMessage().getChatId();
        Integer messageId = callbackQuery.getMessage().getMessageId();
        String username = "@" + callbackQuery.getFrom().getUserName();
        String status = checkAuth(username);
        Map<String, Integer> itemCounts = getItemCounts();
        String realName = GoogleParser.checkName(username);

        deleteMessage(chatId, messageId);
        if (hello_flag) {
            deleteMessage(chatId, hello_messageId);
            hello_flag = false;
        }

        switch (callbackData) {
            case "rent", "back_to_micro_menu":
                GoogleParser.goPars();
                changeMcnMenu.sendMicroMenu(chatId);
                break;

            case "order":
                GoogleParser.goPars();
                GoogleParser.goParsCount();
                buttery.sendButteryMenu(chatId);
                break;

            case "auth":
                authMenu.sendAuthMenu(chatId, username);
                break;

            case "manual","back_manual":
                manual.sendManualMenu(chatId);
                break;

            case "politics":
                rules.sendPolitics(chatId);
                break;

            case "agreements":
                rules.sendAgreements(chatId);
                break;

            case "in_1":
                manual.sendManual1Menu(chatId);
                break;

            case "in_2":
                manual.sendManual2Menu(chatId);
                break;

            case "in_3":
                manual.sendManual3Menu(chatId);
                break;

            case "in_4":
                manual.sendManual4Menu(chatId);
                break;

            case "rules", "back_rules":
                rules.sendRulesMenu(chatId);
                break;

            case "tech_support":
                support_flag = true;
                support_messageId = sendSpecialMessage(chatId, """
                    \uD83D\uDE13 Похоже, у вас возникла какая-то проблема. \
                    Сообщите нам о ней, и мы с удовольствием поможем вам её решить.☺\uFE0F
                    \s
                     \uD83D\uDCCAГрафик работы с 8:00 - 21:00 по ВДК\
                    \uD83C\uDF0A
                    \s
                      Или нажмите /empty, чтобы вернуться назад.\uD83D\uDD19\uD83D\uDD19""");
                break;

            case "choice_magnet":
                magnet.sendMagnetMenu(chatId);
                break;

            case "choice_capsule":
                capsule.sendCapsMenu(chatId);
                break;

            case "choice_capsule_with_microphone":
                capsuleWithMicrophone.sendCapsuleWithMicrophoneMenu(chatId);
                break;

            case "choice_capsule_max":
                capsuleMax.sendCapsMaxMenu(chatId);
                break;

            case "magnet_pay":
                handleAuthenticatedPayment(chatId, username, status, itemCounts.get("Магнитные"), magnetPay::sendPaysMagnetMenu);
                break;

            case "caps_pay":
                handleAuthenticatedPayment(chatId, username, status, itemCounts.get("Капсульные"), capsulePay::sendPaysCapsMenu);
                break;

            case "caps_micro_pay":
                handleAuthenticatedPayment(chatId, username, status, itemCounts.get("Капсульные с микрофоном"), capsuleWithMicrophonePay::sendPaysCapsMicroMenu);
                break;

            case "caps_max_pay":
                handleAuthenticatedPayment(chatId, username, status, itemCounts.get("Капсульные Max"), capsuleMaxPay::sendPaysCapsMaxMenu);
                break;

            case "but_1":
                handleAuthenticatedPayment(chatId, username, status, itemCounts.get("Батарейки") > 0 ? 1 : 0, buttery1::sendPaysB1Menu);
                break;

            case "but_2":
                handleAuthenticatedPayment(chatId, username, status, itemCounts.get("Батарейки") > 1 ? 1 : 0, buttery2::sendPaysB2Menu);
                break;

            case "but_3":
                handleAuthenticatedPayment(chatId, username, status, itemCounts.get("Батарейки") > 2 ? 1 : 0, buttery3::sendPaysB3Menu);
                break;

            case "back", "back_but", "auth_back":
                mainMenu.sendMainMenu(chatId);
                break;

            case "auth_ready":
                sendMessage(orders_chat, "⚠\uFE0F⚠\uFE0F⚠\uFE0F\n #запросы"+"От пользователя : " + username +  "\nЗапрос на авторизацию!!!");
                mainMenu.sendMainMenu(chatId);
                break;

            case "feedback":
                feedbackMenu.sendFeedbackMenu(chatId, "Отзыв можно написать сюда -> https://t.me/microhelpersupport\n\n*как только отзыв пройдет модерацию, он появится в офф. канале MicroHelper\uD83D\uDD25");
                break;

            case "finish_caps_max", "finish_caps", "finish_caps_micro", "finish_magnet", "finish_but", "finish_but_2",
                 "finish_but_3", "finish_caps_max_plus", "finish_caps_plus", "finish_caps_micro_plus":
                GoogleParser.goParsName();
                finishMenu.sendFinishMenu(chatId);
                sendMessage(orders_chat, changeTypeOfOrder(callbackData, username, realName));
                break;

            case "caps_micro_pay_plus":
                handleAuthenticatedPlusPayment(chatId, username, status, itemCounts.get("Капсульные с микрофоном"), itemCounts.get("Батарейки"), capsuleWithMicrophonePay::sendCapsMicroPlus);
                break;

            case "caps_max_pay_plus":
                handleAuthenticatedPlusPayment(chatId, username, status, itemCounts.get("Капсульные Max"), itemCounts.get("Батарейки"), capsuleMaxPay::sendCapsMaxPlus);
                break;

            case "caps_pay_plus":
                handleAuthenticatedPlusPayment(chatId, username, status, itemCounts.get("Капсульные"), itemCounts.get("Батарейки"), capsulePay::sendCapsPlus);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + callbackData);
        }
    }

    private Map<String, Integer> getItemCounts() {
        Map<String, Integer> itemCounts = new HashMap<>();
        itemCounts.put("Магнитные", GoogleParser.sendCount("Магнитные"));
        itemCounts.put("Капсульные", GoogleParser.sendCount("Капсульные"));
        itemCounts.put("Капсульные Max", GoogleParser.sendCount("Капсульные Max"));
        itemCounts.put("Капсульные с микрофоном", GoogleParser.sendCount("Капсульные с микрофоном"));
        itemCounts.put("Батарейки", GoogleParser.sendCount("Батарейки"));
        return itemCounts;
    }

    private void handlePayment(long chatId, String username, Integer itemCount, BiConsumer<Long, String> paymentMethod) {
        if (itemCount > 0) {
            paymentMethod.accept(chatId, username);
        } else {
            supportMenu.sendSupportMenu(chatId, "Товара нет в наличие.❌");
        }
    }

    private void handleAuthenticatedPayment(long chatId, String username, String status, Integer itemCount, BiConsumer<Long, String> paymentMethod) {
        if (status.equals("Проверен")) {
            handlePayment(chatId, username, itemCount, paymentMethod);
        } else {
            supportMenu.sendSupportMenu(chatId, "Вы не прошли аутентификацию.❌");
        }
    }

    private void handleAuthenticatedPlusPayment(long chatId, String username, String status, Integer itemCount1, Integer itemCount2, BiConsumer<Long, String> paymentMethod) {
        if (status.equals("Проверен")) {
            if (itemCount1 > 0 && itemCount2 > 0) {
                paymentMethod.accept(chatId, username);
            } else {
                supportMenu.sendSupportMenu(chatId, "Товара нет в наличие.❌");
            }
        } else {
            supportMenu.sendSupportMenu(chatId, "Вы не прошли аутентификацию.❌");
        }
    }


    private void handlePayment(long chatId, String username, String status, boolean isAvailable, BiConsumer<Long, String> paymentMethod) {
        if (status.equals("Проверен")) {
            if (isAvailable) {
                paymentMethod.accept(chatId, username);
            } else {
                supportMenu.sendSupportMenu(chatId, "Товара нет в наличие.❌");
            }
        } else {
            supportMenu.sendSupportMenu(chatId, "Вы не прошли аутентификацию.❌");
        }
    }




    private void deleteMessage(Long chatId, Integer messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId.toString());
        deleteMessage.setMessageId(messageId);

        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        message.setDisableWebPagePreview(true);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private int sendSpecialMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            Message sentMessage = execute(message);
            return sentMessage.getMessageId();
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return -1; // Возвращаем -1 в случае ошибки
        }
    }


    private String  changeTypeOfOrder(String type, String username,String realName) {
        String text = "#заказы\nПользователь : "+ realName + "\n" +"Никнейм : " + username + "\n" + "Товар : ";
        return switch (type) {
            case "finish_caps_max" -> text + "Капсульный MAX.";
            case "finish_caps" -> text + "Капсульный.";
            case "finish_caps_micro" -> text + "Капсульный с выносным микрофоном.";
            case "finish_caps_max_plus" -> text + "Капсульный MAX + 1 батарейка.";
            case "finish_caps_plus" -> text + "Капсульный+ 1 батарейка.";
            case "finish_caps_micro_plus" -> text + "Капсульный с выносным микрофоном + 1 батарейка.";
            case "finish_magnet" -> text + "Магнитный.";
            case "finish_but" -> text + "Батарейка 1 шт.";
            case "finish_but_2" -> text + "Батарейки 2 шт.";
            case "finish_but_3" -> text + "Батарейки 3 шт.";
            default -> text + "Ошибка!!!, Свяжитесь с " + username + " для уточнения обстоятельств.";
        };
    }

    @Override
    public String getBotUsername() {
        return "MicroHelper";
    }

    @Override
    public String getBotToken() {
        return "7341776356:AAGjomMnVnZjKkI7d0JltD-8TyR--B8oVu0";
    }
    
}
