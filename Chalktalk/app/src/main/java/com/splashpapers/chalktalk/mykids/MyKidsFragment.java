package com.splashpapers.chalktalk.mykids;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.app.Constants;
import com.splashpapers.chalktalk.mykids.model.MyKidsVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manishsharma on 11/30/16.
 */
public class MyKidsFragment extends Fragment {
    private static final String TAG = MyKidsFragment.class.getSimpleName();
    private static final String emptyField = "\\{\\}(?=(?:[^\"\\\\]*(?:\\.|\"(?:[^\"\\\\]*\\.)*[^\"\\\\]*\"))*[^\"]*$)";

    @SuppressWarnings("unchecked")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mykids_screen_layout, container, false);


//        TextView emptyTextMsg = (TextView)view.findViewById(R.id.participant_list_empty);
//        emptyTextMsg.setVisibility(View.GONE);
        ListView mList = (ListView) view.findViewById(R.id.list);

        // Set the adapter
        MyKidsAdapter mAdapter = new MyKidsAdapter(getActivity(), getmItems());
        Log.d("mItems","mItems"+getmItems());
        mList.setAdapter(mAdapter);

        return view;
    }

    private List<MyKidsVO> getmItems() {
        List<MyKidsVO> mItems = new ArrayList<>();

        SharedPreferences user = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
        try {
            JSONObject childrenJSONObject = new JSONObject(user.getString(Constants.CHILDREN_LIST, null));
            JSONArray childrenJSONArray = childrenJSONObject.getJSONArray("children");

            if (childrenJSONArray != null && childrenJSONArray.length() > 0) {

                for (int i = 0; i < childrenJSONArray.length(); i++) {


                    MyKidsVO myKidsVO = new MyKidsVO();

                    JSONObject courseObject = null;

                    try {
                        courseObject = childrenJSONArray.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    myKidsVO.setName(getStringFromJSON(courseObject, "name", ""));
                    myKidsVO.setImageURL(getStringFromJSON(courseObject, "image_url", ""));

                    mItems.add(myKidsVO);


                }
                }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return mItems;
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
}
