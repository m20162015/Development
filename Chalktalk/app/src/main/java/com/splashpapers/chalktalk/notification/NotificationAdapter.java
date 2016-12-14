package com.splashpapers.chalktalk.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.notification.model.NotificationVO;

import java.util.List;

/**
 * Created by my pc on 16-Nov-16.
 */

public class NotificationAdapter extends ArrayAdapter<NotificationVO> {
    public NotificationAdapter(Context context, List<NotificationVO> items) {
        super(context, R.layout.notif_list_cell_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.notif_list_cell_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.date = (TextView) convertView.findViewById(R.id.notif_date);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.notif_imageView_list);
            viewHolder.heading = (TextView) convertView.findViewById(R.id.notif_desc);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        NotificationVO item = getItem(position);
        viewHolder.date.setText(item.getDate());
       // viewHolder.image.setImageURI(item.getImage());
        viewHolder.heading.setText(item.getTitle());
        return convertView;
    }

    private static class ViewHolder {
        TextView date;
        TextView heading;
        ImageView image;
    }


}
