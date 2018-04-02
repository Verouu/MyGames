package com.av.mygames.mygames.addGame;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.av.mygames.mygames.R;

/**
 * Created by Verou on 2/4/18.
 */

public class AskGameConfirmationDialog extends DialogFragment {
    IConfirmedListener confirmedListener;
    String name, summary;
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_SUMMARY = "extra_summary";
    TextView nameTv;
    TextView summaryTv;

    public static AskGameConfirmationDialog newInstance(String name, String summary){
        AskGameConfirmationDialog dialog = new AskGameConfirmationDialog();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_NAME, name);
        bundle.putString(EXTRA_SUMMARY, summary);
        dialog.setArguments(bundle);

        return dialog;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = getArguments().getString(EXTRA_NAME);
        summary = getArguments().getString(EXTRA_SUMMARY);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity context = getActivity();
        View view = context.getLayoutInflater().inflate(R.layout.ask_confirmation_dialog, null);
        nameTv = view.findViewById(R.id.confirmation_dialog_name_content);
        summaryTv = view.findViewById(R.id.confirmation_dialog_summary_content);
        nameTv.setText(name);
        summaryTv.setText(summary);

        AlertDialog.Builder builder = new AlertDialog.Builder (context);
        builder.setView(view);
        builder.setPositiveButton("YEP, PROCEED", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirmedListener.onActionConfirmed();
            }
        });
        builder.setNegativeButton("NOT AT ALL", null);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        confirmedListener = (IConfirmedListener) context;
    }

    public interface IConfirmedListener {
        void onActionConfirmed ();
    }


}
