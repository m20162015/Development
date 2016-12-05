package com.splashpapers.chalktalk.myprofile.model;

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
 * Created by manishsharma on 11/30/16.
 */
public class MyProfileModel implements Serializable {

    private static final long serialVersionUID = 5637846962633230043L;

    private static String TAG = MyProfileModel.class.getSimpleName();
    private static MyProfileModel sMyProfileModel;
    private static String FILENAME = "MyProfileModel.fwd";
    private HashMap<String, HashMap<String, Object>> profileList = new HashMap<>();

    private MyProfileModel() {
        loadData();
    }

    public static MyProfileModel getInstance() {
        if (sMyProfileModel == null) {
            synchronized (MyProfileModel.class) {
                sMyProfileModel = new MyProfileModel();
            }
        }
        return sMyProfileModel;
    }

    private void loadData() {
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = Constants.CONTEXT.openFileInput(FILENAME);
            ois = new ObjectInputStream(fis);

            sMyProfileModel = (MyProfileModel) ois.readObject();
            ois.close();
            fis.close();
            profileList = sMyProfileModel.profileList;
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
                out.writeObject(sMyProfileModel);
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
            profileList.clear();
        } catch (Exception e) {
            Log.e(TAG, "Error trying delete file ");
        }
    }

    public void setMyProfile(String key, MyProfileVO profile) {
        HashMap<String, Object> entry = new HashMap<>();
        entry.put("profile", profile);
        entry.put("lastUpdate", new Date());
        entry.put("isRefresh", false);
        profileList.put(key, entry);
    }

    @SuppressWarnings("unchecked")
    public MyProfileVO getMyProfile(String key) {
        checkIfKeyExists(key);
        return (MyProfileVO) this.profileList.get(key).get("profile");
    }

    public Date getLastUpdate(String key) {
        checkIfKeyExists(key);
        return (Date) profileList.get(key).get("lastUpdate");
    }

    public void setLastUpdate(String key) {
        checkIfKeyExists(key);
        profileList.get(key).put("lastUpdate", new Date());
    }

    public void setLastRefresh(String key, Boolean isRefresh) {
        profileList.get(key).put("isRefresh", isRefresh);
    }

    public boolean getLastRefresh(String key) {
        return (Boolean) this.profileList.get(key).get("isRefresh");
    }

    private void checkIfKeyExists(String key) {
        if (!profileList.containsKey(key)) {
            HashMap<String, Object> entry = new HashMap<>();
            entry.put("lastUpdate", new Date(0));
            entry.put("profile", null);
            entry.put("isRefresh", false);
            profileList.put(key, entry);
        }
    }
}

