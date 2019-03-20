import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class mine_bot extends TelegramLongPollingBot {

    ArrayList<String> user_context = new ArrayList<>();
    HashMap<String, ArrayList<String>> contesti = new HashMap<>();
    HashMap<String, String> valore_contesto = new HashMap<>();

    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String command = update.getMessage().getText();

            if (command.equals("/start")) {
                user_context.clear();
                contesti = ReadFile.getContestoGrado("data/bari/contesti.txt");
                valore_contesto = ReadFile.getGradoContesto("data/bari/contesti.txt");
                try {
                    run.run_questions(this, update, contesti);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


            if (command.equals("/healthy")) {
                try {
                    run.run_experiment(this, user_context, update, "healthy");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (command.equals("/unhealthy")) {
                try {
                    run.run_experiment(this, user_context, update, "unhealthy");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (command.equals("/family")) {
                try {
                    run.run_experiment(this, user_context, update, "family");
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
                    if(valore_contesto.containsKey(call_data)){
                        ArrayList<String> temp_available_valori = contesti.get(valore_contesto.get(call_data));
                        for (String x: temp_available_valori){
                            if(x != call_data && user_context.contains(x)){
                                user_context.remove(x);
                            }
                        }
                    }
                    user_context.add(call_data);
                    System.out.println(call_data);
                }
            }else{
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
        return "700184595:AAFMeoNS7EN4AjmuAEuzRCnQ49qdWqVmNGg";
    }


}
