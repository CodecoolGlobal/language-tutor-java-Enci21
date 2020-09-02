package com.codecool.languagetutor.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.roomDataBase.History;

import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder> {

    List<History> allHistories;

    public HistoryListAdapter(List<History> allHistories) {
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
        History history = allHistories.get(position);
        holder.ratio.setText(history.getRatio());
        holder.date.setText(history.getDate().toString().substring(0, 16));
        holder.incorrectWords.setText(history.getIncorrectWords());
    }

    @Override
    public int getItemCount() {
        return allHistories.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        final TextView ratio;
        final TextView date;
        final TextView incorrectWords;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ratio = itemView.findViewById(R.id.ratioTextView);
            date = itemView.findViewById(R.id.dateTextView);
            incorrectWords = itemView.findViewById(R.id.incorrect_words);
        }
    }
}
