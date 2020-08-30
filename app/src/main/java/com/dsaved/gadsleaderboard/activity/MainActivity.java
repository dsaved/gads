package com.dsaved.gadsleaderboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dsaved.gadsleaderboard.R;
import com.dsaved.gadsleaderboard.utils.Navigator;
import com.dsaved.gadsleaderboard.utils.SlidingTabLayout;
import com.dsaved.gadsleaderboard.fragments.LearningLeaders;
import com.dsaved.gadsleaderboard.fragments.SkillIQLeaders;

public class MainActivity extends AppCompatActivity {

    SlidingTabLayout tabHost;
    ViewPager pager;
    ViewPagerAdapter adapter;
    Navigator navigator;
    Button submit;

    int Numboftabs = 2;
    CharSequence[] Titles = {"Learning Leaders", "Skill IQ Leaders"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigator = new Navigator(this);

        tabHost = findViewById(R.id.tabHost);
        pager = findViewById(R.id.pager);
        submit = findViewById(R.id.submit);

        tabHost.setDistributeEvenly(true);
        tabHost.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorAccent);
            }
        });

        tabHost.setTexSizeTabs(15);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(Numboftabs);

        // Setting the ViewPager For the SlidingTabsLayout
        tabHost.setViewPager(pager);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigator.to(Submiission.class).open(false);
            }
        });
    }


    private class ViewPagerAdapter extends FragmentStatePagerAdapter {


        CharSequence[] Titles; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
        int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


        // Build a Constructor and assign the passed Values to appropriate values in the class
        public ViewPagerAdapter(FragmentManager fm, CharSequence[] mTitles, int mNumbOfTabsumb) {
            super(fm);
            this.Titles = mTitles;
            this.NumbOfTabs = mNumbOfTabsumb;
        }

        // This method return the titles for the Tabs in the Tab Strip

        @Override
        public CharSequence getPageTitle(int position) {
            return Titles[position];
        }

        public Fragment getItem(int num) {

            switch (num) {
                case 0:
                    return new LearningLeaders();
                case 1:
                    return new SkillIQLeaders();

            }
            return null;
        }

        // This method return the Number of tabs for the tabs Strip

        @Override
        public int getCount() {
            return NumbOfTabs;
        }
    }
}