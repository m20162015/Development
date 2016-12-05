package com.splashpapers.chalktalk.homework;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.app.Constants;
import com.splashpapers.chalktalk.homework.model.HomeWorkModel;
import com.splashpapers.chalktalk.homework.model.HomeWorkVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by manishsharma on 11/23/16.
 */
public class HomeWorkFragment extends Fragment {

    static HomeWorkAdapter mAdapter;
    List<HomeWorkVO> mItems;
    static TextView mEmptyList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_list_view_layout, container, false);

        mEmptyList = (TextView) view.findViewById(R.id.task_list_empty);

        mItems = HomeWorkModel.getInstance().getHomeWorkList("news");
        mAdapter = new HomeWorkAdapter(getActivity(),mItems);

        ListView listView = (ListView) view.findViewById(R.id.task_list);
        listView.setAdapter(mAdapter);
        callRequest();

        return view;
    }

    private void callRequest()
    {
        HomeWorkRequest homeWorkRequest = new HomeWorkRequest(getActivity());
        homeWorkRequest.callRequest(HomeWorkConstants.HOMEWORK_FUNCTION_NAME,getParameters());
    }

    private HashMap<String, String> getParameters() {
        HashMap<String, String> parameters = new HashMap<>();
        SharedPreferences user = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
        JSONArray childrenJSONArray = null;
        JSONObject childrenJSONObject = null;
        JSONObject selectedChildJSONObject = null;
        String classId = null;
        try {
            childrenJSONObject = new JSONObject(user.getString(Constants.CHILDREN_LIST, null));
            childrenJSONArray = childrenJSONObject.getJSONArray("children");
            selectedChildJSONObject = childrenJSONArray.getJSONObject(0);
            classId = selectedChildJSONObject.getString("class_id");
//            classId = "2";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        parameters.put(HomeWorkConstants.CLASS_ID, classId);
        parameters.put(HomeWorkConstants.SUBJECT_ID, classId);
        parameters.put(HomeWorkConstants.SECTION_ID, classId);

        return parameters;
    }

    public static void setVisibilityEmptyList(TextView mEmptyList, int sizeList) {
        if (mEmptyList == null) {
            return;
        }

        mEmptyList.setVisibility(View.VISIBLE);
        if (sizeList > 0) {
            mEmptyList.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Update the visibility of empty message
        setVisibilityEmptyList(mEmptyList, HomeWorkModel.getInstance().getHomeWorkList("news").size());
    }
}
