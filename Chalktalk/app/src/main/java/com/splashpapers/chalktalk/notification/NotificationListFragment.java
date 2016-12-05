package com.splashpapers.chalktalk.notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.notification.model.NotificationVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by my pc on 16-Nov-16.
 */

public class NotificationListFragment extends android.support.v4.app.Fragment {

    List mItems;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_list_view_layout, container, false);

        mItems = new ArrayList();

        NotificationVO notificationVO = new NotificationVO();
        notificationVO.setDate("30 April 2017");
        notificationVO.setTitle("New Notif");
        mItems.add(notificationVO);

        NotificationAdapter mAdapter = new NotificationAdapter(getActivity(),mItems);

        ListView listView = (ListView)view.findViewById(R.id.task_list);
        listView.setAdapter(mAdapter);

        return super.onCreateView(inflater, container, savedInstanceState);


    }
    @Override
    public void onCreate( Bundle savedInstanceState) {
        //String screenName = SCREEN_NAME;

        super.onCreate(savedInstanceState);
    }
}
