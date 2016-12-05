package com.splashpapers.chalktalk.notices;

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
import com.splashpapers.chalktalk.notices.model.NoticeModel;
import com.splashpapers.chalktalk.notices.model.NoticesVO;

import java.util.HashMap;
import java.util.List;

/**
 * Created by manishsharma on 11/22/16.
 */
public class NoticesFragment extends Fragment {

    static NoticesAdapter mAdapter;
    List<NoticesVO> mItems;
    static TextView mEmptyList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_list_view_layout, container, false);

        mEmptyList = (TextView) view.findViewById(R.id.task_list_empty);

        mItems = NoticeModel.getInstance().getNewsList("news");
        mAdapter = new NoticesAdapter(getActivity(),mItems);

        ListView listView = (ListView) view.findViewById(R.id.task_list);
        listView.setAdapter(mAdapter);
        callRequest();

        return view;
    }

    private void callRequest()
    {
        NoticesRequest noticesRequest = new NoticesRequest(getActivity());
        noticesRequest.callRequest(NoticesConstants.NOTICES_FUNCTION_NAME,getParameters());
    }

    private HashMap<String, String> getParameters() {
        HashMap<String, String> parameters = new HashMap<>();
        SharedPreferences user = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);

        parameters.put(NoticesConstants.STUDENT_ID, user.getString(Constants.CHILDREN_LIST, null));

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
        setVisibilityEmptyList(mEmptyList, NoticeModel.getInstance().getNewsList("news").size());
    }
}
