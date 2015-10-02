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
    private float[] mMinSaturation = new float[RECT_NUM]; // [0.2,0.4)
    private float[] mRangeSaturation = new float[RECT_NUM]; // [0.2,0.5)

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Random rand = new Random();
        int iWhite = rand.nextInt(5);
        Log.i(TAG, "white index = " + iWhite);
        for (int i = 0; i < RECT_NUM; ++i) {

            if (i == iWhite) mMinSaturation[i] = 0.0f;
            else mMinSaturation[i] = 0.2f + rand.nextFloat() * 0.4f;
            mRangeSaturation[i] = 0.3f + rand.nextFloat() * 0.6f;

            float hsv[] = new float[3];
            hsv[0] = rand.nextInt(360);
            hsv[1] = mMinSaturation[i];
            if (i == iWhite) hsv[2] = 1.0f;
            else hsv[2] = 0.5f;

            mRectColor[i] = Color.HSVToColor(hsv);
        }

        for (int i = 0; i < mMinSaturation.length; ++i) {
            Log.i(TAG, "mMinSaturation = " + mMinSaturation[i]);
            Log.i(TAG, "mRangeSaturation = " + mRangeSaturation[i]);
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

        for (int i = 0; i < mRectColor.length; ++i) {
            float hsv[] = new float[3];
            Color.colorToHSV(mRectColor[i], hsv);
            hsv[1] = mMinSaturation[i] + progress * (mRangeSaturation[i]) / (float) seekBar.getMax();
            mRectColor[i] = Color.HSVToColor(hsv);
            mRectView[i].setBackgroundColor(mRectColor[i]);
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // NoOp
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // NoOp
    }
}
