package com.example.main1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Sequencegame extends AppCompatActivity implements View.OnClickListener{
    TextView mTv;
    Integer level = 1;
    ImageView button1, button2, button3, button4, button5,
            button6, button7, button8, button9;

    Random randNumber;
    List<Integer> ButtonHistory;
    List<Integer> userSelect;
    int cnt = 0;

    Boolean yourTurn = false;
    Boolean aiTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sequencegame);

        mTv = (TextView) findViewById(R.id.level);

        //Getting the buttons
        button1 = (ImageView) findViewById(R.id.btn1);
        button2 = (ImageView) findViewById(R.id.btn2);
        button3 = (ImageView) findViewById(R.id.btn3);
        button4 = (ImageView) findViewById(R.id.btn4);
        button5 = (ImageView) findViewById(R.id.btn5);
        button6 = (ImageView) findViewById(R.id.btn6);
        button7 = (ImageView) findViewById(R.id.btn7);
        button8 = (ImageView) findViewById(R.id.btn8);
        button9 = (ImageView) findViewById(R.id.btn9);

        button1.setTag(1);
        button2.setTag(2);
        button3.setTag(3);
        button4.setTag(4);
        button5.setTag(5);
        button6.setTag(6);
        button7.setTag(7);
        button8.setTag(8);
        button9.setTag(9);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

        //Giving them a tag for easier comparison in onClick


        //Showing a would you like to play dialog
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                switch (which) {
                    case (DialogInterface.BUTTON_POSITIVE):
                        ButtonHistory = new ArrayList<Integer>();
                        ButtonHistory.clear();
                        cnt = 0;
                        GameStart();
                        break;

                    case (DialogInterface.BUTTON_NEGATIVE):
                        Intent intent1 = new Intent(Sequencegame.this, MainActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("get start")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }

    // Main loop
    public void GameStart() {
        if (aiTurn) {
            int n = randomNumber();
            showSequence(n);

            yourTurn = true;
            aiTurn = false;
        }
    }

    public void showSequence(int n){
        ButtonHistory.add(n);
        Handler delayHandler = new Handler();

        for(int i = 0; i < ButtonHistory.size(); i++){
            int now = ButtonHistory.get(i);
            int idx = i;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    button4.setEnabled(false);
                    button5.setEnabled(false);
                    button6.setEnabled(false);
                    button7.setEnabled(false);
                    button8.setEnabled(false);
                    button9.setEnabled(false);
                    if (now == 1) {
                        button1.setImageResource(R.drawable.drawable_view_focused);
                    } else if (now == 2) {
                        button2.setImageResource(R.drawable.drawable_view_focused);
                    } else if (now == 3) {
                        button3.setImageResource(R.drawable.drawable_view_focused);
                    } else if (now == 4) {
                        button4.setImageResource(R.drawable.drawable_view_focused);
                    } else if (now == 5) {
                        button5.setImageResource(R.drawable.drawable_view_focused);
                    } else if (now == 6) {
                        button6.setImageResource(R.drawable.drawable_view_focused);
                    } else if (now == 7) {
                        button7.setImageResource(R.drawable.drawable_view_focused);
                    } else if (now == 8) {
                        button8.setImageResource(R.drawable.drawable_view_focused);
                    } else if (now == 9) {
                        button9.setImageResource(R.drawable.drawable_view_focused);
                    }
                }
            }, 500 * (idx+1) + 100);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (now == 1) {
                        button1.setImageResource(R.drawable.drawable_view);
                    } else if (now == 2) {
                        button2.setImageResource(R.drawable.drawable_view);
                    } else if (now == 3) {
                        button3.setImageResource(R.drawable.drawable_view);
                    } else if (now == 4) {
                        button4.setImageResource(R.drawable.drawable_view);
                    } else if (now == 5) {
                        button5.setImageResource(R.drawable.drawable_view);
                    } else if (now == 6) {
                        button6.setImageResource(R.drawable.drawable_view);
                    } else if (now == 7) {
                        button7.setImageResource(R.drawable.drawable_view);
                    } else if (now == 8) {
                        button8.setImageResource(R.drawable.drawable_view);
                    } else if (now == 9) {
                        button9.setImageResource(R.drawable.drawable_view);
                    }
                }
            }, 500 * (idx+2));
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
                button4.setEnabled(true);
                button5.setEnabled(true);
                button6.setEnabled(true);
                button7.setEnabled(true);
                button8.setEnabled(true);
                button9.setEnabled(true);
            }
        }, 500 * (ButtonHistory.size() + 1));

    }

    public void buttonInit(){
        button1.setImageResource(R.drawable.blue);
        button2.setImageResource(R.drawable.blue);
        button3.setImageResource(R.drawable.blue);
        button4.setImageResource(R.drawable.blue);
        button5.setImageResource(R.drawable.blue);
        button6.setImageResource(R.drawable.blue);
        button7.setImageResource(R.drawable.blue);
        button8.setImageResource(R.drawable.blue);
        button9.setImageResource(R.drawable.blue);
    }

    // 1 ~ 9 사이의 랜덤 숫자 뽑기
    public int randomNumber() {
        randNumber = new Random();
        int n = randNumber.nextInt(9) + 1;
        return n;
    }


    public void failed(){

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                switch (which) {
                    case (DialogInterface.BUTTON_POSITIVE):
                        ButtonHistory = new ArrayList<Integer>();
                        ButtonHistory.clear();
                        aiTurn = true;
                        cnt = 0;
                        level = 1;
                        mTv.setText("Level : " + level);
                        GameStart();
                        break;
                    case (DialogInterface.BUTTON_NEGATIVE):
                        Intent intent1 = new Intent(Sequencegame.this, MainActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("실패, 재시작 하시겠습니까?\nScore : " + (int)Z_Score())
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void customClick(int tag){
        if(tag == 1){
            button1.setImageResource(R.drawable.drawable_view_selected);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    button1.setImageResource(R.drawable.drawable_view);
                }
            }, 300);
        }
        else if(tag == 2){
            button2.setImageResource(R.drawable.drawable_view_selected);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    button2.setImageResource(R.drawable.drawable_view);
                }
            }, 300);
        }
        else if(tag == 3){
            button3.setImageResource(R.drawable.drawable_view_selected);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    button3.setImageResource(R.drawable.drawable_view);
                }
            }, 300);
        }
        else if(tag == 4){
            button4.setImageResource(R.drawable.drawable_view_selected);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    button4.setImageResource(R.drawable.drawable_view);
                }
            }, 300);
        }
        else if(tag == 5){
            button5.setImageResource(R.drawable.drawable_view_selected);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    button5.setImageResource(R.drawable.drawable_view);
                }
            }, 300);
        }
        else if(tag == 6){
            button6.setImageResource(R.drawable.drawable_view_selected);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    button6.setImageResource(R.drawable.drawable_view);
                }
            }, 300);
        }
        else if(tag == 7){
            button7.setImageResource(R.drawable.drawable_view_selected);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    button7.setImageResource(R.drawable.drawable_view);
                }
            }, 300);
        }
        else if(tag == 8){
            button8.setImageResource(R.drawable.drawable_view_selected);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    button8.setImageResource(R.drawable.drawable_view);
                }
            }, 300);
        }
        else if(tag == 9){
            button9.setImageResource(R.drawable.drawable_view_selected);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    button9.setImageResource(R.drawable.drawable_view);
                }
            }, 300);
        }
    }

    // 내 차례일 때 버튼을 누르고 누른 버튼과 history 순서를 비교
    public void onClick(View v) {
        // TODO Auto-generated method stub
        int tag = (Integer) v.getTag();
//        Log.d("picked ", " num : " + tag);
        customClick(tag);

        if (tag != 0 && tag == ButtonHistory.get(cnt)) {
            cnt++;
        }
        else {
            aiTurn = false;
            updateScore(Z_Score());
            failed();
        }
        if(cnt == ButtonHistory.size()){
//            Log.d("picked ", " hg : " + cnt);
            aiTurn = true;
            cnt = 0;
            level++;
            mTv.setText("Level : " + level);
            GameStart();
        }
    }

    public double Z_Score(){
        double SD = 3.6;
        double z_score = (double)(level - 10) / SD;

        double ret;
        ret = 50 + 25 * z_score;
        if(ret < 0) return 0;
        return Math.round(ret * 1000)/1000.0;
    }

    public void updateScore(double now){


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("Wake up Brain");
        String uid = user.getUid();
        DatabaseReference game = mDatabaseRef.child("UserAccount").child(uid).child("gMap").child("Sequence");

        game.addValueEventListener(new ValueEventListener() {
            boolean doUpdate = false;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Game gSeq = snapshot.getValue(Game.class);
                if (!doUpdate) {
                    boolean doBest = gSeq.update(now);
                    mDatabaseRef.child("UserAccount").child(uid).child("gMap").child("Sequence").setValue(gSeq);

                    if (doBest) {
                        Toast.makeText(Sequencegame.this, "최고 기록 경신!", Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(Sequencegame.this, "점수가 기록되었습니다", Toast.LENGTH_SHORT).show();
                    doUpdate = true;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }


}