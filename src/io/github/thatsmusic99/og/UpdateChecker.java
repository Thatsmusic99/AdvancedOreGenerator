package io.github.thatsmusic99.og;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

class UpdateChecker {

    private final static String versionURL = "https://api.spiget.org/v2/resources/51153/versions?size=1000";
    private final static String descriptionURL = "https://api.spiget.org/v2/resources/51153/updates?size=1000";

    static Object[] getUpdate() throws IOException {
        URL url = new URL(versionURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.addRequestProperty("User-Agent", "AdvancedOreGeneratorPluginAgent");
        InputStream inputStream = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        JSONArray versionsArray = null;
        try {
            versionsArray = (JSONArray) new JSONParser().parse(reader);
        } catch (ParseException | IOException e) {
            e.printStackTrace();

        }
        int size = Objects.requireNonNull(versionsArray).size();
        String[] lastVersion = ((JSONObject) versionsArray.get(size - 1)).get("name").toString().split("\\.");
        String[] currentVersion = OreGenerator.getInstance().getDescription().getVersion().split("\\.");
        String latestVersionString = ((JSONObject) versionsArray.get(versionsArray.size() - 1)).get("name").toString();

        url = new URL(descriptionURL);
        connection = (HttpURLConnection) url.openConnection();
        connection.addRequestProperty("User-Agent", "AdvancedOreGeneratorPluginAgent");
        inputStream = connection.getInputStream();
        reader = new InputStreamReader(inputStream);
        JSONArray updatesArray = null;
        try {
            updatesArray = (JSONArray) new JSONParser().parse(reader);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        if (!Arrays.asList(lastVersion).toString().equals(Arrays.asList(currentVersion).toString())) {

            String updateName = ((JSONObject) updatesArray.get(Objects.requireNonNull(updatesArray).size() - 1)).get("title").toString();
            return new Object[]{lastVersion, updateName, latestVersionString};
        }
        return null;
    }
}