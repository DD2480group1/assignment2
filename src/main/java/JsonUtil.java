import javax.json.*;
import java.io.StringReader;
import java.util.List;

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


    public static JsonArray encodeQueryResults(List<tableEntry> rows) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (tableEntry row : rows) {
            builder.add(encodeRow(row));
        }

        return builder.build();
    }

    public static JsonObject encodeRow(tableEntry row) {
        return Json.createObjectBuilder()
                .add("commitId", row.commitId)
                .add("branch", row.branch)
                .add("timeStamp", String.valueOf(row.timeStamp))
                .add("buildInfo", row.buildInfo)
                .add("testInfo", row.testInfo)
                .build();
    }
}
