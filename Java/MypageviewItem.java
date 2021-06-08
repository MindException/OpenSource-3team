package com.cookandroid.opensw_3team_cafereviewproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MypageviewItem {

    ReviewFormat rf;
    List<String> mypictures;

    MypageviewItem(ReviewFormat rf){

        this.rf = rf;

    }

    public Bitmap getBitmap(){              //여기서 넘겨 줄 값을 작성하면 viewer가 가서 이걸 가져가는 것이다.

        Bitmap bmp;
        mypictures = rf.pictures;               //일단 사진첩을 가져온다.
        String picture = mypictures.get(0);     //처음 것만 가져옴
        byte[] byteP = ReviewFormat.binaryStringToByteArray(picture);           //바이트까지 바꿨다.
        bmp = BitmapFactory.decodeByteArray(byteP, 0, byteP.length);      //비트맵 변환 성공

        return bmp;

    }


    public ReviewFormat getRf() {
        return rf;
    }

    public void setRf(ReviewFormat rf) {
        this.rf = rf;
    }

}
