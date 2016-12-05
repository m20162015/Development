package com.splashpapers.chalktalk.timetable;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.timetable.model.TimeTableVO;

import java.util.List;

/**
 * Created by manishsharma on 11/24/16.
 */
public class TimeTableAdapter extends ArrayAdapter<TimeTableVO> {

    public TimeTableAdapter(Context context, List<TimeTableVO> items) {
        super(context, R.layout.timetable_list_cell_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.timetable_list_cell_item,parent, false);
        }

        TextView mNewsTitleListTextView = (TextView) v.findViewById(R.id.timetable_subject);
        TextView mNewsDateListTextView = (TextView) v.findViewById(R.id.timetable_name);
        TextView mNewsDetailListTextView = (TextView) v.findViewById(R.id.timetable_time);
        CardView cardView = (CardView) v.findViewById(R.id.card_view);

        TimeTableVO news = getItem(position);

//        Picasso.with(getContext()).load(news.getImg()).error(R.drawable.profile_logo).placeholder(R.drawable.profile_logo).into(mNewsImgView);
        mNewsTitleListTextView.setText(news.getSubjet());
        mNewsDateListTextView.setText(news.getName());
        mNewsDetailListTextView.setText(news.getTime());

        if(position%2==0)cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        else cardView.setCardBackgroundColor(Color.parseColor("#F2F1F1"));


        return v;
    }

    @Override
    public TimeTableVO getItem(int position) {
        return super.getItem(position);
    }
}



