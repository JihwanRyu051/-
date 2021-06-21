package com.example.main1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.main1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class Responsegame extends AppCompatActivity {

    Random randWait;
    ImageView mTouch;
    TextView mText;
    Button mRes_again;

    private int turn = 1;
    private long start_time;
    private long end_time;
    private long temp_time;
    private int cnt = 0;
    private double res_result;
    Handler handler;

    public ArrayList<Long> response_result = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_responsegame);

        mTouch = findViewById(R.id.response);
        mText = findViewById(R.id.showtext);
        mRes_again = findViewById(R.id.res_again);

        mRes_again.setVisibility(View.INVISIBLE);

        handler = new Handler();

        mTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if(turn == 1){ //게임 시작
                            WaitingInRed();
                        }
                        else if(turn == 2){ //빨간 상태에서 누르는 경우
                            mTouch.setImageResource(R.drawable.start_response);
                            mText.setText("Calm down..\nTouch to try again");
                            turn = 1;
                            handler.removeCallbacksAndMessages(null);
                        }
                        else if(turn == 3){ //초록 상태에서 누르는 경우
                            end_time = System.currentTimeMillis();
                            temp_time = end_time - start_time;
                            response_result.add(temp_time);
                            cnt++;
                            if(cnt == 5){
                                res_result = calAverage();
                                mTouch.setImageResource(R.drawable.start_response);
                                updateScore(Z_Score());
                                mText.setText("Response Time : " + res_result + "\nScore : " + (int)Z_Score());
                                mRes_again.setVisibility(View.VISIBLE);
                                turn = 0;
                            }
                            else{
                                mTouch.setImageResource(R.drawable.start_response);
                                mText.setText(temp_time + "ms\nTouch to keep going");
                                turn = 1;
                            }
                        }
                }
                return false;
            }
        });

        mRes_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRes_again.setVisibility(View.INVISIBLE);
                turn = 1;
                mTouch.setImageResource(R.drawable.start_response);
                mText.setText("Touch Anywhere to Start");
                cnt = 0;
                response_result.clear();
            }
        });

    }

    public void WaitingInRed(){
        turn = 2;
        mTouch.setImageResource(R.drawable.wait_response);
        mText.setText("...Wait...");

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startTouch();
            }
        }, randomWait());
    }

    public void startTouch(){
        turn = 3;
        mTouch.setImageResource(R.drawable.touch_response);
        mText.setText("...Touch!!");
        start_time = System.currentTimeMillis();
    }


    public int randomWait() {
        randWait = new Random();
        int n = randWait.nextInt(2000) + 2000;  // 2~4초
        return n;
    }

    public double calAverage(){
        int total = 0;
        double average = 0;
        for(int i = 0; i < response_result.size(); i++){
            total += response_result.get(i);
        }
        average = total / response_result.size();
        return average;
    }

    public double Z_Score(){
        double SD = 50;
        double z_score = (double)(res_result - 300) / SD;

        double ret;
        if(z_score >= 0){
            ret = 50 - 23 * z_score;
        }
        else{
            ret = (23 * z_score) * (-1) + 50;
        }
        if(ret < 0) return 0;
        return Math.round(ret * 1000)/1000.0;
    }

    public void updateScore(double now){


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("Wake up Brain");
        String uid = user.getUid();
        DatabaseReference game = mDatabaseRef.child("UserAccount").child(uid).child("gMap").child("Response");

        game.addValueEventListener(new ValueEventListener() {
            boolean doUpdate = false;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Game gSeq = snapshot.getValue(Game.class);
                if (!doUpdate) {
                    boolean doBest = gSeq.update(now);
                    mDatabaseRef.child("UserAccount").child(uid).child("gMap").child("Response").setValue(gSeq);

                    if (doBest) {
                        Toast.makeText(Responsegame.this, "최고 기록 경신!", Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(Responsegame.this, "점수가 기록되었습니다", Toast.LENGTH_SHORT).show();
                    doUpdate = true;
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}

