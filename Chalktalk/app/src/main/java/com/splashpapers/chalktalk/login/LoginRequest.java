package com.splashpapers.chalktalk.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.app.AppController;
import com.splashpapers.chalktalk.app.Constants;
import com.splashpapers.chalktalk.home.HomeActivity;
import com.splashpapers.chalktalk.utils.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by manishsharma on 11/9/16.
 */
public class LoginRequest {

    protected Context context;
    protected ProgressDialog dialog;
    private boolean rememberFlag;
    private String username;
    private String password;
    private static final String TAG = LoginRequest.class.getSimpleName();

    /**
     * Pattern to check if the value of the key is {}
     */
    private static final String emptyField = "\\{\\}(?=(?:[^\"\\\\]*(?:\\.|\"(?:[^\"\\\\]*\\.)*[^\"\\\\]*\"))*[^\"]*$)";

    public LoginRequest(Context context, boolean rememberFlag, String username, String password) {
        this.context = context;
        this.rememberFlag = rememberFlag;
        this.username = username;
        this.password = password;

    }

    public boolean callRequest(String funcName, final HashMap<String, String> parameters) {

        if(isConnectedToInternet(context)) {

                this.dialog = new ProgressDialog(context);
                this.dialog.setCancelable(false);
                this.dialog.setMessage(this.getContext().getString(R.string.loading));
                this.dialog.show();


            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.BASE_URL+ "mobile1/"+ funcName,new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    dialog.dismiss();
//                    Toast.makeText(context,""+response,Toast.LENGTH_SHORT).show();
                    Log.d("RESPONSE "," RESPONSE"+response);

                    // Get login response

                   JSONObject loginResponse;
                   JSONObject loginResponseData;

                    try {
                        loginResponseData = new JSONObject(response);
                        loginResponse = loginResponseData.getJSONObject("data");

                        if (loginResponse != null) {
                            String status = loginResponse.getString(Constants.STATUS);
                            switch (status) {
                                case Constants.OK:
                                    saveUserPassword(username,password);
//                                    String userId = JsonParser.getString(loginResponse, Constants.USER_ID, "");
//                                    String roleId = JsonParser.getString(loginResponse, Constants.ROLE_ID, "");
//                                    String userNC = JsonParser.getString(loginResponse, Constants.USER_NC, "");
//                                    String userToken = JsonParser.getString(loginResponse, Constants.USER_TOKEN, Constants.NO_MOODLE);
//                                    String name = JsonParser.getString(loginResponse, Constants.NAME, "");
//                                    String shortName = JsonParser.getString(loginResponse, Constants.SHORT_NAME, "");
//                                    String profile = JsonParser.getString(loginResponse, Constants.PROFILE, "");
//                                    String userData = JsonParser.getJSONObject(loginResponse.toString(), Constants.USER_DATA).toString();
//                                    String email = JsonParser.getString(loginResponse, Constants.EMAIL, "-");
//
//                                    boolean isMultiprofile = false;
//                                    if (roleId.equals("")) {
//                                        isMultiprofile = true;
//                                    }

                                    // persist login information
                                    SharedPreferences sharedPref = getContext().getSharedPreferences(getContext().getPackageName(), Context.MODE_PRIVATE);
                                    SharedPreferences.Editor edit = sharedPref.edit();
                                    edit.putBoolean(Constants.LOGIN, true);
                                    edit.putString(Constants.CHILDREN_LIST,(JsonParser.getJSONObject(loginResponse.toString(), "0").toString()).toString());
                                    edit.putString(Constants.AUTH_KEY, JsonParser.getString(loginResponse, Constants.AUTH_KEY, ""));
                                    edit.putString(Constants.NAME, JsonParser.getString(loginResponse, Constants.NAME, ""));
                                    edit.putString(Constants.LOGIN_TYPE, JsonParser.getString(loginResponse, Constants.LOGIN_TYPE, ""));
                                    edit.putString(Constants.LOGIN_USER_ID, JsonParser.getString(loginResponse, Constants.LOGIN_USER_ID, ""));
                                    edit.putString(Constants.INSTITUTE_NAME, JsonParser.getString(loginResponse.getJSONObject("0"), Constants.INSTITUTE_NAME, ""));
                                    edit.putString(Constants.BASE_URL_LOGIN, JsonParser.getString(loginResponse.getJSONObject("0"), Constants.BASE_URL_LOGIN, ""));
                                    edit.commit();

                                    // Launch Home Dashboard Activity
                                    Context contextApp = getContext();
                                    Intent intent = new Intent(contextApp, HomeActivity.class);
                                    contextApp.startActivity(intent);

//                                    WelcomeActivity.finishActivity();


                                    // Finish Activity Login
                                    Activity activity = (Activity) getContext();
                                    activity.finish();
                                    break;
                                case Constants.NOK:
                                    showToast(getStringFromJSON(loginResponse, Constants.MESSAGE, getContext().getString(R.string.defaultError)));
                                    break;
                                default:
                                    showToast(getContext().getString(R.string.defaultError));
                                    break;
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            dialog.dismiss();
//                            Toast.makeText(context,""+error,Toast.LENGTH_SHORT).show();
                        }
                    })
            {
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = parameters;
                    return params;
                }
            };

//            RequestQueue requestQueue = Volley.newRequestQueue(context);
            AppController.getInstance().addToRequestQueue(stringRequest);
//            requestQueue.add(stringRequest);

            return true;
        }
        else {
            Toast.makeText(context,getContext().getString(R.string.network_unavailble),Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    public Context getContext() {
        return this.context;
    }

    private void saveUserPassword(String userName, String password)
    {

        SharedPreferences sharedPref = getContext().getSharedPreferences(getContext().getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        if(rememberFlag) {
            edit.putString(Constants.USERNAME, userName);
            edit.putString(Constants.PASSWORD, password);
        } else {
            edit.putString(Constants.USERNAME, "");
            edit.putString(Constants.PASSWORD, "");
        }
        edit.putBoolean(Constants.REMEMBER_FLAG, rememberFlag);
        edit.commit();
    }

    protected String getStringFromJSON(JSONObject object, String key, String defaultValue) {
        String value = defaultValue;
        try {
            value = object.getString(key);
            if (value.matches(emptyField)) {
                value = defaultValue;
            }
        } catch (JSONException e) {
            Log.e(TAG, "Cannot find the key = " + key);
        }
        return value;
    }

    protected void showToast(String message) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    protected ArrayList<HashMap<String, String>> getListFromArray(JSONObject object, String key, String[] keys) {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        try {
            for (int i = 0; i < object.getJSONArray(key).length(); i++) {
                JSONObject itemObject = object.getJSONArray(key).getJSONObject(i);
                HashMap<String, String> entry = new HashMap<>();

                for (String k : keys) {
                    entry.put(k,itemObject.getString(k));
                }

                arrayList.add(entry);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

}

