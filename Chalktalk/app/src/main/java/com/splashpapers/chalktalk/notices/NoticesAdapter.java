package com.splashpapers.chalktalk.notices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.notices.model.NoticesVO;

import java.util.List;

/**
 * Created by manishsharma on 11/22/16.
 */
public class NoticesAdapter extends ArrayAdapter<NoticesVO> {

    Context context;
    public NoticesAdapter(Context context, List<NoticesVO> items) {
        super(context, R.layout.notices_list_cell_item, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.notices_list_cell_item,parent, false);
        }

        TextView mNewsTitleListTextView = (TextView) v.findViewById(R.id.notif_heading_list);
        TextView mNewsDateListTextView = (TextView) v.findViewById(R.id.notif_date);
        TextView mNewsDetailListTextView = (TextView) v.findViewById(R.id.notif_desc);

//        ExpandableTextView expTv1 = (ExpandableTextView) v.findViewById(R.id.expand_text_view);
//
//        expTv1.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
//            @Override
//            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
//                Toast.makeText(context, isExpanded ? "Expanded" : "Collapsed", Toast.LENGTH_SHORT).show();
//            }
//        });


        NoticesVO news = getItem(position);

//        Picasso.with(getContext()).load(news.getImg()).error(R.drawable.profile_logo).placeholder(R.drawable.profile_logo).into(mNewsImgView);
        mNewsTitleListTextView.setText(news.getTitle());
        mNewsDateListTextView.setText(news.getDate());
        mNewsDetailListTextView.setText(news.getDesc());
//        expTv1.setText(news.getDesc());
        return v;
    }

    @Override
    public NoticesVO getItem(int position) {
        return super.getItem(position);
    }
}

