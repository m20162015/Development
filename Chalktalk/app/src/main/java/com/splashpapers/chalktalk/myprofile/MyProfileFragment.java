package com.splashpapers.chalktalk.myprofile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.app.Constants;
import com.splashpapers.chalktalk.myprofile.model.MyProfileModel;
import com.splashpapers.chalktalk.myprofile.model.MyProfileVO;

import java.util.HashMap;

/**
 * Created by manishsharma on 11/30/16.
 */
public class MyProfileFragment extends Fragment {

    static MyProfileVO myProfileVO;
    static EditText profileName, profileEmail, profilePhone,profileAddress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_screen_layout, container, false);

        profileName = (EditText) view.findViewById(R.id.profile_name_editText);
        profileEmail = (EditText) view.findViewById(R.id.profile_email_editText);
        profilePhone = (EditText) view.findViewById(R.id.profile_mobile_editText);
        profileAddress = (EditText) view.findViewById(R.id.profile_address_editText);
        setData();
        callRequest();

        return view;
    }

    private void callRequest()
    {
        ProfileRequest profileRequest = new ProfileRequest(getActivity());
        profileRequest.callRequest(ProfileConstants.PROFILE_FUNCTION_NAME,getParameters());
    }

    private HashMap<String, String> getParameters() {
        HashMap<String, String> parameters = new HashMap<>();
        SharedPreferences user = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);

        parameters.put(ProfileConstants.LOGIN_TYPE, user.getString(Constants.LOGIN_TYPE, null));
//        parameters.put(ProfileConstants.LOGIN_USER_ID, user.getString(Constants.LOGIN_USER_ID, null));
        parameters.put(ProfileConstants.LOGIN_USER_ID, "4");

        return parameters;
    }

    public static void setData()
    {
        myProfileVO = MyProfileModel.getInstance().getMyProfile("myprofile");
        if(myProfileVO != null) {
            profileName.setText(myProfileVO.getName());
            profileEmail.setText(myProfileVO.getEmail());
            profilePhone.setText(myProfileVO.getPhone());
            profileAddress.setText(myProfileVO.getAddress());
        }


    }
}
