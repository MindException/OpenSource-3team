package com.cookandroid.opensw_3team_cafereviewproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; //하단바
    private FragmentManager fm;
    private FragmentTransaction ft;
    private home home;
    private map map;
    private set set;
    private Local local;
    private String mylocal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);            //툴바 이름 삭제


        bottomNavigationView = findViewById(R.id.battomMavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        setFrag(0);
                        break;
                    case R.id.action_map:
                        setFrag(1);
                        break;
                    case R.id.action_set:
                        setFrag(2);
                        break;
                }
                return true;
            }
        });
        setFrag(0); //첫화면
    }


   //프레그먼트 교체
    private  void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        //닉네임 받아야 되어서 이렇게 옮김 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 이거 다른 곳 가면 작동 안함
        home = new home();
        map = new map();
        set = new set();
        local = new Local(mylocal);

        switch (n) {
            case 0: //홈 화면
                ft.replace(R.id.main_frame, home);
                ft.commit();
                break;


            case 1: //맵 화면

                Intent mapintent = new Intent(MainActivity.this, map.class);
                startActivity(mapintent);
                ft.commit();
                break;

            case 2:
                ft.replace(R.id.main_frame, set);
                ft.commit();
                break;

            case 3:
                Intent reviewit = new Intent(MainActivity.this, ReviewReport.class);
                reviewit.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                reviewit.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(reviewit);
                ft.commit();
                break;

            case 4:
                ft.replace(R.id.main_frame, local);
                ft.commit();
                break;

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_categorie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if(item.getItemId() == R.id.review){        //리뷰 작성으로 넘어간다.

            setFrag(3);

        }else{         //아니라면 전부 지역 작성으로 넘어간다.

            mylocal = item.getTitle().toString();       //지역을 넘겨 받는다.
            System.out.println(mylocal);
            setFrag(4);

        }

        return super.onOptionsItemSelected(item);
    }



}