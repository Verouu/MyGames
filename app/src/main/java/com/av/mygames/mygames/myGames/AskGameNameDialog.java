package com.av.mygames.mygames.myGames;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.av.mygames.mygames.R;

/**
 * Created by Verou on 1/4/18.
 */

public class AskGameNameDialog extends DialogFragment{
    EditText gameNameEdit;
    IGameNameListener gameNameListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity context = getActivity();
        View view = context.getLayoutInflater().inflate(R.layout.lookfor_name_layout, null);
        gameNameEdit = view.findViewById(R.id.lookForGameEt);

        AlertDialog.Builder builder = new AlertDialog.Builder (context);
        builder.setView(view);
        builder.setPositiveButton("SEARCH", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (gameNameListener != null){
                    gameNameListener.onNameInput(gameNameEdit.getText().toString());
                }
            }
        });
        builder.setNegativeButton("CANCEL", null);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        gameNameListener = (IGameNameListener) context;
    }

    public interface IGameNameListener {
        void onNameInput (String name);
    }
}
