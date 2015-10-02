package com.example.vklyovan.modertartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

public class ConfirmMoreInfoDialogFragment extends DialogFragment {

    public static String TAG = "ConfirmMoreInfoDialog";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Log.i(TAG, "Creating moreinfo dialog");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.fragment_confirm_moreinfo, null))
                .setPositiveButton(R.string.moreinfo_visit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "Visit button clicked");

                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://moma.org"));
                        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                            Log.i(TAG, "Starting browser view activity");
                            startActivity(intent);
                        } else {
                            Log.e(TAG, "Error while resolving browser activity");
                        }
                    }
                })
                .setNegativeButton(R.string.moreinfo_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "Cancel button clicked");
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations
                = R.style.DialogAnimation;
        return dialog;
    }
}
