package com.cookandroid.opensw_3team_cafereviewproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.util.ArrayList;
import java.util.List;


public class Local extends Fragment {

    String location = "";
    private View view;
    RecyclerVierAdapter adapter;
    ArrayList<String> myfollwing = new ArrayList<String>();             //내가 팔로우한 사람들 리스트 저장
    List<ReviewFormat> myreviews = new ArrayList<ReviewFormat>();              //저장

    Local(String location){

        this.location = location;           //지역 저장 성공

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.local, container, false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reviewRef = database.getReference("LocalReview").child(location);     //지역에서 가져온다.

        reviewRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myreviews.clear();        //리스트 청소
                DataSnapshot mySnapshot;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {  //지역별로 가져온다.

                    ReviewFormat rf = snapshot.getValue(ReviewFormat.class);
                    rf.setlocalid(snapshot.getKey());
                    myreviews.add(rf);

                }

                myreviews.sort(new CompareReview<ReviewFormat>());      //이렇게 하면 최신순으로 정령한다.

                //여기 위까지 완벽하다. 전부 최신순으로 다 가져온다.

                if(myreviews.size() != 0) {

                    init();

                }

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



