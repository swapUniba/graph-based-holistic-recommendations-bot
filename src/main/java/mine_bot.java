import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class mine_bot extends TelegramLongPollingBot {

    ArrayList<String> user_context = new ArrayList<>();

    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String command = update.getMessage().getText();
            String p = "";
            HashMap<String, ArrayList<String>> contesti = new HashMap<>();
            contesti = ReadFile.getContestoGrado("data/bari/contesti.txt");

            if (command.equals("/start")) {
                try {
                    run.run_questions(this, update, contesti);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


            if (command.equals("/Athlete")) {
                try {
                    run.run_experiment(this, user_context, update, "athlete");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (command.equals("/BadHabits")) {
                try {
                    run.run_experiment(this, user_context, update, "bad_habits");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (command.equals("/GrownUp")) {
                try {
                    run.run_experiment(this, user_context, update, "grown_up");
                } catch (IOException e) {
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


        } else if (update.hasCallbackQuery()) {
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if(!call_data.equals("End")){
                if(!user_context.contains(call_data)){
                    user_context.add(call_data);
                    System.out.println(call_data);
                }
            }else{
                System.out.println("ciao");
                SendMessage msg = new SendMessage();
                msg.setChatId(chat_id);
                msg.setText("Well done! This is your context:\n"+user_context.toString()+"\nNow digits /recommendations ");
                System.out.println(user_context);
                try {
                    this.execute(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
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
