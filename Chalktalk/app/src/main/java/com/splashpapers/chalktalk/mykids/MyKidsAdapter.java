package com.splashpapers.chalktalk.mykids;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.mykids.model.MyKidsVO;

import java.util.List;

/**
 * Created by manishsharma on 11/30/16.
 */
public class MyKidsAdapter  extends ArrayAdapter<MyKidsVO> {


    public MyKidsAdapter(Context context, List<MyKidsVO> items) {
        super(context, R.layout.mykids_list_cell_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.mykids_list_cell_item, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.mykids_name);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.mykids_img_list_view);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        MyKidsVO item = getItem(position);
        viewHolder.title.setText(item.getName());

        Glide.with(getContext()).load(item.getImageURL()).error(R.drawable.profile).placeholder(R.drawable.profile).into(viewHolder.image);
        return convertView;
    }

    @Override
    public MyKidsVO getItem(int position) {
        return super.getItem(position);
    }

    private static class ViewHolder {
        TextView title;
        ImageView image;
    }

}

