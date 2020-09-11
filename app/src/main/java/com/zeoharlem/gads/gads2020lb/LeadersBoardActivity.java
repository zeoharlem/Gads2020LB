package com.zeoharlem.gads.gads2020lb;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zeoharlem.gads.gads2020lb.ui.main.LearnersFragment;
import com.zeoharlem.gads.gads2020lb.ui.main.SkillFragment;
import com.zeoharlem.gads.gads2020lb.ui.main.ViewPagerAdapter;

public class LeadersBoardActivity extends AppCompatActivity {

    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaders_board);

        mTabLayout                  = findViewById(R.id.tabs);
        ViewPager viewPager         = findViewById(R.id.view_pager);
        Button submitProject        = findViewById(R.id.postProject);
        FloatingActionButton fab    = findViewById(R.id.fab);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, getSupportFragmentManager());
        viewPagerAdapter.addFragment(new LearnersFragment(), "Learners Leaders");
        viewPagerAdapter.addFragment(new SkillFragment(), "Skill IQ Leaders");
        viewPager.setAdapter(viewPagerAdapter);

        mTabLayout.setupWithViewPager(viewPager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent   = new Intent(LeadersBoardActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

        submitProject.setTypeface(ViewAttributes.getIntanceViewAttributes(this).getMySpartanBlack());
        submitProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent   = new Intent(LeadersBoardActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

        changeTabsFont();
    }

    private void changeTabsFont() {
        ViewGroup vg = (ViewGroup) mTabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(ViewAttributes.getIntanceViewAttributes(
                            getApplicationContext()).getMySpartanBlack());
                }
            }
        }
    }
}