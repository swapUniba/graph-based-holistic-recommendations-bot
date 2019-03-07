import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Commands_BOT {

    public static SendMessage showMenu(Update u) {
        SendMessage menu = new SendMessage();
        menu.setChatId(u.getMessage().getChatId());
        menu.setText("Hello " + u.getMessage().getFrom().getFirstName() + ", tell me what predefined mood you belong:\n1- /Athlete\n2- /BadHabits\n3- /GrownUp\n");
        return menu;
    }

    public static SendMessage give_recommendations(Update u, String result) {
        SendMessage msg2send = new SendMessage();
        String text = "";

        //ArrayList<String> contesti = new ArrayList();
        //contesti = ReadFile.getContesti("data/bari/contesti.txt");


        //ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        //List<KeyboardRow> rows = new ArrayList<>();

        JSONObject r = new JSONObject(result);
        Iterator<String> keys = r.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (r.get(key) instanceof JSONObject) {
                JSONObject res = r.getJSONObject(key);
                JSONArray keys_indexing = res.names();
                for (int i = 0; i < keys_indexing.length(); i++) {
                    text += (i + 1) + ") " + keys_indexing.getString(i) + "\n";
                }
            }
        }
       /* for (String x : result) {
            KeyboardRow kbr = new KeyboardRow();
            kbr.add(new KeyboardButton(x));
            rows.add(kbr);
        }*/

        /*markup.setKeyboard(rows);
        msg2send.setReplyMarkup(markup);*/
        msg2send.setChatId(u.getMessage().getChatId());
        msg2send.setText(text);
        return msg2send;
    }
}
