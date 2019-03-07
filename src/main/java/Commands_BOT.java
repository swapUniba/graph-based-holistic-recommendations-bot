import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Commands_BOT {


    public static HashMap<String, String> getPlacesNew() throws IOException {
        File file = new File("data/bari/new_businesses_bari.csv");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        String[] all;
        HashMap<String, String> dict = new HashMap<String, String>();
        br.readLine();
        while ((st = br.readLine()) != null) {
            all = st.split(",");
            //System.out.println(all[0]);
            String url = all[0];
            dict.put(all[1].replaceAll("'", "").trim(), url);
        }

        br.close();
        return dict;

    }

    public static SendMessage showMenu(Update u) {
        SendMessage menu = new SendMessage();
        menu.setChatId(u.getMessage().getChatId());
        menu.setText("Hello " + u.getMessage().getFrom().getFirstName() + ", tell me what predefined mood you belong:\n1- /Athlete" +
                "\n2- /BadHabits\n3- /GrownUp\n");
        return menu;
    }

    public static SendMessage ask_question(Update u, HashMap<String, ArrayList<String>> c) {
        SendMessage q = new SendMessage();
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        for (Map.Entry<String, ArrayList<String>> entry : c.entrySet()) {
            String k = entry.getKey();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            ArrayList<String> vs = entry.getValue();
            for (String x : vs) {
                rowInline.add(new InlineKeyboardButton(x).setCallbackData(x));
            }
            rowsInline.add(rowInline);
        }

        List<InlineKeyboardButton> end = new ArrayList<>();
        end.add(new InlineKeyboardButton("End").setCallbackData("End"));
        rowsInline.add(end);
        // Set the keyboard to the markup
        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        q.setReplyMarkup(markupInline);
        q.setChatId(u.getMessage().getChatId());
        q.setText("Insert your actual context\n - Please choose just one value for each row. - \n");
        return q;
    }

    public static SendMessage show_recommendations(Update u, String result) throws IOException {
        SendMessage msg2send = new SendMessage();
        String text = "";

        HashMap<String, String> dizionario = getPlacesNew();
        /*for (Map.Entry<String, String> entry : dizionario.entrySet()){
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }*/

        HashMap<String, ArrayList<String>> contesti = new HashMap<>();
        contesti = ReadFile.getContestoGrado("data/bari/contesti.txt");


        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();

        JSONObject parameters = new JSONObject(result);
        Iterator<String> par_names = parameters.keys();
        while (par_names.hasNext()) {
            String single_par = par_names.next();
            if (parameters.get(single_par) instanceof JSONObject) {
                JSONObject res = parameters.getJSONObject(single_par);
                JSONArray keys_indexing = res.names();

                for (int i = 0; i < keys_indexing.length(); i++) {
                    text += "###\t\t" + keys_indexing.getString(i) + "\n";
                    if (dizionario.containsKey(keys_indexing.getString(i))) {
                        text += dizionario.get(keys_indexing.getString(i)) + "\n";
                    }
                }
            }
        }
        /*for (String x : contesti) {
            KeyboardRow kbr = new KeyboardRow();
            kbr.add(new KeyboardButton(x));
            rows.add(kbr);
        }*/
        /*for (Map.Entry<String, ArrayList<String>> entry : contesti.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
            String k = entry.getKey();
            KeyboardRow kbr = new KeyboardRow();
            ArrayList<String> vs = entry.getValue();
            for (String x : vs) {
                kbr.add(new KeyboardButton(x));
            }
            rows.add(kbr);
        }

        markup.setKeyboard(rows);
        msg2send.setReplyMarkup(markup);*/
        msg2send.setChatId(u.getMessage().getChatId());
        msg2send.setText(text);
        return msg2send;
    }
}
