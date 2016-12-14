package com.splashpapers.chalktalk.aboutus;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.aboutus.model.AboutUsModel;
import com.splashpapers.chalktalk.aboutus.model.AboutUsVO;
import com.splashpapers.chalktalk.app.Constants;

import java.util.HashMap;

/**
 * Created by manishsharma on 11/20/16.
 */
public class AboutUsFragment extends Fragment {

    AboutUsActivity activity;
    static WebView aboutUsWebView;
    static ImageView image;
    static TextView schoolNameTxtView, locationTxtView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_us_view, container, false);

        activity = (AboutUsActivity) getActivity();
        activity.setTitle("ABOUT US");

        aboutUsWebView = (WebView) view.findViewById(R.id.abt_us_web_view);
        image = (ImageView) view.findViewById(R.id.iamge);
        schoolNameTxtView = (TextView) view.findViewById(R.id.school_name);
        locationTxtView = (TextView) view.findViewById(R.id.location);

        setData(getActivity());
        callRequest();
        return view;
    }

    public static void setData(Context context)
    {
        AboutUsVO data = AboutUsModel.getInstance().getAboutUs("aboutus");
        SharedPreferences user = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        try {
            Log.d("Exception","ex5 ");
//            JSONObject childrenJSONObject = new JSONObject(user.getString(Constants.CHILDREN_LIST, null));
            Log.d("Exception","ex6 ");
            schoolNameTxtView.setText((user.getString(Constants.INSTITUTE_NAME,"")).toString());
            Log.d("Exception","ex7 ");
//            if(data.size()>0) {
                Log.d("Exception","ex1 ");
                locationTxtView.setText(data.getLocation());
                Log.d("Exception","ex2 ");
                aboutUsWebView.loadData(data.getAboutUsText(), "text/html; charset=UTF-8", null);
                Log.d("Exception","ex3 ");
                Glide.with(context).load(data.getImageURL().toString()).placeholder(R.drawable.chalk_talk_icon).error(R.drawable.chalk_talk_icon).into(image);
                Log.d("Exception","ex4 ");
//            }
        }
        catch (Exception e){
            Log.d("Exception","ex "+e);
        }
    }

    private void callRequest()
    {
        AboutUsRequest aboutUsRequest = new AboutUsRequest(getActivity());
        aboutUsRequest.callRequest(AboutUsConstants.ABOUTUS_FUNCTION_NAME,getParameters());
    }

    private HashMap<String, String> getParameters() {
        HashMap<String, String> parameters = new HashMap<>();
        return parameters;
    }
}
