package com.splashpapers.chalktalk.homework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.homework.model.HomeWorkVO;

import java.util.List;

/**
 * Created by manishsharma on 11/23/16.
 */
public class HomeWorkAdapter  extends ArrayAdapter<HomeWorkVO> {

    public HomeWorkAdapter(Context context, List<HomeWorkVO> items) {
        super(context, R.layout.notification_list_cell_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.notification_list_cell_item,parent, false);
        }

        TextView mNewsTitleListTextView = (TextView) v.findViewById(R.id.notif_heading_list);
        TextView mNewsDateListTextView = (TextView) v.findViewById(R.id.notif_date);
        TextView mNewsDetailListTextView = (TextView) v.findViewById(R.id.notif_desc);

        HomeWorkVO news = getItem(position);

//        Picasso.with(getContext()).load(news.getImg()).error(R.drawable.profile_logo).placeholder(R.drawable.profile_logo).into(mNewsImgView);
        mNewsTitleListTextView.setText(news.getTitle());
        mNewsDateListTextView.setText(news.getDate());
        mNewsDetailListTextView.setText(news.getDesc());

        return v;
    }

    @Override
    public HomeWorkVO getItem(int position) {
        return super.getItem(position);
    }
}

