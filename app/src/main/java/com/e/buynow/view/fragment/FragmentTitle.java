package com.e.buynow.view.fragment;

import androidx.fragment.app.Fragment;

public abstract class FragmentTitle extends Fragment {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
