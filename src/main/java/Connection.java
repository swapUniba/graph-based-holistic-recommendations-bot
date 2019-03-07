import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Connection {


    public static String ask_recommendations(String user, ArrayList<String> user_context, String type) throws IOException {

        String rr = new String();
        String fpjson = "";

        switch (type) {
            case "athlete":
                fpjson = "data/bari/athlete.json";
                break;
            case "bad_habits":
                fpjson = "data/bari/bad_habits.json";
                break;
            case "grown_up":
                fpjson = "data/bari/grown-up.json";
                break;
        }
        //INSERT USER
        JSONObject result = new JSONObject();
        result.put("user", user);

        //INSERT PAST CONTEXT
        JSONObject behaviour = ReadFile.parseJSONFile(fpjson);
        Iterator<String> keys = behaviour.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (behaviour.get(key) instanceof JSONObject) {
                result.put(key, behaviour.get(key));
            }
        }

        //INSERT ACTUAL CONTEXT
        result.put("contesto", user_context);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost request = new HttpPost("https://graph-recommender.herokuapp.com/recommendation/post");
            StringEntity params = new StringEntity(result.toString(), "UTF-8");
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                rr = EntityUtils.toString(entity);
            }
// handle response here...
        } catch (Exception ex) {
            // handle exception here
        } finally {
            httpClient.close();
        }
        return rr.toString();
    }
}
