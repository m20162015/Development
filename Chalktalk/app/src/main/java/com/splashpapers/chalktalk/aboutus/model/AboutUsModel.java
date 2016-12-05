package com.splashpapers.chalktalk.aboutus.model;

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
import java.util.Date;
import java.util.HashMap;

/**
 * Created by manishsharma on 11/27/16.
 */
public class AboutUsModel  implements Serializable {
    private static final long serialVersionUID = -4047227889881088072L;
    private static String TAG = AboutUsModel.class.getSimpleName();
    private static AboutUsModel sAboutUsModel;
    private static String FILENAME = "AboutUsModel.spp";
    private HashMap<String, HashMap<String, Object>> aboutUsList = new HashMap<>();

    private AboutUsModel() {
        loadData();
    }

    public static AboutUsModel getInstance() {
        if (sAboutUsModel == null) {
            synchronized (AboutUsModel.class) {
                sAboutUsModel = new AboutUsModel();
            }
        }
        return sAboutUsModel;
    }

    private void loadData() {
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = Constants.CONTEXT.openFileInput(FILENAME);
            ois = new ObjectInputStream(fis);

            sAboutUsModel = (AboutUsModel) ois.readObject();
            ois.close();
            fis.close();
            aboutUsList = sAboutUsModel.aboutUsList;
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
                out.writeObject(sAboutUsModel);
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
            aboutUsList.clear();
        } catch (Exception e) {
            Log.e(TAG, "Error trying delete file ");
        }
    }

    public void setAboutUs(String key, AboutUsVO profile) {
        HashMap<String, Object> entry = new HashMap<>();
        entry.put("about", profile);
        entry.put("lastUpdate", new Date());
        entry.put("isRefresh", false);
        aboutUsList.put(key, entry);
    }

    @SuppressWarnings("unchecked")
    public AboutUsVO getAboutUs(String key) {
        checkIfKeyExists(key);
        return (AboutUsVO) this.aboutUsList.get(key).get("about");
    }

    public Date getLastUpdate(String key) {
        checkIfKeyExists(key);
        return (Date) aboutUsList.get(key).get("lastUpdate");
    }

    public void setLastUpdate(String key) {
        checkIfKeyExists(key);
        aboutUsList.get(key).put("lastUpdate", new Date());
    }

    public void setLastRefresh(String key, Boolean isRefresh) {
        aboutUsList.get(key).put("isRefresh", isRefresh);
    }

    public boolean getLastRefresh(String key) {
        checkIfKeyExists(key);
        return (Boolean) this.aboutUsList.get(key).get("isRefresh");
    }

    private void checkIfKeyExists(String key) {
        if (!aboutUsList.containsKey(key)) {
            HashMap<String, Object> entry = new HashMap<>();
            entry.put("lastUpdate", new Date(0));
            entry.put("isRefresh", false);
            entry.put("about", null);
            aboutUsList.put(key, entry);
        }
    }
}


