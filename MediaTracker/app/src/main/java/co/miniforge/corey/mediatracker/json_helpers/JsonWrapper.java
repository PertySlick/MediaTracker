package co.miniforge.corey.mediatracker.json_helpers;

import org.json.JSONObject;

/**
 * Created by Perty on 10/26/2017.
 */

public class JsonWrapper {

    // Get value from json Object
    public static Object get(String tag, JSONObject jsonObject) {
        try {
            return jsonObject.get(tag);
        } catch (Exception e) {
            JSONExceptionLogger.logError(e);
        }

        return null;
    }

    // Put value into json object with tag
    public static boolean put(String tag, Object o, JSONObject jsonObject) {
        try {
            jsonObject.put(tag, o);
        } catch (Exception e) {
            JSONExceptionLogger.logError(e);
        }

        return false;
    }
}
