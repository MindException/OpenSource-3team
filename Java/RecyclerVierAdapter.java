package com.cookandroid.opensw_3team_cafereviewproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerVierAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ReviewFormat> listData = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //여기서 피드 일단 연결 성공
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed, parent, false);
        return new ViewHolderReview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderReview)holder).onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    void addItem(ReviewFormat data) {      //집어 넣고

        listData.add(data);
    }
}
  