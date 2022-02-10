import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

public class JsonUtil {
    /**
     *
     * @param payload the string to convert
     * @return json object from the provided string
     */
    public static JsonObject getJson(String payload) {
        JsonReader jsonReader = javax.json.Json.createReader(new StringReader(payload));
        return jsonReader.readObject();
    }
    /**
     *
     * @param payload the http payload of the webhook request
     * @return the url of the github repo
     */
    public static String getRepoUrl(JsonObject payload) {
        return payload.getJsonObject("repository").getString("url");
    }


    /**
     * @param payload the http payload of the webhook request
     * @return the email from the person that pushed
     */
    public static String getHeadCommitEmail(JsonObject payload) {
        return payload.getJsonObject("head_commit").getJsonObject("author").getString("email");
    }

    /**
     * @param payload the http payload of the webhook request
     * @return the id of the head commit
     */
    public static String getHeadCommitId(JsonObject payload) {
        return payload.getJsonObject("head_commit").getString("id");
    }

    /**
     * @param payload the http payload of the webhook request
     * @return the commit id from the person that pushed
     */
    public static String getCommitMsg(JsonObject payload) {
        return payload.getJsonObject("head_commit").getString("message");
    }

    /**
     * @param payload the http payload of the webhook request
     * @return the commit ref of the commit
     */
    public static String getCommitRef(JsonObject payload) {
        return payload.getString("ref");
    }




}
