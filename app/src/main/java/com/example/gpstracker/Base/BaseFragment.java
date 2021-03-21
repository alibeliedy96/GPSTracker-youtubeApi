package com.example.gpstracker.Base;

import android.content.Context;


import androidx.fragment.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;


public class BaseFragment extends Fragment {
    protected BaseActivity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(BaseActivity) context;
    }

    public MaterialDialog showMessage(int titleResId, int messageResId, int posResText){
        return activity.showMessage(titleResId,messageResId,posResText);

    }


    public MaterialDialog showConfirmationMessage(int titleResId, int messageResId, int posResText,
                                                  MaterialDialog.SingleButtonCallback onPosAction){
        return activity.showConfirmationMessage(titleResId,messageResId,posResText,onPosAction);

    }
    public MaterialDialog showMessage(String title, String message, String posText){

        return activity.showMessage(title,message,posText);
    }
    public MaterialDialog showProgressBar(int message){

        return activity.showProgressBar(message);
    }
    public void hideProgressBar() {
        activity.hideProgressBar();
    }
}
