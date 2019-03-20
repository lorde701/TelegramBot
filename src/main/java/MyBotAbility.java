import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class MyBotAbility extends AbilityBot {
    protected MyBotAbility(String botToken, String botUsername, DefaultBotOptions options) {
        super(botToken, botUsername, options);
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {

    }

    @Override
    public int creatorId() {
        return 0;
    }
}

