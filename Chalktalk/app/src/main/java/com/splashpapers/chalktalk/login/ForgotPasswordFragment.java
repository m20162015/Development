package com.splashpapers.chalktalk.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.splashpapers.chalktalk.R;

/**
 * Created by manishsharma on 11/18/16.
 */
public class ForgotPasswordFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forgot_view,container,false);

        TextView mBackToLogin = (TextView) view.findViewById(R.id.back_to_login);
        mBackToLogin.setOnClickListener(loadBackToLogin);
        return view;
    }

    private View.OnClickListener loadBackToLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
//            FragmentTransaction ft = fm.beginTransaction();
//            ForgotPasswordFragment forgotPasswordFragment = new ForgotPasswordFragment();
//            ft.add(R.id.main_container, forgotPasswordFragment);
//            ft.addToBackStack("FORGET");
//            ft.commit();
            fm.executePendingTransactions();
        }
    };
}
