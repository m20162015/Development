package com.splashpapers.chalktalk.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.splashpapers.chalktalk.R;
import com.splashpapers.chalktalk.app.Constants;

import java.util.HashMap;

/**
 * Created by manishsharma on 11/9/16.
 */
public class LoginFragment extends Fragment {

    TextView mEmailTextView, mPasswordTextView;
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String LOGIN_FUNC_NAME = "login";

    AlertDialog.Builder alert;
    private boolean rememberflag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_view,container,false);

        mEmailTextView = (TextView) view.findViewById(R.id.email);
        mPasswordTextView = (TextView) view.findViewById(R.id.password);

        SharedPreferences user = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
        mEmailTextView.setText(user.getString(Constants.USERNAME, ""));
        mPasswordTextView.setText(user.getString(Constants.PASSWORD, ""));

        TextView mForgotPassword = (TextView) view.findViewById(R.id.forget_txt_view);
        mForgotPassword.setOnClickListener(loadForgotPassword);

        Button mLoginButton = (Button) view.findViewById(R.id.login_btn);
        mLoginButton.setOnClickListener(loadLogin);

        String alertButton = getString(R.string.ok);
        // Initialize alert
        alert = new AlertDialog.Builder(getActivity());
        alert.setPositiveButton(alertButton, null);

        CheckBox mRadio = (CheckBox) view.findViewById(R.id.remember_me_check_box);
        mRadio.setChecked(user.getBoolean(Constants.REMEMBER_FLAG,false));
        rememberflag = user.getBoolean(Constants.REMEMBER_FLAG,false);
        mRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rememberflag = isChecked;
            }
        });
        return view;
    }

    private void doLoginAction(String username, String password) {
        if (username.trim().length() > 0 && password.trim().length() > 0) {
            // create hashmap with params
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put(KEY_EMAIL, username);
            parameters.put(KEY_PASSWORD, password);
            LoginRequest loginRequest = new LoginRequest(getActivity(), rememberflag, username, password);
            loginRequest.callRequest(LOGIN_FUNC_NAME, parameters);
        } else{
            alert.setMessage(getString(R.string.login_alert_user_and_pass));
            alert.show();
        }
    }

    private View.OnClickListener loadLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            doLoginAction(mEmailTextView.getText().toString(), mPasswordTextView.getText().toString());
        }
    };

    private View.OnClickListener loadForgotPassword = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ForgotPasswordFragment forgotPasswordFragment = new ForgotPasswordFragment();
            ft.add(R.id.main_container, forgotPasswordFragment);
            ft.addToBackStack("FORGET");
            ft.commit();
            fm.executePendingTransactions();
        }
    };
}
