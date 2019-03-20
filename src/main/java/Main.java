import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.telegram.telegrambots.*;
import org.telegram.telegrambots.bots.*;
import org.telegram.telegrambots.meta.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class Main {
    private static String BOT_NAME = "MyNewTelegaBot";
    private static String BOT_TOKEN = "704957908:AAEp-tIOMrci50qXjV0sbTnzWeixLHftmWo" /* your bot's token here */;

    private static String PROXY_HOST = "ru.tgproxy.today" /* proxy host */;
    private static Integer PROXY_PORT = 443 /* proxy port */;
    private static String PROXY_USER = "" /* proxy user */;
    private static String PROXY_PASSWORD = "" /* proxy password */;

    public static void main(String[] args) {
        try {

            ApiContextInitializer.init();
            MyBotAbility bot;
            TelegramBotsApi botsApi = new TelegramBotsApi();


//             Авторизация бота в прокси, после создания будет использоваться автоматически
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(PROXY_USER, PROXY_PASSWORD.toCharArray());
                }
            });

            // Создаем экземпляр настроек
            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

            // Устанавливаем настройки прокси
            botOptions.setProxyHost(PROXY_HOST);
            botOptions.setProxyPort(PROXY_PORT);
            // Выбираем тип прокси: [HTTP|SOCKS4|SOCKS5] (по умолчанию: NO_PROXY)
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
            bot = new MyBotAbility(BOT_TOKEN, BOT_NAME, botOptions);
            botsApi.registerBot(new MyBotAbility(BOT_TOKEN, BOT_NAME, botOptions));

//            // Create the TelegramBotsApi object to register your bots
//            TelegramBotsApi botsApi = new TelegramBotsApi();
//
//            // Set up Http proxy
//            DefaultBotOptions botOptions2 = ApiContext.getInstance(DefaultBotOptions.class);
//
//            CredentialsProvider credsProvider = new BasicCredentialsProvider();
//            credsProvider.setCredentials(
//                    new AuthScope(PROXY_HOST, PROXY_PORT),
//                    new UsernamePasswordCredentials(PROXY_USER, PROXY_PASSWORD));
//
//            HttpHost httpHost = new HttpHost(PROXY_HOST, PROXY_PORT);
//
//            RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).setAuthenticationEnabled(true).build();
//            botOptions2.setRequestConfig(requestConfig);
//            botOptions2.setCredentialsProvider(credsProvider);
//            botOptions2.setHttpProxy(httpHost);
//
//            // Register your newly created AbilityBot
//            MyBotAbility bot = new MyBotAbility(BOT_TOKEN, BOT_NAME, botOptions);
//
//            botsApi.registerBot(bot);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
