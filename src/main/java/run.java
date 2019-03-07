import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class run {
    public static void run_experiment(AbsSender as, ArrayList<String> a, Update u, String type) throws IOException {
        String s = Connection.ask_recommendations(u.getMessage().getFrom().getFirstName(), a, type);
        SendMessage sm = null;
        sm = Commands_BOT.show_recommendations(u, s);
        try {
            as.execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }
    }

    public static void run_questions(AbsSender as, Update u, HashMap<String, ArrayList<String>> c) throws TelegramApiException {
        SendMessage sm1 = new SendMessage();
        sm1 = Commands_BOT.ask_question(u, c);
        as.execute(sm1);
    }
}
