import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class mine_bot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
        String command = update.getMessage().getText();
        SendMessage comeback = new SendMessage();
        String r;
        if(command.equals("/tellmemyname")){
            r = update.getMessage().getFrom().getFirstName();
             comeback.setText(r);
        }
        comeback.setChatId(update.getMessage().getChatId());
        try {
            execute(comeback);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "Maurizio, sescè!";
    }

    public String getBotToken() {
        return "";
    }
}
