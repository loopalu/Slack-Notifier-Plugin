package ee.loopalu.slacknotifier.slack;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static ee.loopalu.slacknotifier.configuration.SettingsManager.getInstance;

public class SlackClient {

    private static final String webhook = StringUtils.firstNonBlank(getInstance().getState().webhook, "");
    private static final String channelName = StringUtils.firstNonBlank(getInstance().getState().channelId, "");

    public static void postSlackMessage(String message) throws IOException {
        String input = "payload=" + URLEncoder.encode(getPayloadMessage(message), StandardCharsets.UTF_8);

        try {
            URL url = new URL(webhook);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            DataOutputStream outputStream = new DataOutputStream (connection.getOutputStream ());
            outputStream.writeBytes (input);
            outputStream.flush ();
            outputStream.close ();

            if (connection.getResponseCode() == 200 && readInputStreamToString(connection).equals("ok")) {
                System.out.println("All OK");
            } else {
                int status_code = connection.getResponseCode();
                String title = "";
                String content = "";
                if (status_code == 400) {
                    title = "invalid_payload";
                    content = "the data sent in your request cannot be understood as presented; verify your content body matches your content type and is structurally valid.";
                } else if (status_code == 403) {
                    title = "action_prohibited";
                    content = "the team associated with your request has some kind of restriction on the webhook posting in this context.";
                } else if (status_code == 404) {
                    title = "channel_not_found";
                    content = "the channel associated with your request does not exist.";
                } else if (status_code == 410) {
                    title = "channel_is_archived";
                    content = "the channel has been archived and doesn't accept further messages, even from your incoming webhook.";
                } else if (status_code == 500) {
                    title = "rollup_error";
                    content = "something strange and unusual happened that was likely not your fault at all.";
                }

                System.out.println("Error: " + title + ": " + content);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static String readInputStreamToString(HttpURLConnection connection) {
        String result;
        StringBuilder builder = new StringBuilder();
        InputStream is;

        try {
            is = new BufferedInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                builder.append(inputLine);
            }
            result = builder.toString();
        }
        catch (Exception e) {
            result = "";
        }

        return result;
    }

    public static String getPayloadMessage(String message) {

        message = message.replace("\\", "\\\\").replace("\"", "\\\"");

        String payload = "{" +
                "\"text\" : \"" + message + "\"";
        String channel = channelName;
        if (channel != null && !channel.isEmpty()) {
            payload += ",\"channel\" : \"" + channel + "\"";
        }
        payload += "}";

        return payload;
    }
}