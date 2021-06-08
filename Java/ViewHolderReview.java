package com.cookandroid.opensw_3team_cafereviewproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class ViewHolderReview extends  RecyclerView.ViewHolder{

    private View view;

    //내가 필요한 것들
    ReviewFormat rf;
    TextView nickname;      //닉네임
    TextView day;           //날짜
    TextView state;         //도
    TextView city;          //시
    TextView reviews;       //리뷰
    RatingBar rb;           //별점
    ImageView picture;

    List<String> pitctures; //사진 저장


    public ViewHolderReview(@NonNull View itemView) {
        super(itemView);

        //전부 저장
        nickname = (TextView)itemView.findViewById(R.id.nickname);
        day = (TextView)itemView.findViewById(R.id.date);
        state = (TextView)itemView.findViewById(R.id.state);
        city = (TextView)itemView.findViewById(R.id.city);
        reviews = (TextView)itemView.findViewById(R.id.reviews);
        rb = (RatingBar)itemView.findViewById(R.id.star);
        picture = (ImageView)itemView.findViewById(R.id.picture);



    }

    public void onBind(ReviewFormat rf){                    //일단 rf가 넘어와서 전부 저장

        //전부 저장
        this.rf = rf;
        nickname.setText(rf.nickname);
        day.setText(rf.day);
        state.setText(rf.state);
        city.setText(rf.city);
        reviews.setText(rf.review);
        rb.setRating(rf.score);
        pitctures = this.rf.pictures;

        Bitmap bmp;
        String pictureS = pitctures.get(0);     //처음 것만 가져옴
        byte[] byteP = ReviewFormat.binaryStringToByteArray(pictureS);           //바이트까지 바꿨다.
        bmp = BitmapFactory.decodeByteArray(byteP, 0, byteP.length);      //비트맵 변환 성공
        picture.setImageBitmap(bmp);



    }




}