import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;

public class mine_bot extends TelegramLongPollingBot {


    public void onUpdateReceived(Update update) {

        String command = update.getMessage().getText();
        String p = "";

        ArrayList<String> ac = new ArrayList<>();
        ac.add("amici");
        ac.add("weekend");

        if (command.equals("/Athlete")){
            try {
                p = Connection.ask2server(update.getMessage().getFrom().getFirstName(), ac,"athlete");
                //System.out.println(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
            SendMessage sm = Commands_BOT.give_recommendations(update, p);
            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if (command.equals("/BadHabits")){
            try {
                p = Connection.ask2server(update.getMessage().getFrom().getFirstName(), ac,"bad_habits");
                //System.out.println(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
            SendMessage sm1 = Commands_BOT.give_recommendations(update, p);
            try {
                execute(sm1);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if (command.equals("/GrownUp")){
            try {
                p = Connection.ask2server(update.getMessage().getFrom().getFirstName(), ac,"grown_up");
                //System.out.println(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
            SendMessage sm = Commands_BOT.give_recommendations(update, p);
            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if (command.equals("/recommendations")) {
            SendMessage menu = Commands_BOT.showMenu(update);
            try {
                execute(menu);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return "Maurizio, sescè!";
    }

    public String getBotToken() {
        return "";
    }


}
