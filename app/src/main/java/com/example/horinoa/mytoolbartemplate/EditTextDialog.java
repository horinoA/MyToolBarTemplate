package com.example.horinoa.mytoolbartemplate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by horinoA on 2015/09/17.
 */
public class EditTextDialog extends DialogFragment {
    private DialogInterface.OnClickListener okClickListener = null;
    private DialogInterface.OnClickListener cancelClickListener = null;
    private EditText editText;

    public EditTextDialog(){}

    public static EditTextDialog newInstance(Fragment target, String title,String txt,int requestCode) {
        EditTextDialog fragment = new EditTextDialog();
        fragment.setTargetFragment(target, requestCode);
        Bundle bundle = new Bundle();
        bundle.putString("txt",txt);
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        String txt = getArguments().getString("txt");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(title)
                    .setMessage(txt)
                    .setView(this.editText)
                    .setPositiveButton("OK", this.okClickListener)
                    .setNegativeButton("Cancel", this.cancelClickListener);

        return builder.create();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //DialogFragmenで画面を再生成しない設定にしたら
        setRetainInstance(true);
    }

    @Override
    public void onDestroyView() {
        //後からsetしたViewは再生成時親Viewにはめ直すためするため、再生性前に親Viewから外しておく
        ViewGroup parent = (ViewGroup)editText.getParent();
        if ( parent != null ) {
            parent.removeView(editText);
        }
        //setRetainInstance(true}時、メッセージキューにpushさるDISSMISSメッセージをnullにする
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }

    public void setOnOkClickListener(DialogInterface.OnClickListener listener) {
        this.okClickListener = listener;
    }

    public void setOnCancelClickListener(DialogInterface.OnClickListener listener) {
        this.cancelClickListener = listener;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

}

