package com.splashpapers.chalktalk.timetable;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.splashpapers.chalktalk.notices.NoticesRequest;
import com.splashpapers.chalktalk.timetable.model.TimeTableModel;
import com.splashpapers.chalktalk.timetable.model.TimeTableVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by manishsharma on 11/24/16.
 */
public class TimeTableRequest {
        protected Context context;
        protected ProgressDialog dialog;
        private static final String TAG = NoticesRequest.class.getSimpleName();
    private String key;

       TimeTableFragmentMonday fragment;
        TimeTableFragmentTuesday fragmentTuesday;
    TimeTableFragmentFriday fragmentFriday;
    TimeTableFragmentSaturday fragmentSaturday;
    TimeTableFragmentWednesday fragmentWednesday;
    TimeTableFragmentThrusday fragmentThrusday;

        /**
         * Pattern to check if the value of the key is {}
         */
        private static final String emptyField = "\\{\\}(?=(?:[^\"\\\\]*(?:\\.|\"(?:[^\"\\\\]*\\.)*[^\"\\\\]*\"))*[^\"]*$)";

        public TimeTableRequest(Context context) {
            this.context = context;
        }

        public boolean callRequest(final String action, String funcName, final HashMap<String, String> parameters) {

            Log.d("Call Func", "Function Name :"+funcName+" parameter "+parameters);
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
                        try {
                            loginResponse = new JSONObject(response);
                            if (loginResponse != null) {
                                String status = loginResponse.getString(Constants.STATUS);
                                switch (status) {
                                    case Constants.OK:
//                                        showToast(loginResponse.toString());
                                        List<TimeTableVO> listToSave = getListHomeWork(loginResponse.getJSONArray("data"));
                                        persistList(listToSave);
                                        if(action.equals("MON")) {
                                            fragment.mAdapter.clear();
                                            for (TimeTableVO course : listToSave) {
                                                fragment.mAdapter.add(course);
                                            }
                                            fragment.mAdapter.notifyDataSetChanged();
                                        } else if(action.equals("TUE")) {
                                            fragmentTuesday.mAdapter.clear();
                                            for (TimeTableVO course : listToSave) {
                                                fragmentTuesday.mAdapter.add(course);
                                            }
                                            fragmentTuesday.mAdapter.notifyDataSetChanged();
                                        }else if(action.equals("WED")) {
                                            fragmentWednesday.mAdapter.clear();
                                            for (TimeTableVO course : listToSave) {
                                                fragmentWednesday.mAdapter.add(course);
                                            }
                                            fragmentWednesday.mAdapter.notifyDataSetChanged();
                                        }else if(action.equals("THU")) {
                                            fragmentThrusday.mAdapter.clear();
                                            for (TimeTableVO course : listToSave) {
                                                fragmentThrusday.mAdapter.add(course);
                                            }
                                            fragmentThrusday.mAdapter.notifyDataSetChanged();
                                        }else if(action.equals("FRI")) {
                                            fragmentFriday.mAdapter.clear();
                                            for (TimeTableVO course : listToSave) {
                                                fragmentFriday.mAdapter.add(course);
                                            }
                                            fragmentFriday.mAdapter.notifyDataSetChanged();
                                        }else if(action.equals("SAT")) {
                                            fragmentSaturday.mAdapter.clear();
                                            for (TimeTableVO course : listToSave) {
                                                fragmentSaturday.mAdapter.add(course);
                                            }
                                            fragmentSaturday.mAdapter.notifyDataSetChanged();
                                        }

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


        private List<TimeTableVO> getListHomeWork(JSONArray courseListModel) {
            List<TimeTableVO> mItems = new ArrayList<TimeTableVO>();


            if (courseListModel != null && courseListModel.length() > 0) {

                for (int i = 0; i < courseListModel.length(); i++) {



                    TimeTableVO homeWorkVO = new TimeTableVO();

                    JSONObject courseObject = null;

                    try {
                        courseObject = courseListModel.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }




                    homeWorkVO.setSubjet(getStringFromJSON(courseObject, "subject", ""));
                    homeWorkVO.setName(getStringFromJSON(courseObject, "name", ""));
                    homeWorkVO.setTime(getStringFromJSON(courseObject, "time_start", ""));
//                noticesVO.setProgramName(getStringFromJSON(courseObject, "programName", ""));


                    mItems.add(homeWorkVO);



                }

            }
            updateVisibilityList(mItems.size());


            return mItems;
        }

        private void persistList(List<TimeTableVO> list)
        {
            TimeTableModel.getInstance().setTimeTableList(getKey(),list);
            TimeTableModel.getInstance().persistData();
        }

        private void updateVisibilityList(int sizeList) {
//        if (NoticesFragment.key.equals(key)) {
            TimeTableFragment.setVisibilityEmptyList(TimeTableFragment.mEmptyList, sizeList);
//        }
        }

    public TimeTableFragmentMonday getFragment() {
        return fragment;
    }

    public void setFragment(TimeTableFragmentMonday fragment) {
        this.fragment = fragment;
    }

    public TimeTableFragmentTuesday getFragmentTuesday() {
        return fragmentTuesday;
    }

    public void setFragmentTuesday(TimeTableFragmentTuesday fragmentTuesday) {
        this.fragmentTuesday = fragmentTuesday;
    }

    public TimeTableFragmentFriday getFragmentFriday() {
        return fragmentFriday;
    }

    public void setFragmentFriday(TimeTableFragmentFriday fragmentFriday) {
        this.fragmentFriday = fragmentFriday;
    }

    public TimeTableFragmentSaturday getFragmentSaturday() {
        return fragmentSaturday;
    }

    public void setFragmentSaturday(TimeTableFragmentSaturday fragmentSaturday) {
        this.fragmentSaturday = fragmentSaturday;
    }

    public TimeTableFragmentWednesday getFragmentWednesday() {
        return fragmentWednesday;
    }

    public void setFragmentWednesday(TimeTableFragmentWednesday fragmentWednesday) {
        this.fragmentWednesday = fragmentWednesday;
    }

    public TimeTableFragmentThrusday getFragmentThrusday() {
        return fragmentThrusday;
    }

    public void setFragmentThrusday(TimeTableFragmentThrusday fragmentThrusday) {
        this.fragmentThrusday = fragmentThrusday;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}





