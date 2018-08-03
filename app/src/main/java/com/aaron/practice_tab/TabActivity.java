package com.aaron.practice_tab;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TabActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnTab1;
    private Button mBtnTab2;

    private FragmentManager mFragmentManager;
    private FragmentTransaction transaction;
    private Tab1Fragment mTab1Fragment = new Tab1Fragment();
    private Tab2Fragment mTab2Fragment = new Tab2Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        mFragmentManager = getFragmentManager();
        mBtnTab1 = findViewById(R.id.tab1);
        mBtnTab2 = findViewById(R.id.tab2);

        mBtnTab1.setOnClickListener(this);
        mBtnTab2.setOnClickListener(this);

        transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.layout, mTab1Fragment, "Tab1")
                .add(R.id.layout, mTab2Fragment, "Tab2")
                .show(mTab1Fragment)
                .hide(mTab2Fragment)
                .commit();
    }

    @Override
    public void onClick(View v) {
        transaction = mFragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.tab1:
                mBtnTab1.setClickable(false);
                mBtnTab2.setClickable(true);
                if (!mTab1Fragment.isAdded()) {
                    transaction.add(R.id.layout, mTab1Fragment).show(mTab1Fragment).hide(mTab2Fragment);
                } else {
                    transaction.show(mTab1Fragment).hide(mTab2Fragment);
                }
                transaction.commit();
                break;
            case R.id.tab2:
                mBtnTab1.setClickable(true);
                mBtnTab2.setClickable(false);
                if (!mTab2Fragment.isAdded()) {
                    transaction.add(R.id.layout, mTab2Fragment).show(mTab2Fragment).hide(mTab1Fragment);
                } else {
                    transaction.show(mTab2Fragment).hide(mTab1Fragment);
                }
                transaction.commit();
                break;
        }
    }
}
