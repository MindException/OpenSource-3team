package com.cookandroid.opensw_3team_cafereviewproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.StringTokenizer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //파이어베이스연동
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getInstance().getReference();

        //회원가입
        ImageButton sign_button = (ImageButton) findViewById(R.id.sign_up_button);
        sign_button.setOnClickListener(new View.OnClickListener(){ //버튼을 눌렀을 경우 발생

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, NewUser.class);       //첫 번째 매개변수는 자신, 두 번째는 이동
                startActivity(intent);      //시작

            }
        });

        //로그인
        ImageButton login_button = (ImageButton) findViewById(R.id.loginButton);
        login_button.setOnClickListener(new View.OnClickListener() {

            String email = "";
            String id = "";
            String site = "";
            String domain = "";
            String pwd = "";
            String trash = "";

            @Override
            public void onClick(View v) {

            EditText idtext = (EditText) findViewById(R.id.emailtext);
            email = idtext.getText().toString();       //Email 저장

            // 혹여라도 . 이나 com이 없으면 안되기 때문에 검사하는 코드 작성

            int trigger1 = 0;        //@이 있는지 확인
            int trigger2 = 0;        //.이 있는지 확인

            char c1 = '@';
            char c2 = '.';

            char temp;

            for(int i = 0; i < email.length(); i++){ //@가 있나 확인

                temp = email.charAt(i);
                if(c1 == temp){

                    trigger1 = 1;
                    break;
                }

            }
            if(trigger1 == 0){  //@이 없을경우
                idtext.setText("id 입력에 @가 없습니다");
                return;
            }


            for(int j = 0; j < email.length(); j++){

                temp = email.charAt(j);
                if(c2 == temp){

                    trigger2 = 1;
                    break;
                }
            }

            if(trigger2 == 0){  //.이 없을경우
                idtext.setText("id 입력에 .가 없습니다");
                return;
            }


            //다시 진행
            StringTokenizer st1 = new StringTokenizer(email, "@");
            id = st1.nextToken();
            trash = st1.nextToken();

            StringTokenizer st2 = new StringTokenizer(trash,".");
            site = st2.nextToken();
            domain= st2.nextToken();

            EditText pwdtext = (EditText)findViewById(R.id.pwdtext);
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
                                loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(loginIntent);      //시작
                                return;

                            }
                        }
                    }

                    idtext.setText("id or password 불일치");

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {



                }
            });


            return;


            }//onClick

        });



    }
}