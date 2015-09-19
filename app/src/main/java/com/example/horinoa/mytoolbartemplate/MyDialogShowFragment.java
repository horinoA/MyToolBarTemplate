package com.example.horinoa.mytoolbartemplate;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by horinoA on 2015/09/16.
 */


public class MyDialogShowFragment extends Fragment {
    private View view;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main,container,false);
        textView = (TextView)view.findViewById(R.id.textView);
        Button okbuttom = (Button)view.findViewById(R.id.Okbutton);
        okbuttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkDialog dialog = OkDialog.newInstance(MyDialogShowFragment.this,"OKdialog","OKdialoよ〜ん",1001);
                dialog.setOnOkClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //ここにokボタン実行メソッド
                        Toast.makeText(getActivity().getApplicationContext(),"OK牧場",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show(getFragmentManager(), "okdialog");
            }
        });
        Button okcancelbuttom = (Button)view.findViewById(R.id.OkCancelbutton);
        okcancelbuttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkCancelButtomClick();
            }
        });
        Button editbuttom = (Button)view.findViewById(R.id.EditTextbutton);
        editbuttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditTextClick();
            }
        });
        return view;
    }

    private void OkCancelButtomClick(){
        OkCancelDialog dialog = OkCancelDialog.newInstance(MyDialogShowFragment.this,"okCancelDialog","okCanceよ〜ん",1002);
        dialog.setOnOkClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ここにOKボタ実行メソッド
                Toast.makeText(getActivity().getApplicationContext(),"OK牧場",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setOnCancelClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show(getFragmentManager(),"okcanceldialog");
    }

    private void EditTextClick(){
        final EditText editText = new EditText(getActivity());
        InputFilter[] inputFilter = new InputFilter[1];
        //文字数設定
        inputFilter[0] = new InputFilter.LengthFilter(30);
        editText.setFilters(inputFilter);
        editText.setHint("メモを入力");
        EditTextDialog dialogFragment = EditTextDialog
                .newInstance(MyDialogShowFragment.this, "メモを入力", "メモを入力してください（３０字まで）", 1003);
        dialogFragment.setOnOkClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //こっからokボタン実行メソッド
                textView.setText(editText.getText());
                Toast.makeText(getActivity().getApplicationContext(),editText.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        dialogFragment.setOnCancelClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogFragment.setEditText(editText);
        dialogFragment.show(getActivity().getFragmentManager(), "editTextile");
    }



}
