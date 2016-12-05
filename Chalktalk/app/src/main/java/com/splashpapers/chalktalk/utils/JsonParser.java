package com.splashpapers.chalktalk.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by manishsharma on 11/20/16.
 */
public class JsonParser {

    private static final String TAG = JsonParser.class.getSimpleName();

    /**
     * Create a String from a file
     *
     * @param context Context
     * @param fileName JsonParser file to read
     * @return String with the file content
     */
    public static String loadJSONFromAsset(Context context, String fileName) {
        String json = "";

        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return json;
    }

    /**
     * Get a String from a JSONObject, if the key doesn't exists return a default value
     *
     * @param object JSONObject to search the key
     * @param key Key to search in JSONObject
     * @param defaultValue Return this default value if key doesn't exists
     * @return String if key exists
     */
    public static String getString(JSONObject object, String key, String defaultValue) {
        String value = defaultValue;
        try {
            Object valueObject = object.get(key);
            if (valueObject.getClass().equals(String.class)) {
                value = valueObject.toString();
            }
        } catch (JSONException e) {
            Log.e(TAG, "Cannot get the string from: " + object + " with the key: " + key + " . Error: " + e.getMessage());
        }
        return value;
    }

    /**
     * Get JSONObject from string object
     *
     * @param object string of json object
     * @param key Key to search in JSONObject
     * @return JSONObject
     */
    public static JSONObject getJSONObject(String object, String key) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = createJSONObject(object).getJSONObject(key);
        } catch (JSONException e) {
            Log.e(TAG, "Cannot create JSONObject from: " + object + " with the key: " + key + " Error: " + e.getMessage());
        }
        return jsonObject;
    }

    /**
     * Create JSONArray from string object
     *
     * @param object String object to search the JSONArray
     * @param key String key to search in object
     * @return JSONArray
     */
    public static JSONArray getJSONArray(String object, String key) {
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = createJSONObject(object).getJSONArray(key);
        } catch (JSONException e) {
            Log.e(TAG, "Cannot create jsonArray from: " + object + " with the key: " + key + " Error: " + e.getMessage());
        }
        return jsonArray;
    }

    /**
     * Create a JSONObject from string object
     *
     * @param object string object
     * @return JSONObject
     */
    public static JSONObject createJSONObject(String object) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(object);
        } catch (JSONException e) {
            Log.e(TAG, "Cannot create JSONObject with: " + object + ". Error: " + e.getMessage());
        }
        return jsonObject;
    }

    /**
     * Create a JSONArray from string array
     *
     * @param object string object
     * @return JSONObject
     */
    public static JSONArray createJSONArray(String object) {
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = new JSONArray(object);
        } catch (JSONException e) {
            Log.e(TAG, "Cannot create JSONArray with: " + object + ". Error: " + e.getMessage());
        }
        return jsonArray;
    }

    /**
     * Get the value of one JSONObject from the key
     * @param object JSONObject to get the value
     * @param key String has contains the key
     * @param defaultValue String value if the key does not exists
     * @return String value
     */
    public static boolean getBoolean(JSONObject object, String key, boolean defaultValue) {
        boolean value = defaultValue;
        try {
            value = object.getBoolean(key);
        } catch (JSONException e) {
            Log.e(TAG, "Cannot get the boolean from: " + object + " with the key: " + key + " . Error: " + e.getMessage());
        }
        return value;
    }
}