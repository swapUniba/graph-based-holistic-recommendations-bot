import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadFile {
    public static HashMap<String, ArrayList<String>> getContestoGrado(String filename) {
        File file = new File(filename);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String st = "";
        String[] all;
        HashMap<String, ArrayList<String>> contestogrado = new HashMap<>();
        while (true) {
            ArrayList<String> temp = new ArrayList<>();
            try {
                if (!((st = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            all = st.split("=");
            if(contestogrado.containsKey(all[0])){
                contestogrado.get(all[0]).add(all[1]);
            }else{
                ArrayList<String> v = new ArrayList<>();
                v.add(all[1]);
                contestogrado.put(all[0],v);
            }
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contestogrado;
    }

    public static HashMap<String, String> getGradoContesto(String filename) {
        File file = new File(filename);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String st = "";
        String[] all;
        HashMap<String, String> gradocontesto = new HashMap<>();
        while (true) {
            ArrayList<String> temp = new ArrayList<>();
            try {
                if (!((st = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            all = st.split("=");
            gradocontesto.put(all[1],all[0]);
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gradocontesto;
    }

    public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }
}
