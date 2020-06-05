package com.codecool.languagetutor.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.roomDataBase.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    List<Word> words;

    public WordListAdapter(List<Word> words) {
        this.words = words;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item, parent, false);
        return new WordListAdapter.WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word current = words.get(position);
        holder.translationText.setText(current.getTranslation());
        holder.englishWordText.setText(current.getEnWord());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {

        final TextView englishWordText;
        final TextView translationText;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            englishWordText = itemView.findViewById(R.id.engWord);
            translationText = itemView.findViewById(R.id.transWord);
        }
    }
}
