package com.cookandroid.opensw_3team_cafereviewproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class home extends Fragment {

    private View view;
    RecyclerVierAdapter adapter;
    ArrayList<String> myfollwing = new ArrayList<String>();             //내가 팔로우한 사람들 리스트 저장
    List<ReviewFormat> myreviews = new ArrayList<ReviewFormat>();              //저장


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home, container, false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Following");                 //자신이 팔로우한 사람들을 추가하여 준다.
        DatabaseReference reviewRef = database.getReference("FollowingReview");     //리스트를 보고 리뷰를 전부 가져온다.

        ref.child(Login.nickname).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                myfollwing.clear();                         //한번 청소하고
                GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
                //이거 리스트 못가져오면 초기화 안되어서 버그 뜨는듯 for : 문으로 변경하자
                ArrayList<String> templist = new ArrayList<String>();

                for(DataSnapshot ds : snapshot.getChildren()){      //전부 다 넣는다.

                    String myfollwer = ds.getValue(String.class);
                    templist.add(myfollwer);

                }


                if(templist.size() == 0){       //안에가 비워져 있다면

                    myfollwing.add(Login.nickname);         //자신의 이름만 추가하기

                }else{          //가져왔다면

                    for(int i = 0; i < templist.size(); i++){

                        myfollwing.add(templist.get(i));        //순서대로 전부 저장

                    }

                    //마지막으로 자기자신 추가
                    myfollwing.add(Login.nickname);

                }   //성공






            }//onDataChange 끝끝

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reviewRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                myreviews.clear();        //리스트 청소
                DataSnapshot mySnapshot;
                for(int i = 0; i < myfollwing.size(); i++){     //전부 가져온다.

                    mySnapshot = snapshot.child(myfollwing.get(i));      //경로를 한 사용자로 지정

                    for (DataSnapshot datasnapshot : mySnapshot.getChildren()) {        //한 계정의 리뷰 전부 저장

                        ReviewFormat rf = datasnapshot.getValue(ReviewFormat.class);
                        rf.setfollowid(datasnapshot.getKey());
                        myreviews.add(rf);

                    }   //개개인의 리뷰들을 저장

                }//팔로잉한 계정 전체의 리뷰 저장

                myreviews.sort(new CompareReview<ReviewFormat>());      //이렇게 하면 최신순으로 정령한다.

                //여기 위까지 완벽하다. 전부 최신순으로 다 가져온다.

                init();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }//onCreate 끝

    private void init(){

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerVierAdapter();

        for(int i = 0; i < myreviews.size(); i++){      //이거 돌려야 들어간다.


            adapter.addItem(myreviews.get(i));          //이거 해줘야 순서대로 다 들어간다.

        }
        recyclerView.setAdapter(adapter);


    }

}
