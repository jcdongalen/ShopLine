package com.pinas.xburner.shopline.Activity;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pinas.xburner.shopline.Custom.mTextView;
import com.pinas.xburner.shopline.Fragments.AllItems;
import com.pinas.xburner.shopline.Fragments.Completed;
import com.pinas.xburner.shopline.Fragments.HighlightsFragment;
import com.pinas.xburner.shopline.Fragments.Inquiry;
import com.pinas.xburner.shopline.Fragments.Shipping;
import com.pinas.xburner.shopline.Fragments.ToShip;
import com.pinas.xburner.shopline.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivityCustomer extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerLayout;
    ImageButton btnDrawer, btnMore, btnCart;
    TextView tvTitle;
    LinearLayout leftDrawer, rightDrawer;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] tabNames = {"Highlights", "All Items", "Trending", "Brands", "Outdoor", "Sports", "Kids"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_customer);

        initialization();
    }

    private void initialization() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        btnDrawer = (ImageButton) findViewById(R.id.btnDrawer);
        btnCart = (ImageButton) findViewById(R.id.btnCart);
        btnMore = (ImageButton) findViewById(R.id.btnMore);

        btnDrawer.setOnClickListener(this);
        btnCart.setOnClickListener(this);
        btnMore.setOnClickListener(this);

        leftDrawer = (LinearLayout) findViewById(R.id.leftDrawer);
        rightDrawer = (LinearLayout) findViewById(R.id.rightDrawer);

        tvTitle = (TextView) findViewById(R.id.tvTitle);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDrawer:
                drawerLayout.openDrawer(leftDrawer);
                break;
            case R.id.btnMore:
                drawerLayout.openDrawer(rightDrawer);
                break;
            case R.id.btnCart:
                break;
        }
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HighlightsFragment(), "Highlights");
        adapter.addFragment(new AllItems(), "All Items");
        adapter.addFragment(new Shipping(), "Shipping");
        adapter.addFragment(new Completed(), "Completed");
        adapter.addFragment(new Completed(), "Completed");
        adapter.addFragment(new HighlightsFragment(), "Highlights");
        adapter.addFragment(new HighlightsFragment(), "Highlights");
        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }
    }
}
