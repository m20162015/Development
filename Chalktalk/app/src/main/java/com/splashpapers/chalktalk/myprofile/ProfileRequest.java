package com.splashpapers.chalktalk.myprofile;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.splashpapers.chalktalk.myprofile.model.MyProfileModel;
import com.splashpapers.chalktalk.myprofile.model.MyProfileVO;
import com.splashpapers.chalktalk.notices.NoticesRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by manishsharma on 12/10/16.
 */

public class ProfileRequest {

    protected Context context;
    protected ProgressDialog dialog;
    private static final String TAG = NoticesRequest.class.getSimpleName();

    /**
     * Pattern to check if the value of the key is {}
     */
    private static final String emptyField = "\\{\\}(?=(?:[^\"\\\\]*(?:\\.|\"(?:[^\"\\\\]*\\.)*[^\"\\\\]*\"))*[^\"]*$)";

    public ProfileRequest(Context context) {
        this.context = context;
    }

    public boolean callRequest(String funcName, final HashMap<String, String> parameters) {

        Log.d("Call Func", "Function Name :"+funcName+" parameter "+parameters);
        if(isConnectedToInternet(context)) {

            this.dialog = new ProgressDialog(context);
            this.dialog.setCancelable(false);
            this.dialog.setMessage(this.getContext().getString(R.string.loading));
            this.dialog.show();

            SharedPreferences user = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, user.getString(Constants.BASE_URL_LOGIN,"")+ "mobile1/"+ funcName,new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    dialog.dismiss();
//                    Toast.makeText(context,""+response,Toast.LENGTH_SHORT).show();
                    Log.d("RESPONSE "," RESPONSE"+response);

                    // Get login response

                    JSONObject loginResponse;

                    try {
                        loginResponse = new JSONObject(response);
                        if (loginResponse != null) {
                            String status = loginResponse.getString(Constants.STATUS);
                            switch (status) {
                                case Constants.OK:
//                                        showToast(loginResponse.toString());
                                        MyProfileVO myProfileVO = getProfileData(loginResponse.getJSONArray("data"));
                                    persistList(myProfileVO);

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

            AppController.getInstance().addToRequestQueue(stringRequest);
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


    private MyProfileVO getProfileData(JSONArray courseListModel) {
        MyProfileVO myProfileVO = new MyProfileVO();
        if (courseListModel != null && courseListModel.length() > 0) {

            for (int i = 0; i < courseListModel.length(); i++) {

                JSONObject courseObject = null;

                try {
                    courseObject = courseListModel.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }




                myProfileVO.setName(getStringFromJSON(courseObject, "name", "-"));
                myProfileVO.setEmail(getStringFromJSON(courseObject, "email", "-"));
                myProfileVO.setImageURL(getStringFromJSON(courseObject, "image_url", "-"));
                myProfileVO.setPhone(getStringFromJSON(courseObject, "phone", "-"));
                myProfileVO.setAddress(getStringFromJSON(courseObject, "address", "-"));
            }

        }


        return myProfileVO;

    }

    private void persistList(MyProfileVO myProfileVO)
    {
        MyProfileModel.getInstance().setMyProfile("myprofile",myProfileVO);
        MyProfileModel.getInstance().persistData();
    }

}
