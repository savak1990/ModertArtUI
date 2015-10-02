package com.example.vklyovan.modertartui;

import android.app.Fragment;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import java.util.Random;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment
                                  implements SeekBar.OnSeekBarChangeListener {

    private static final String TAG = "MainActivityFragment";

    private static final int RECT_NUM = 5;

    private View[] mRectView = new View[RECT_NUM];
    private int[] mRectColor = new int[RECT_NUM];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Random rand = new Random();
        mRectColor[0] = Color.WHITE;
        for (int i = 1; i < RECT_NUM; ++i) {
            mRectColor[i] = Color.rgb(
                    rand.nextInt(256),
                    rand.nextInt(256),
                    rand.nextInt(256));
        }

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.fragment_main, container, false);

        mRectView[0] = fragView.findViewById(R.id.colorRect1);
        mRectView[1] = fragView.findViewById(R.id.colorRect2);
        mRectView[2] = fragView.findViewById(R.id.colorRect3);
        mRectView[3] = fragView.findViewById(R.id.colorRect4);
        mRectView[4] = fragView.findViewById(R.id.colorRect5);

        for (int i = 0; i< mRectView.length; ++i) {
            mRectView[i].setBackgroundColor(mRectColor[i]);
        }

        SeekBar seekBar = (SeekBar) fragView.findViewById(R.id.colorChangeSeekBar);
        seekBar.setOnSeekBarChangeListener(this);

        return fragView;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.i(TAG, "onProgressChanged: " + progress + " fromUser = " + fromUser);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        Log.i(TAG, "onStartTrackingTouch "
                + seekBar.getProgress() + "/" + seekBar.getMax());
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Log.i(TAG, "onStopTrackingTouch "
                + seekBar.getProgress() + "/" + seekBar.getMax());
    }
}
