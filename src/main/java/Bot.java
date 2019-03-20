import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class Bot extends TelegramLongPollingBot {


    public Bot(DefaultBotOptions options){
        super(options);
    }


    public static void main(String[] args) {

        String PROXY_HOST = "ru.tgproxy.today";
        int PROXY_PORT = 443;
        String PROXY_USER = "";
        String PROXY_PASSWORD = "";

//        System.getProperties().put( "proxySet", "true" );
//        System.getProperties().put( "socksProxyHost", PROXY_HOST);
//        System.getProperties().put( "socksProxyPort", PROXY_PORT);


        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
////             Авторизация бота в прокси, после создания будет использоваться автоматически
//            Authenticator.setDefault(new Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(PROXY_USER, PROXY_PASSWORD.toCharArray());
//                }
//            });

            // Создаем экземпляр настроек
            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

            // Устанавливаем настройки прокси
            botOptions.setProxyHost(PROXY_HOST);
            botOptions.setProxyPort(PROXY_PORT);
            // Выбираем тип прокси: [HTTP|SOCKS4|SOCKS5] (по умолчанию: NO_PROXY)
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);

            telegramBotsApi.registerBot(new Bot(botOptions));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для приема сообщений.
     *
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            sendMsg(message, "Привет!");
        }
    }


    public synchronized void sendMsg(Message message, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     *
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return "MyFirstTelegmBot_bot";
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     *
     * @return token для бота
     */
    @Override
    public String getBotToken() {
        return "729471216:AAElfvR8rBN4Ab3DcB4tmSlmPIIa8OfYQTU";
    }

}