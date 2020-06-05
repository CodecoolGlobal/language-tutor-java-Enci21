package com.codecool.languagetutor.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.roomDataBase.History;
import com.codecool.languagetutor.roomDataBase.HistoryWithWords;
import com.codecool.languagetutor.roomDataBase.HistoryWordCrossRef;

import java.util.Arrays;
import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder> {

    List<HistoryWithWords> allHistories;


    public HistoryListAdapter(List<HistoryWithWords> allHistories) {
        this.allHistories = allHistories;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new HistoryListAdapter.HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        HistoryWithWords history = allHistories.get(position);
        System.out.println("array++++++++++++++++" + Arrays.toString( history.getIncorrectWordsId().toArray()));
        holder.ratio.setText(history.getIncorrectWordsId().toString());
        holder.date.setText(history.getHistory().getDate().toString());
    }

    @Override
    public int getItemCount() {
        return allHistories.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        final TextView ratio;
        final TextView date;


        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ratio = itemView.findViewById(R.id.ratioTextView);
            date = itemView.findViewById(R.id.dateTextView);
        }
    }
}
