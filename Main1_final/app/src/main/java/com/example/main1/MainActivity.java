package com.example.main1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mFirebaseAuth;

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar;

    NavController navController;
    private AppBarConfiguration mAppBarConfiguration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        CardView mpair_game = findViewById(R.id.pair_game);
        CardView msequence_game = findViewById(R.id.sequence_game);
        CardView mverbal_game = findViewById(R.id.verbal_game);
        CardView mresponse_game = findViewById(R.id.response_game);
        CardView mchat = findViewById(R.id.chat);

        mpair_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, Matchgame.class);
                startActivity(intent1);
            }
        });
        msequence_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, Sequencegame.class);
                startActivity(intent2);
            }
        });
        mverbal_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, Verbalgame.class);
                startActivity(intent3);
            }
        });
        mresponse_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MainActivity.this, Responsegame.class);
                startActivity(intent4);
            }
        });

        mchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(MainActivity.this,Chat.class);
                startActivity(intent5);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else super.onBackPressed();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_match:
                Intent intent1 = new Intent(MainActivity.this, Matchgame.class);
                startActivity(intent1);
                break;
            case R.id.nav_sequence:
                Intent intent2 = new Intent(MainActivity.this, Sequencegame.class);
                startActivity(intent2);
                break;
            case R.id.nav_verbal:
                Intent intent3 = new Intent(MainActivity.this, Verbalgame.class);
                startActivity(intent3);
                break;
            case R.id.nav_response:
                Intent intent4 = new Intent(MainActivity.this, Responsegame.class);
                startActivity(intent4);
                break;

            case R.id.nav_chat:
                Intent intent5 = new Intent(MainActivity.this, Chat.class);
                startActivity(intent5);
                break;
            case R.id.nav_profile:
                Intent intent6 = new Intent(MainActivity.this,mpAndroidChart.class);
                startActivity(intent6);
                break;

            case R.id.nav_logout:
                mFirebaseAuth = FirebaseAuth.getInstance();
                mFirebaseAuth.signOut();
                Intent intent7 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent7);
                finish();
                break;

            case R.id.nav_delete:

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case (DialogInterface.BUTTON_POSITIVE):

                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                DatabaseReference mDatabaseRef =
                                        FirebaseDatabase.getInstance().getReference("Wake up Brain")
                                                .child("UserAccount").child(user.getUid());


                                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(MainActivity.this, "계정이 삭제되었습니다", Toast.LENGTH_SHORT).show();
                                                    mDatabaseRef.setValue(null);
                                                    Intent intent7 = new Intent(MainActivity.this, LoginActivity.class);
                                                    startActivity(intent7);
                                                    finish();
                                                }
                                                else{
                                                    Toast.makeText(MainActivity.this, "계정을 삭제하지 못했습니다", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                            case (DialogInterface.BUTTON_NEGATIVE):
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false);
                builder.setMessage("계정을 삭제하겠습니까?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

        }
        return true;
    }


}
