package com.splashpapers.chalktalk.aboutus;

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
import com.splashpapers.chalktalk.aboutus.model.AboutUsModel;
import com.splashpapers.chalktalk.aboutus.model.AboutUsVO;
import com.splashpapers.chalktalk.app.AppController;
import com.splashpapers.chalktalk.app.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by manishsharma on 11/27/16.
 */
public class AboutUsRequest {
    protected Context context;
    protected ProgressDialog dialog;
    private static final String TAG = AboutUsRequest.class.getSimpleName();

    /**
     * Pattern to check if the value of the key is {}
     */
    private static final String emptyField = "\\{\\}(?=(?:[^\"\\\\]*(?:\\.|\"(?:[^\"\\\\]*\\.)*[^\"\\\\]*\"))*[^\"]*$)";

    public AboutUsRequest(Context context) {
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
                    AboutUsModel.getInstance().persistData();
//                    Toast.makeText(context,""+response,Toast.LENGTH_SHORT).show();
                    Log.d("RESPONSE "," RESPONSE"+response);

                    // Get login response

                    JSONObject abtusResponse;
                    JSONArray abtusResponseData;

                    try {
                        abtusResponse = new JSONObject(response);
                        if (abtusResponse != null) {
                            String status = abtusResponse.getString(Constants.STATUS);
                            abtusResponseData = abtusResponse.getJSONArray("data");
                            switch (status) {
                                case Constants.OK:

                                    if (abtusResponseData != null) {
                                        String aboutus = abtusResponseData.getJSONObject(0).getString("aboutus");
                                        String location = abtusResponseData.getJSONObject(0).getString("location");
                                        String imageUrl = abtusResponseData.getJSONObject(0).getString("image_url");
                                        AboutUsVO aboutUsVO = new AboutUsVO();
                                        aboutUsVO.setAboutUsText(aboutus);
                                        aboutUsVO.setLocation(location);
                                        aboutUsVO.setImageURL(imageUrl);
//                                        aboutUsVO.setImageURL("http://www.ramagyaschool.com/wp-content/uploads/2014/12/RD-RW-DVD-11-0085.jpg");
//                                        Log.d("abt","feq"+aboutUsData.toString());
                                        persistData(aboutUsVO);
                                        AboutUsFragment.setData(context);
                                    }




                                    break;
                                case Constants.NOK:
                                    showToast(getStringFromJSON(abtusResponse, Constants.MESSAGE, getContext().getString(R.string.defaultError)));
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

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    public Context getContext() {
        return this.context;
    }



    protected void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private void persistData(AboutUsVO aboutUsVO)
    {
        AboutUsModel.getInstance().setAboutUs("aboutus",aboutUsVO);
        AboutUsModel.getInstance().persistData();
    }
}
