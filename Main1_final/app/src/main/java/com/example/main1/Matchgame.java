package com.example.main1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Collections;

public class Matchgame extends AppCompatActivity {
    TextView mTv;

    ImageView iv_01, iv_02, iv_03, iv_04,
            iv_05, iv_06, iv_07, iv_08,
            iv_09, iv_10, iv_11, iv_12;

    Integer[] cardsArray = {11, 12, 13, 14, 15, 16, 21, 22, 23, 24, 25, 26};

    int image11, image12, image13, image14, image15, image16,
            image21, image22, image23, image24, image25, image26;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;

    int mScore = 0;
    int mClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_matchgame);

        mTv = (TextView) findViewById(R.id.score);
        iv_01 = (ImageView) findViewById(R.id.iv_01);
        iv_02 = (ImageView) findViewById(R.id.iv_02);
        iv_03 = (ImageView) findViewById(R.id.iv_03);
        iv_04 = (ImageView) findViewById(R.id.iv_04);

        iv_05 = (ImageView) findViewById(R.id.iv_05);
        iv_06 = (ImageView) findViewById(R.id.iv_06);
        iv_07 = (ImageView) findViewById(R.id.iv_07);
        iv_08 = (ImageView) findViewById(R.id.iv_08);

        iv_09 = (ImageView) findViewById(R.id.iv_09);
        iv_10 = (ImageView) findViewById(R.id.iv_10);
        iv_11 = (ImageView) findViewById(R.id.iv_11);
        iv_12 = (ImageView) findViewById(R.id.iv_12);

        iv_01.setTag("0");
        iv_02.setTag("1");
        iv_03.setTag("2");
        iv_04.setTag("3");
        iv_05.setTag("4");
        iv_06.setTag("5");
        iv_07.setTag("6");
        iv_08.setTag("7");
        iv_09.setTag("8");
        iv_10.setTag("9");
        iv_11.setTag("10");
        iv_12.setTag("11");

        frontCardInit();

        Collections.shuffle(Arrays.asList(cardsArray));

        iv_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                CardSelect(iv_01, theCard);
            }
        });
        iv_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                CardSelect(iv_02, theCard);
            }
        });
        iv_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                CardSelect(iv_03, theCard);
            }
        });
        iv_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                CardSelect(iv_04, theCard);
            }
        });
        iv_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                CardSelect(iv_05, theCard);
            }
        });
        iv_06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                CardSelect(iv_06, theCard);
            }
        });
        iv_07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                CardSelect(iv_07, theCard);
            }
        });
        iv_08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                CardSelect(iv_08, theCard);
            }
        });
        iv_09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                CardSelect(iv_09, theCard);
            }
        });
        iv_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                CardSelect(iv_10, theCard);
            }
        });
        iv_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                CardSelect(iv_11, theCard);
            }
        });
        iv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                CardSelect(iv_12, theCard);
            }
        });
    }

    private void CardSelect(ImageView iv, int theCard) {
        mClicked++;
        if(cardsArray[theCard] == 11){
            iv.setImageResource(image11);
        }
        else if(cardsArray[theCard] == 12){
            iv.setImageResource(image12);
        }
        else if(cardsArray[theCard] == 13){
            iv.setImageResource(image13);
        }
        else if(cardsArray[theCard] == 14){
            iv.setImageResource(image14);
        }
        else if(cardsArray[theCard] == 15){
            iv.setImageResource(image15);
        }
        else if(cardsArray[theCard] == 16){
            iv.setImageResource(image16);
        }
        else if(cardsArray[theCard] == 21){
            iv.setImageResource(image21);
        }
        else if(cardsArray[theCard] == 22){
            iv.setImageResource(image22);
        }
        else if(cardsArray[theCard] == 23){
            iv.setImageResource(image23);
        }
        else if(cardsArray[theCard] == 24){
            iv.setImageResource(image24);
        }
        else if(cardsArray[theCard] == 25){
            iv.setImageResource(image25);
        }
        else if(cardsArray[theCard] == 26){
            iv.setImageResource(image26);
        }

        // 어떤 이미지가 선택되었는지 저장
        // 첫 번째 카드인 경우
        if(cardNumber == 1){
            firstCard = cardsArray[theCard];
//            if(firstCard > 20){
//                firstCard = firstCard - 10;
//            }
            cardNumber = 2 ;
            clickedFirst = theCard;

            iv.setEnabled(false);

        }
        else if(cardNumber == 2){
            secondCard = cardsArray[theCard];
//            if(secondCard > 20){
//                secondCard = secondCard - 10;
//            }
            cardNumber = 1 ;
            clickedSecond = theCard;
        }

        iv_01.setEnabled(false);
        iv_02.setEnabled(false);
        iv_03.setEnabled(false);
        iv_04.setEnabled(false);
        iv_05.setEnabled(false);
        iv_06.setEnabled(false);
        iv_07.setEnabled(false);
        iv_08.setEnabled(false);
        iv_09.setEnabled(false);
        iv_10.setEnabled(false);
        iv_11.setEnabled(false);
        iv_12.setEnabled(false);
        mTv.setText("Clicked : " + mClicked);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                calculate();
            }
        }, 1000);
    }

    private void calculate() {
        boolean is_same = false;
        if(cardNumber == 1 && secondCard > firstCard && secondCard - 10 == firstCard){
            is_same = true;
        }
        else if(cardNumber == 1 && firstCard > secondCard && firstCard - 10 == secondCard){
            is_same = true;
        }
        if(is_same){
            if(clickedFirst == 0){
                iv_01.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 1){
                iv_02.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 2){
                iv_03.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 3){
                iv_04.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 4){
                iv_05.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 5){
                iv_06.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 6){
                iv_07.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 7){
                iv_08.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 8){
                iv_09.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 9){
                iv_10.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 10){
                iv_11.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 11){
                iv_12.setVisibility(View.INVISIBLE);
            }

            if(clickedSecond == 0){
                iv_01.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 1){
                iv_02.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 2){
                iv_03.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 3){
                iv_04.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 4){
                iv_05.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 5){
                iv_06.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 6){
                iv_07.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 7){
                iv_08.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 8){
                iv_09.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 9){
                iv_10.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 10){
                iv_11.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 11){
                iv_12.setVisibility(View.INVISIBLE);
            }
            mScore++;
            mTv.setText("Clicked : " + mClicked);
        }
        else{
            iv_01.setImageResource(R.drawable.back);
            iv_02.setImageResource(R.drawable.back);
            iv_03.setImageResource(R.drawable.back);
            iv_04.setImageResource(R.drawable.back);
            iv_05.setImageResource(R.drawable.back);
            iv_06.setImageResource(R.drawable.back);
            iv_07.setImageResource(R.drawable.back);
            iv_08.setImageResource(R.drawable.back);
            iv_09.setImageResource(R.drawable.back);
            iv_10.setImageResource(R.drawable.back);
            iv_11.setImageResource(R.drawable.back);
            iv_12.setImageResource(R.drawable.back);
        }

        iv_01.setEnabled(true);
        iv_02.setEnabled(true);
        iv_03.setEnabled(true);
        iv_04.setEnabled(true);
        iv_05.setEnabled(true);
        iv_06.setEnabled(true);
        iv_07.setEnabled(true);
        iv_08.setEnabled(true);
        iv_09.setEnabled(true);
        iv_10.setEnabled(true);
        iv_11.setEnabled(true);
        iv_12.setEnabled(true);

        checkEnd();

    }

    private void checkEnd() {
        if(iv_01.getVisibility() == View.INVISIBLE &&
                iv_02.getVisibility() == View.INVISIBLE &&
                iv_03.getVisibility() == View.INVISIBLE &&
                iv_04.getVisibility() == View.INVISIBLE &&
                iv_05.getVisibility() == View.INVISIBLE &&
                iv_06.getVisibility() == View.INVISIBLE &&
                iv_07.getVisibility() == View.INVISIBLE &&
                iv_08.getVisibility() == View.INVISIBLE &&
                iv_09.getVisibility() == View.INVISIBLE &&
                iv_10.getVisibility() == View.INVISIBLE &&
                iv_11.getVisibility() == View.INVISIBLE &&
                iv_12.getVisibility() == View.INVISIBLE ){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Matchgame.this);
            updateScore(Z_Score());
            alertDialogBuilder
                    .setMessage("Game Over\nScore : " + (int)Z_Score())
                    .setCancelable(false)
                    .setPositiveButton("New game", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent =  new Intent(getApplicationContext(), Matchgame.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent1 = new Intent(Matchgame.this, MainActivity.class);
                            startActivity(intent1);
                            finish();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    private void frontCardInit() {
        image11 = R.drawable.image11;
        image12 = R.drawable.image12;
        image13 = R.drawable.image13;
        image14 = R.drawable.image14;
        image15 = R.drawable.image15;
        image16 = R.drawable.image16;
        image21 = R.drawable.image21;
        image22 = R.drawable.image22;
        image23 = R.drawable.image23;
        image24 = R.drawable.image24;
        image25 = R.drawable.image25;
        image26 = R.drawable.image26;
    }

    public double Z_Score(){
        double SD = 5;
        double z_score = (double)(mClicked - 24) / SD;

        double ret;
        if(z_score >= 0){
            ret = 50 - 28 * z_score;
        }
        else{
            ret = (31 * z_score) * (-1) + 50;
        }
        if(ret < 0) return 0;
        return Math.round(ret * 1000)/1000.0;
    }

    public void updateScore(double now){


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("Wake up Brain");
        String uid = user.getUid();
        DatabaseReference game = mDatabaseRef.child("UserAccount").child(uid).child("gMap").child("Match");

        game.addValueEventListener(new ValueEventListener() {
            boolean doUpdate = false;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Game gSeq = snapshot.getValue(Game.class);
                if (!doUpdate) {
                    boolean doBest = gSeq.update(now);
                    mDatabaseRef.child("UserAccount").child(uid).child("gMap").child("Match").setValue(gSeq);

                    if (doBest) {
                        Toast.makeText(Matchgame.this, "최고 기록 경신!", Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(Matchgame.this, "점수가 기록되었습니다", Toast.LENGTH_SHORT).show();
                    doUpdate = true;
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}