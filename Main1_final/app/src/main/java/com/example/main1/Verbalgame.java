package com.example.main1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Verbalgame extends AppCompatActivity {
    Button seenButton, newButton;
    TextView mscore, mlives, Verbal;

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;

    public ArrayList<String> all_verbals = Words_Set.words;
    public ArrayList<String> stage_verbals = new ArrayList<String>();
    public ArrayList<String> history = new ArrayList<String>();

    Random randNumber = new Random();
    int lives, score;
    int per, h_idx, idx;
    String new_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_verbalgame);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Wake up Brain");

        mscore = (TextView) findViewById(R.id.score);
        mlives = (TextView) findViewById(R.id.lives);
        Verbal = (TextView) findViewById(R.id.verbal);
        seenButton = (Button) findViewById(R.id.seenbtn);
        newButton = (Button) findViewById(R.id.newbtn);

        // 시작 버튼 Dialog
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                switch (which) {
                    case (DialogInterface.BUTTON_POSITIVE):

                        Game_reset();
                        break;

                    case (DialogInterface.BUTTON_NEGATIVE):
                        Intent intent1 = new Intent(Verbalgame.this, MainActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Verbal Memory test")
                .setPositiveButton("Start", dialogClickListener)
                .setNegativeButton("Exit", dialogClickListener).show();


        seenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Correct_Check(1);
            }
        });
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Correct_Check(2);
            }
        });

    }

    public void Game_reset(){
        lives = 3;
        score = 0;
        idx = 0;
        history.clear();
        stage_verbals.clear();

        // 최대 300개
        Collections.shuffle(all_verbals);
        for(int i = 0; i < 300; i++){
            stage_verbals.add(all_verbals.get(i));
        }
        mscore.setText("Score | " + score);
        mlives.setText("Lives | " + lives);
        Verbal.setText(stage_verbals.get(0));
    }


    // 1 : seen, 2 : new
    public void Correct_Check(int c){
        String now_verbal = Verbal.getText().toString();

        if(c == 1){
            if(VerbalInHistory(now_verbal)){
                score += 1;
                mscore.setText("Score | " + score);
            }
            else{
                CheckFailed();
            }
        }
        else if(c == 2) {
            if(!VerbalInHistory(now_verbal)){
                score += 1;
                mscore.setText("Score | " + score);
            }
            else{
                CheckFailed();
            }
        }

        ShowNextVerbal();
    }

    void CheckFailed(){
        lives -= 1;
        mlives.setText("Lives | " + lives);
        if(lives == 0){
            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    switch (which) {
                        case (DialogInterface.BUTTON_POSITIVE):
                            Game_reset();
                            break;

                        case (DialogInterface.BUTTON_NEGATIVE):
                            Intent intent1 = new Intent(Verbalgame.this, MainActivity.class);
                            startActivity(intent1);
                            break;
                    }
                }
            };
            updateScore(Z_Score());
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Remembered words :" + score + "\nScore : " + (int)Z_Score())
                    .setPositiveButton("Try again", dialogClickListener)
                    .setNegativeButton("Exit", dialogClickListener).show(); // 종료하면 메인화면으로 이동하는기능
        }
    }

    boolean VerbalInHistory(String str){
        boolean is_in = false;
        for(int i = 0; i < history.size(); i++){
            if(history.get(i) == str){
                is_in = true;
                break;
            }
        }
        return is_in;
    }

    void SetNextVerbal(int percent){
        if(per > percent){
            idx+=1;
            new_string = stage_verbals.get(idx);
        }
        else{
            h_idx = randNumber.nextInt(history.size());
            if(h_idx == history.size()-1){
                // 같은 단어가 연속으로 나오는 경우 방지
                h_idx -= 1;
            }
            new_string = history.get(h_idx);
        }
    }

    public void ShowNextVerbal(){
        String now_verbal = Verbal.getText().toString();
        if(!VerbalInHistory(now_verbal)) history.add(now_verbal);


        per = randNumber.nextInt(100) + 1;
        // 새로운 단어가 나올확률 : 70% 시작

        if(history.size() < 3){
            idx+=1;
            new_string = stage_verbals.get(idx);
        }
        else if(history.size() < 15){
            SetNextVerbal(30);
        }
        else if(history.size() < 30){
            SetNextVerbal(32);
        }
        else if(history.size() < 45){
            SetNextVerbal(34);
        }
        else if(history.size() < 60){
            SetNextVerbal(36);
        }
        else if(history.size() < 75){
            SetNextVerbal(38);
        }
        else if(history.size() < 100){
            SetNextVerbal(40);
        }
        else{
            SetNextVerbal(45);
        }

        Verbal.setText(new_string);
    }

    public double Z_Score(){
        double SD = 11;
        double z_score = (double)(score - 45) / SD;

        double ret;
        ret = 50 + 12 * z_score;
        if(ret < 0) return 0;
        return Math.round(ret * 1000)/1000.0;
    }


    public void updateScore(double now){


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("Wake up Brain");
        String uid = user.getUid();
        DatabaseReference game = mDatabaseRef.child("UserAccount").child(uid).child("gMap").child("Verbal");

        game.addValueEventListener(new ValueEventListener() {
            boolean doUpdate = false;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Game gSeq = snapshot.getValue(Game.class);
                if (!doUpdate) {
                    boolean doBest = gSeq.update(now);
                    mDatabaseRef.child("UserAccount").child(uid).child("gMap").child("Verbal").setValue(gSeq);

                    if (doBest) {
                        Toast.makeText(Verbalgame.this, "최고 기록 경신!", Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(Verbalgame.this, "점수가 기록되었습니다", Toast.LENGTH_SHORT).show();
                    doUpdate = true;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}