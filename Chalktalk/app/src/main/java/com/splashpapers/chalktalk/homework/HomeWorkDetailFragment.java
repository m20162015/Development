package com.splashpapers.chalktalk.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.homework.model.HomeWorkVO;

/**
 * Created by manishsharma on 12/10/16.
 */

public class HomeWorkDetailFragment extends Fragment{

    HomeWorkVO homeWorkVO;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homework_detail_view_layout, container, false);

        homeWorkVO = (HomeWorkVO) this.getArguments().get("HomeWorkVO");

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView date = (TextView) view.findViewById(R.id.date);
        TextView detail = (TextView) view.findViewById(R.id.detail);

        title.setText(homeWorkVO.getTitle());
        date.setText(homeWorkVO.getDate());
        detail.setText(homeWorkVO.getDesc());

        return view;
    }


}
