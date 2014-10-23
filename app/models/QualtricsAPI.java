package models;

import play.Configuration;
import play.Play;
import play.libs.ws.*;

public abstract class QualtricsAPI {

    private static final Configuration conf = Play.application().configuration();
    private static final String API_BASE_URL = conf.getString("qualtrics.api.baseUrl");
    private static final String API_USERNAME = conf.getString("qualtrics.api.username");
    private static final String API_TOKEN = conf.getString("qualtrics.api.token");
    private static final String API_VERSION = conf.getString("qualtrics.api.version");
    private static final Integer API_REQUEST_TIMEOUT = conf.getInt("qualtrics.api.requestTimeout");

    public static WSRequestHolder request(String requestName) {

        return WS.url(API_BASE_URL)
                .setTimeout(API_REQUEST_TIMEOUT)
                .setQueryParameter("Request", requestName)
                .setQueryParameter("User", API_USERNAME)
                .setQueryParameter("Token", API_TOKEN)
                .setQueryParameter("Version", API_VERSION);

    }
}
