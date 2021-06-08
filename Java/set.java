package com.cookandroid.opensw_3team_cafereviewproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class set extends Fragment {

    private View view;
    private static final String TAG = "set";
    private Context ct;

    private Button reviews;
    private Button follwers;
    private Button following;
    private GridView gl;

    private PictureAdapter pa;

    private long myFollwers = 0;
    private long myFollings = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.set, container, false);

        ct = container.getContext();

        TextView myNickName = (TextView)view.findViewById(R.id.mypage_title);
        myNickName.setText(Login.nickname + "의 조각들");

        reviews = (Button)view.findViewById(R.id.reviews);
        follwers = (Button)view.findViewById(R.id.follwer_button);
        following = (Button)view.findViewById(R.id.following_button);
        gl = (GridView) view.findViewById(R.id.gridlayout);

        //리스트
        List<ReviewFormat> myreviews = new ArrayList<ReviewFormat>();

        //데이터베이스에서 정보를 가져와야한다.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("FollowingReview");   //여기다가 getInstance 넣지 말아라

        //이거 push한거 겟키로 키:follow push 값:locla push

        ref.child(Login.nickname).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {


               if(dataSnapshot.exists()) {

                   myreviews.clear();
                   for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                       ReviewFormat rf = snapshot.getValue(ReviewFormat.class);
                       rf.setfollowid(snapshot.getKey());
                       myreviews.add(rf);

                   }
                   reviews.setText(Integer.toString(myreviews.size()));       //사이즈를 게시물의 수로 저장.
                   Collections.reverse(myreviews);          //최신 순으로 앞에 오게 한다.
                   //여기는 이제 자기가 작성한 리뷰들 사진

                   pa = new PictureAdapter();       //어뎁터 생성
                   for(int i = 0; i < myreviews.size(); i++){

                       pa.addItem(new MypageviewItem(myreviews.get(i)));

                   }//어뎁터를 다 저장하였다.
                   gl.setAdapter(pa);           //어뎁터 장착



               }else{

                   System.out.println("비어있음");

               }

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

                System.out.println("버그터짐");

            }
        });

        DatabaseReference followerShot = database.getReference("Follower").child(Login.nickname);
        followerShot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                myFollwers = 0;
                myFollwers = snapshot.getChildrenCount();
                follwers.setText(Long.toString(myFollwers));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference followingShot = database.getReference("Following").child(Login.nickname);
        followingShot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                myFollings = 0;
                myFollings = snapshot.getChildrenCount();
                following.setText(Long.toString(myFollings));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        return view;
    }//끝


    class PictureAdapter extends BaseAdapter {

        ArrayList<MypageviewItem> items = new ArrayList<MypageviewItem>();

        @Override
        public int getCount() {     //아이템 크기 반환

            return items.size();

        }


        public void addItem(MypageviewItem myitem){     //아이템에 클래스 넣기

            items.add(myitem);

        }

        @Override
        public MypageviewItem getItem(int position) {       //특정 아이템 반환

            return items.get(position);

        }

        @Override
        public long getItemId(int position) {   //아이템에 id를 설정해주는 역할 검색 삭제 때문

            return position;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {     //이거 아직 안함함

            Mypageviewer mpv = new Mypageviewer(ct.getApplicationContext());
            mpv.setItem(items.get(position));

            return mpv;        //이거 null이면 안돌아감

        }

    }

}


