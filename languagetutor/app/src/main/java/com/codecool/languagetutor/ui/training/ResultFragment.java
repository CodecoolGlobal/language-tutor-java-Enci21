package com.codecool.languagetutor.ui.training;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codecool.languagetutor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultFragment extends Fragment {

    public static final String CLASS_EXCEPTION_MSG = " must implement OnResultListener";
    private ClosingInterface closingInterface;

    @BindView(R.id.exitButton)
    Button exitButton;

    public ResultFragment() {
    }

    public interface ClosingInterface {
        void saveResult();

        void onClose();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        ButterKnife.bind(this, view);
        setUpClickListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        closingInterface.saveResult();
    }

    private void setUpClickListener() {
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closingInterface.onClose();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            closingInterface = (ResultFragment.ClosingInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + CLASS_EXCEPTION_MSG);
        }
    }




}
