package com.cookandroid.opensw_3team_cafereviewproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReviewReport extends AppCompatActivity {

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;

    private int requestCode;


    String today;       //오늘 날짜


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_report);


        //이미지 버튼이 눌렸을 경우
        imageView1 = findViewById(R.id.cafe_image1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                requestCode = 1;
                startActivityForResult(intent, 1);      //코드 번호로 아래에서 동작한다.

            }
        });

        imageView2 = findViewById(R.id.cafe_image2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                requestCode = 2;
                startActivityForResult(intent, 2);      //코드 번호로 아래에서 동작한다.

            }
        });

        imageView3 = findViewById(R.id.cafe_image3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                requestCode = 3;
                startActivityForResult(intent, 3);      //코드 번호로 아래에서 동작한다.

            }
        });

        imageView4 = findViewById(R.id.cafe_image4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                requestCode = 4;
                startActivityForResult(intent, 4);      //코드 번호로 아래에서 동작한다.

            }
        });

        imageView5 = findViewById(R.id.cafe_image5);
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                requestCode = 5;
                startActivityForResult(intent, 5);      //코드 번호로 아래에서 동작한다.

            }
        });

        imageView6 = findViewById(R.id.cafe_image6);
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                requestCode = 6;
                startActivityForResult(intent, 6);      //코드 번호로 아래에서 동작한다.

            }
        });

        //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 이미지 버튼 끝 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

        //제일 먼저 시간을 저장한다.

        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formTime = new SimpleDateFormat("yyyy-MM-dd");
        this.today = formTime.format(todayDate);

        EditText dateText = (EditText) findViewById(R.id.day);
        dateText.setText(this.today);

        //작성 버튼을 눌렀을 경우
        Button report_button = (Button)findViewById(R.id.report);
        report_button.setOnClickListener(new View.OnClickListener() {





            @Override
            public void onClick(View v) {

            List<String> imgList = new ArrayList<String>();         //이미지 파일들을 여기에 저장한다.
            Date todayDate = Calendar.getInstance().getTime();
            SimpleDateFormat formTime = new SimpleDateFormat("yyyy-MM-dd");
            String today = formTime.format(todayDate);          //날짜 저장
            EditText stateText = (EditText)findViewById(R.id.state);
            String state = stateText.getText().toString();      //도 저장
            EditText cityText = (EditText)findViewById(R.id.city);
            String city = cityText.getText().toString();      //시 저장

            String img1 = null;
            String img2 = null;
            String img3 = null;
            String img4 = null;
            String img5 = null;
            String img6 = null;

            //첫 번째 칸
            try {

                //일일히 해줘야한다.(애초에 배열로 안 묶어서 안된다.)
                ImageView imgView1 = (ImageView) findViewById(R.id.cafe_image1);        //소스를 가져온다.
                BitmapDrawable drawable1 = (BitmapDrawable) imgView1.getDrawable();
                Bitmap bitmap1 = drawable1.getBitmap();
                ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, stream1);
                byte[] review1Byte = stream1.toByteArray();                             //바이트배열로 저장
                img1 = ReviewFormat.byteArrayToBinaryString(review1Byte);        //저장

                System.out.println(img1);

            }catch(Exception e){

                System.out.println("사진 비어 있음");

            }

            if(img1 != null){       //사진이 비어있지 않을 경우 추가

                imgList.add(img1);

            }

            //두 번째 칸
            try {

                //일일히 해줘야한다.(애초에 배열로 안 묶어서 안된다.)
                ImageView imgView2 = (ImageView) findViewById(R.id.cafe_image2);        //소스를 가져온다.
                BitmapDrawable drawable2 = (BitmapDrawable) imgView2.getDrawable();
                Bitmap bitmap2 = drawable2.getBitmap();
                ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, stream2);
                byte[] review2Byte = stream2.toByteArray();                             //바이트배열로 저장
                img2 = ReviewFormat.byteArrayToBinaryString(review2Byte);        //저장

                System.out.println(img2);

            }catch(Exception e){

                System.out.println("사진 비어 있음");

            }

            if(img2 != null){       //사진이 비어있지 않을 경우 추가

                imgList.add(img2);

            }

            //세 번째 칸
            try {

                //일일히 해줘야한다.(애초에 배열로 안 묶어서 안된다.)
                ImageView imgView3 = (ImageView) findViewById(R.id.cafe_image3);        //소스를 가져온다.
                BitmapDrawable drawable3= (BitmapDrawable) imgView3.getDrawable();
                Bitmap bitmap3 = drawable3.getBitmap();
                ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
                bitmap3.compress(Bitmap.CompressFormat.JPEG, 100, stream3);
                byte[] review3Byte = stream3.toByteArray();                             //바이트배열로 저장
                img3 = ReviewFormat.byteArrayToBinaryString(review3Byte);        //저장

                System.out.println(img3);

            }catch(Exception e){

                System.out.println("사진 비어 있음");

            }

            if(img3 != null){       //사진이 비어있지 않을 경우 추가

                imgList.add(img3);

            }

            //네 번째 칸칸
            try {

                //일일히 해줘야한다.(애초에 배열 안 묶어서 안된다.)
                ImageView imgView4 = (ImageView) findViewById(R.id.cafe_image4);        //소스를 가져온다.
                BitmapDrawable drawable4 = (BitmapDrawable) imgView4.getDrawable();
                Bitmap bitmap4 = drawable4.getBitmap();
                ByteArrayOutputStream stream4 = new ByteArrayOutputStream();
                bitmap4.compress(Bitmap.CompressFormat.JPEG, 100, stream4);
                byte[] review4Byte = stream4.toByteArray();                             //바이트배열로 저장
                img4 = ReviewFormat.byteArrayToBinaryString(review4Byte);        //저장

                System.out.println(img4);

            }catch(Exception e){

                System.out.println("사진 비어 있음");

            }

                if(img4 != null){       //사진이 비어있지 않을 경우 추가

                    imgList.add(img4);

                }

            //다섯 번째 칸
            try {

                //일일히 해줘야한다.(애초에 배열로 안 묶어서 안된다.)
                ImageView imgView5 = (ImageView) findViewById(R.id.cafe_image5);        //소스를 가져온다.
                BitmapDrawable drawable5 = (BitmapDrawable) imgView5.getDrawable();
                Bitmap bitmap5 = drawable5.getBitmap();
                ByteArrayOutputStream stream5 = new ByteArrayOutputStream();
                bitmap5.compress(Bitmap.CompressFormat.JPEG, 100, stream5);
                byte[] review5Byte = stream5.toByteArray();                             //바이트배열로 저장
                img5 = ReviewFormat.byteArrayToBinaryString(review5Byte);        //저장

                System.out.println(img5);

            }catch(Exception e){

                System.out.println("사진 비어 있음");

            }

            if(img5 != null){       //사진이 비어있지 않을 경우 추가

                imgList.add(img5);

            }

            //여섯 번째 칸
           try {

                    //일일히 해줘야한다.(애초에 배열로 안 묶어서 안된다.)
                    ImageView imgView6 = (ImageView) findViewById(R.id.cafe_image6);        //소스를 가져온다.
                    BitmapDrawable drawable6 = (BitmapDrawable) imgView6.getDrawable();
                    Bitmap bitmap6 = drawable6.getBitmap();
                    ByteArrayOutputStream stream6 = new ByteArrayOutputStream();
                    bitmap6.compress(Bitmap.CompressFormat.JPEG, 100, stream6);
                    byte[] review6Byte = stream6.toByteArray();                             //바이트배열로 저장
                    img6 = ReviewFormat.byteArrayToBinaryString(review6Byte);        //저장

                    System.out.println(img6);

                }catch(Exception e){

                    System.out.println("사진 비어 있음");

                }

                if(img6 != null){       //사진이 비어있지 않을 경우 추가

                    imgList.add(img6);

                }


            //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ   사진 전부 저장함 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

            EditText reviewText = (EditText)findViewById(R.id.review);
            String review = reviewText.getText().toString();                //리뷰 저장

            EditText titleText = (EditText)findViewById(R.id.cafe_name);
            String title = titleText.getText().toString();

            String nickname = Login.nickname;                               //닉네임 가져옴

            RatingBar rb = (RatingBar)findViewById(R.id.star);
            float score = rb.getRating();                                           //별 점수 가져옴

            //리뷰 형식 모두 저장
            ReviewFormat rf = new ReviewFormat(state,city,today,title,nickname,imgList,review,score);


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference followdb = database.getInstance().getReference("FollowingReview").child(Login.nickname);
            DatabaseReference localdb = database.getInstance().getReference("LocalReview").child(state);

            //저장
            followdb.push().setValue(rf);
            localdb.push().setValue(rf);

            Intent mainIntent = new Intent(ReviewReport.this,MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainIntent);

            }
        });


            //작성을 취소할 경우우
           Button cancle_Button = (Button)findViewById(R.id.cancle);
            cancle_Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent homeIntent = new Intent(ReviewReport.this, MainActivity.class);
                    startActivity(homeIntent);

                }
            });




    }//onCreate 끝






//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ   여기는 이미지 가지고 돌아왔을 경우 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

    @Override
    protected void onActivityResult(int requstCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    InputStream inputStream = null;
                    try {
                        inputStream = getContentResolver().openInputStream(uri);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageView1.setImageBitmap(bitmap);
                }
                break;

            case 2:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    InputStream inputStream = null;
                    try {
                        inputStream = getContentResolver().openInputStream(uri);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageView2.setImageBitmap(bitmap);
                }
                break;

            case 3:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    InputStream inputStream = null;
                    try {
                        inputStream = getContentResolver().openInputStream(uri);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageView3.setImageBitmap(bitmap);
                }
                break;

            case 4:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    InputStream inputStream = null;
                    try {
                        inputStream = getContentResolver().openInputStream(uri);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageView4.setImageBitmap(bitmap);
                }
                break;

            case 5:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    InputStream inputStream = null;
                    try {
                        inputStream = getContentResolver().openInputStream(uri);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageView5.setImageBitmap(bitmap);
                }
                break;

            case 6:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    InputStream inputStream = null;
                    try {
                        inputStream = getContentResolver().openInputStream(uri);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageView6.setImageBitmap(bitmap);
                }
                break;






        }
    }











}