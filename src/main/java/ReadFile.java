import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ReadFile {
    public static ArrayList<String> getContesti(String filename) {
        File file = new File(filename);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String st = "";
        String[] all;
        ArrayList<String> contesti = new ArrayList<String>();
        String appartenenza_contesti = "";
        while (true) {
            ArrayList<String> temp = new ArrayList<>();
            try {
                if (!((st = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            all = st.split("=");
            contesti.add(all[1]);
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contesti;
    }

    public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }
}
