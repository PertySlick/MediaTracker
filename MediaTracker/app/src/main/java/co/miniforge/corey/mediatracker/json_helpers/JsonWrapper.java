package co.miniforge.corey.mediatracker.json_helpers;

import org.json.JSONObject;

/**
 * Utility class for providing reusable methods for JSON Object related operations including get()
 * and put();
 *
 * @author Timothy Roush
 * @version 1.0
 */

public class JsonWrapper {

    /**
     * Returns the requested object from the specified JSON Object.
     * @param tag String property of the JSON Object to retrieve
     * @param jsonObject JSONObject JSON object to retrieve from
     * @return Object value stored for specified tag
     */
    public static Object get(String tag, JSONObject jsonObject) {
        try {
            return jsonObject.get(tag);
        } catch (Exception e) {
            JSONExceptionLogger.logError(e);
        }
        return null;
    }

    /**
     * Places the specified Object into the provided JSONObject within the supplied tag value.
     * @param tag String property to associate Object with
     * @param o Object value or object to be placed within JSONObject
     * @param jsonObject JSONObject JSON object to place value in
     * @return true if successful, false otherwise
     */
    public static boolean put(String tag, Object o, JSONObject jsonObject) {
        try {
            jsonObject.put(tag, o);
            return true;
        } catch (Exception e) {
            JSONExceptionLogger.logError(e);
        }
        return false;
    }
}
