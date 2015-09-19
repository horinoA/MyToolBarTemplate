package com.example.horinoa.mytoolbartemplate;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by horinoA on 2015/09/17.
 */
public class OkCancelDialog extends DialogFragment{
    private DialogInterface.OnClickListener okClickListener = null;
    private DialogInterface.OnClickListener cancelClickListener = null;

    public OkCancelDialog(){}

    public static OkCancelDialog newInstance(Fragment target, String title,String txt,int requestCode){
        OkCancelDialog fragment = new OkCancelDialog();
        fragment.setTargetFragment(target, requestCode);
        Bundle bundle = new Bundle();
        bundle.putString("txt",txt);
        bundle.putString("title",title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String title = getArguments().getString("title");
        String txt= getArguments().getString("txt");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(txt);
        builder.setPositiveButton("OK", this.okClickListener);
        builder.setNegativeButton("Cancel", this.cancelClickListener);
        return builder.create();
    }

    public void setOnOkClickListener(DialogInterface.OnClickListener listener) {
        this.okClickListener = listener;
    }

    public void setOnCancelClickListener(DialogInterface.OnClickListener listener) {
        this.cancelClickListener = listener;
    }
}
