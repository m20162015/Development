package com.splashpapers.chalktalk.notification.model;

import android.content.Context;
import android.util.Log;

import com.splashpapers.chalktalk.app.Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by my pc on 18-Nov-16.
 */

public class NotificationModel implements Serializable {
    private static final long serialVersionUID = -4047227889881088072L;
    private static String TAG = NotificationModel.class.getSimpleName();
    private static NotificationModel sNotificationModel;
    private static String FILENAME = "NotificationModel.spp";
    private HashMap<String, HashMap<String, Object>> newsList = new HashMap<>();

    private NotificationModel() {
        loadData();
    }

    public static NotificationModel getInstance() {
        if (sNotificationModel == null) {
            synchronized (NotificationModel.class) {
                sNotificationModel = new NotificationModel();
            }
        }
        return sNotificationModel;
    }

    private void loadData() {
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = Constants.CONTEXT.openFileInput(FILENAME);
            ois = new ObjectInputStream(fis);

            sNotificationModel = (NotificationModel) ois.readObject();
            ois.close();
            fis.close();
            newsList = sNotificationModel.newsList;
        } catch (IOException e) {
            Log.e(TAG, "Error while reading the file ");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Error while deserializing");
            e.printStackTrace();
        }
    }

    public void persistData() {
        FileOutputStream fos;
        ObjectOutputStream out;
        try {
            fos = Constants.CONTEXT.openFileOutput(FILENAME, Context.MODE_PRIVATE);

            try {
                out = new ObjectOutputStream(fos);
                out.writeObject(sNotificationModel);
                out.close();
            } catch (IOException e) {
                Log.e(TAG, "Error while writing the file ");
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Error while writing the file ");
            e.printStackTrace();
        }
    }

    public void removeModel() {
        FileOutputStream fos;
        ObjectOutputStream out;

        try {
            fos = Constants.CONTEXT.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            out = new ObjectOutputStream(fos);
            out.reset();
            newsList.clear();
        } catch (Exception e) {
            Log.e(TAG, "Error trying delete file ");
        }
    }

    public void setNotificationList(String key, List<NotificationVO> news) {
        HashMap<String, Object> entry = new HashMap<>();

        entry.put("list", news);
        entry.put("lastUpdate", new Date());
        entry.put("isRefresh", false);
        newsList.put(key, entry);
    }

    @SuppressWarnings("unchecked")
    public List<NotificationVO> getNotificationList(String key) {
        checkIfKeyExists(key);
        List<NotificationVO> news = (List<NotificationVO>) newsList.get(key).get("list");
        if (news != null) {
            return news;
        }
        return new ArrayList<>();
    }

    public Date getLastUpdate(String key) {
        checkIfKeyExists(key);
        return (Date) newsList.get(key).get("lastUpdate");
    }

    public void setLastUpdate(String key) {
        checkIfKeyExists(key);
        newsList.get(key).put("lastUpdate", new Date());
    }

    public void setLastRefresh(String key, Boolean isRefresh) {
        newsList.get(key).put("isRefresh", isRefresh);
    }

    public boolean getLastRefresh(String key) {
        checkIfKeyExists(key);
        return (Boolean) this.newsList.get(key).get("isRefresh");
    }

    private void checkIfKeyExists(String key) {
        if (!newsList.containsKey(key)) {
            HashMap<String, Object> entry = new HashMap<>();
            entry.put("lastUpdate", new Date(0));
            entry.put("list", null);
            entry.put("isRefresh", false);
            newsList.put(key, entry);
        }
    }
}

