package com.qq281982108.tallylight.activity;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.qq281982108.bottom_bar.BottomBar;
import com.qq281982108.bottom_bar.BottomBarFragment;
import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.fragment.LeftFragment;
import com.qq281982108.tallylight.fragment.SampleFragment;
import com.qq281982108.tallylight.fragment.TimeLineFragment;

public class MainActivity extends BaseActivity {
    LeftFragment fragment = new LeftFragment();
    SampleFragment sampleFragment = new SampleFragment();
    TimeLineFragment mTimeLineFragment = new TimeLineFragment();
    private BottomBar mBottomBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setFragmentItems(
                getSupportFragmentManager(),
                R.id.fragmentContainer,
                new BottomBarFragment(fragment, R.drawable.ic_recents, "Recents"),
                new BottomBarFragment(sampleFragment, R.drawable.ic_favorites, "Favorites"),
                new BottomBarFragment(mTimeLineFragment, R.drawable.ic_nearby, "Nearby"),
                new BottomBarFragment(fragment, R.drawable.ic_friends, "Friends"),
                new BottomBarFragment(fragment, R.drawable.ic_restaurants, "Food")
        );

        // Setting colors for different tabs when there's more than three of them.
        // You can set colors for tabs in three different ways as shown below.
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
        mBottomBar.mapColorForTab(1, 0xFF5D4037);
        mBottomBar.mapColorForTab(2, "#7B1FA2");
        mBottomBar.mapColorForTab(3, "#FF5252");
        mBottomBar.mapColorForTab(4, "#FF9800");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }
}
