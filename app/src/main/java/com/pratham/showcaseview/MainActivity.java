package com.pratham.showcaseview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import java.util.ArrayList;
import java.util.HashMap;

import smartdevelop.ir.eram.showcaseviewlib.GuideView;
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType;
import smartdevelop.ir.eram.showcaseviewlib.config.Gravity;
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Context context;
    GridView gridView;
    CustomGridViewAdapter customGridAdapter;
    int j = 0;
    Activity activity;
    ShowcaseView sv;
    View targetView;
    ViewTarget target;
    GuideView guideView;
    AdapterView.OnItemClickListener onItemClickListener;
    ArrayList<Item> gridArray;
    Bitmap keySelected;
    private GuideView.Builder builder;
    View view1;
    View view2;
    View view3;
    View view4;
    View view5;
    View btnNoAnswer, btnSubmitAnswer;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        context = this;
        activity = this;
        onItemClickListener = this;

        final TextView tvText = findViewById(R.id.tv_text);
        gridArray = new ArrayList<Item>();
        final Bitmap keyDefault = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_key_default);
        keySelected = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_key_selected);

        for(int i=0; i<12; i++){
            gridArray.add(new Item(1+"", keyDefault));
        }

        intent = new Intent(context, PlayAudioHindi.class);
        startService(intent);

        btnNoAnswer = findViewById(R.id.btn_no_answer);
        btnSubmitAnswer = findViewById(R.id.btn_submit_answer);
        gridView = (GridView) findViewById(R.id.gv);
        customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, gridArray);
        gridView.setAdapter(customGridAdapter);
        gridView.setOnItemClickListener(onItemClickListener);
    }

    public void gridViewClick(){
        targetView = gridView.getChildAt(0);
        builder = new GuideView.Builder(activity)
                .setTitle("Guide Title Text")
                .setContentText("Guide Description Text\n .....Guide Description Text\n .....Guide Description Text .....")
                .setGravity(Gravity.auto) //optional
                .setDismissType(DismissType.targetView) //optional - default DismissType.targetView
                .setTargetView(targetView)
                .setContentTextSize(12)//optional
                .setTitleTextSize(14)//optional
                .setGuideListener(new GuideListener() {
                    @Override
                    public void onDismiss(View view) {
                        gridArray.get(0).setImage(keySelected);
                        gridView.performItemClick(gridView, 0, gridView.getItemIdAtPosition(0));

                    }
                });
    }

    public void buttonClick(View view){
        builder = new GuideView.Builder(activity)
                .setTitle("Guide Title Text")
                .setContentText("Guide Description Text\n .....Guide Description Text\n .....Guide Description Text .....")
                .setGravity(Gravity.auto) //optional
                .setDismissType(DismissType.targetView) //optional - default DismissType.targetView
                .setTargetView(view)
                .setContentTextSize(12)//optional
                .setTitleTextSize(14)//optional
                .setGuideListener(new GuideListener() {
                    @Override
                    public void onDismiss(View v) {
                        switch (v.getId()) {
                            case R.id.btn_submit_answer:
                                builder.setTargetView(btnNoAnswer).build();
                                break;
                            case R.id.btn_no_answer:
                                //builder.setTargetView(btnSubmitAnswer).build();
                                return;
                        }

                        guideView = builder.build();
                        guideView.show();
                    }
                });

        guideView = builder.build();
        guideView.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view1 = (View) findViewById(R.id.action_check_hindi);
                view2 = (View) findViewById(R.id.action_speaker_hindi);
                view3 = (View) findViewById(R.id.action_check_eng);
                view4 = (View) findViewById(R.id.action_speaker_eng);

                builder = new GuideView.Builder(activity)
                    .setTitle("Guide Title Text")
                    .setContentText("Guide Description Text\n .....Guide Description Text\n .....Guide Description Text .....")
                    .setGravity(Gravity.center) //optional
                    .setDismissType(DismissType.targetView) //optional - default DismissType.targetView
                    .setTargetView(view1)
                    .setContentTextSize(12)//optional
                    .setTitleTextSize(14)//optional
                    .setGuideListener(new GuideListener() {
                        @Override
                        public void onDismiss(View view) {
                            switch (view.getId()) {
                                case R.id.action_check_hindi:
                                    stopService(intent);
                                    intent = new Intent(context, PlayAudioClick.class);
                                    startService(intent);
                                    builder.setTargetView(view2).build();
                                    break;
                                case R.id.action_speaker_hindi:
                                    stopService(intent);
                                    intent = new Intent(context, PlayAudioKey.class);
                                    startService(intent);
                                    gridViewClick();
                                    //builder.setTargetView(view3).build();
                                    break;
                                case R.id.action_check_eng:
                                    builder.setTargetView(view4).build();
                                    break;
                                case R.id.action_speaker_eng:
                                    stopService(intent);
                                    intent = new Intent(context, PlayAudioClick.class);
                                    startService(intent);
                                    gridViewClick();
                                    break;
                        /*case R.id.view5:
                            return;*/
                            }

                            guideView = builder.build();
                            guideView.show();
                        }
                    });

                /*intent = new Intent(context, PlayAudioHindi.class);
                startService(intent);*/
                guideView = builder.build();
                guideView.show();

            }
        }, 1000);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_speaker_hindi) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final int selectedPosition = i+1;

        targetView = gridView.getChildAt(selectedPosition);
        builder = new GuideView.Builder(activity)
                .setTitle("Guide Title Text")
                .setContentText("Guide Description Text\n .....Guide Description Text\n .....Guide Description Text .....")
                .setGravity(Gravity.auto) //optional
                //.setDismissType(DismissType.targetView) //optional - default DismissType.targetView
                .setTargetView(targetView)
                .setContentTextSize(12)//optional
                .setTitleTextSize(14)//optional
                .setGuideListener(new GuideListener() {
                    @Override
                    public void onDismiss(View view) {
                        gridArray.get(selectedPosition).setImage(keySelected);
                        customGridAdapter.notifyDataSetChanged();
                        if(selectedPosition == 4){
                            stopService(intent);
                            intent = new Intent(context, PlayAudioNextQue.class);
                            startService(intent);
                            buttonClick(btnSubmitAnswer);
                        } else {
                            gridView.performItemClick(gridView, selectedPosition, gridView.getItemIdAtPosition(selectedPosition));
                        }
                    }
                });

        guideView = builder.build();
        guideView.show();
    }
}


