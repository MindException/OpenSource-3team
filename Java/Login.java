package com.cookandroid.opensw_3team_cafereviewproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.StringTokenizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //파이어베이스연동
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getInstance().getReference();

        //회원가입
        Button sign_button = (Button) findViewById(R.id.sign_button);
        sign_button.setOnClickListener(new View.OnClickListener(){ //버튼을 눌렀을 경우 발생

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, NewUser.class);       //첫 번째 매개변수는 자신, 두 번째는 이동
                startActivity(intent);      //시작

            }
        });

        //로그인
        Button login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {

            String email = "";
            String id = "";
            String site = "";
            String domain = "";
            String pwd = "";
            String trash = "";

            @Override
            public void onClick(View v) {

            EditText idtext = (EditText) findViewById(R.id.emailAddress);
            email = idtext.getText().toString();       //Email 저장
            StringTokenizer st1 = new StringTokenizer(email, "@");
            id = st1.nextToken();
            trash = st1.nextToken();

            StringTokenizer st2 = new StringTokenizer(trash,".");
            site = st2.nextToken();
            domain= st2.nextToken();

            EditText pwdtext = (EditText)findViewById(R.id.pwd);
            pwd = pwdtext.getText().toString();

            //아이디 비번 저장완료 이제 서버에다가 요청
            String finalId = id;

            DatabaseReference userDB = database.getInstance().getReference("USER").child(finalId);
                userDB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapShot) {

                    String dbdomain = dataSnapShot.child("address_domain").getValue(String.class);        //도메인 가져옴
                    String dbsite = dataSnapShot.child("address_site").getValue(String.class);        //사이트 가져옴
                    String dbpasswd = dataSnapShot.child("passwd").getValue(String.class);              //비밀번호 가져옴


                    if(site.equals(dbsite)){    //사이트 검사
                        if(domain.equals(dbdomain)){    //도메인 가져옴
                            if(pwd.equals(dbpasswd)){       //비밀번호 가져옴

                                //전부 다 통과하였으니 다음으로 이동
                                Intent loginIntent = new Intent(Login.this, MainActivity.class);       //첫 번째 매개변수는 자신, 두 번째는 이동
                                startActivity(loginIntent);      //시작


                            }
                        }
                    }




                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



            }//onClick

        });

        //종료
        Button cancle_button = (Button) findViewById(R.id.cancle_button);
        cancle_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();

            }

        });



    }
}