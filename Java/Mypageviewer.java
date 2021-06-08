package com.cookandroid.opensw_3team_cafereviewproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class Mypageviewer extends LinearLayout {        //실질적인 item xml을 관리한다.

    ImageView img;
    Bitmap bimg;


    public Mypageviewer(Context context) {
        super(context);

        init(context);
    }

    public Mypageviewer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context){      //이렇게 세팅한다.

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.myreviewslist,this,true);

        img = (ImageView) findViewById(R.id.first_pirctures);

    }

    public void setItem(MypageviewItem mvi){        //실질적으로 여기에 저장한다.

       bimg = mvi.getBitmap();   //비트맵으로 일단 저장

       //이제 비트맵을 이미지뷰로 다시 변환하여 저장한다.
       img.setImageBitmap(bimg);

    }

}
