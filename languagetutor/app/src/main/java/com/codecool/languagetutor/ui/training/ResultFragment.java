package com.codecool.languagetutor.ui.training;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codecool.languagetutor.databinding.FragmentResultBinding;


public class ResultFragment extends Fragment {

    public static final String CLASS_EXCEPTION_MSG = " must implement OnResultListener";
    private ClosingInterface closingInterface;
    private FragmentResultBinding binding;

    public ResultFragment() {
    }

    public interface ClosingInterface {
        void saveResult();

        void onClose();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentResultBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        setUpClickListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        closingInterface.saveResult();
    }

    private void setUpClickListener() {
        binding.exitButton.setOnClickListener(v -> closingInterface.onClose());
    }

    @Override
    public void onDestroyView() {
        closingInterface = null;
        super.onDestroyView();
    }

}
