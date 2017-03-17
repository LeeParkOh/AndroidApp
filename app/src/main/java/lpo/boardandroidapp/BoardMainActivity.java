package lpo.boardandroidapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import lpo.boardandroidapp.adapter.TabPagerAdapter;
import lpo.boardandroidapp.fragment.WriteFragment;


public class BoardMainActivity extends AppCompatActivity {

    protected static final String TAG = "BoardMainActivity";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Button mHomeBtn;
    private Button mWriteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Log.d(TAG, "onCreate() savedInstanceState" + savedInstanceState);

        // Setting the FragmentManager



        // Initializing the Button
        mHomeBtn = (Button) findViewById(R.id.btn_home);
        mWriteBtn = (Button) findViewById(R.id.btn_write);

        mWriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open();
            }
        });

        // Initializing the TabLayout
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("메인"));
        mTabLayout.addTab(mTabLayout.newTab().setText("내가 쓴 글"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Initializing & Creating ViewPager
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void open() {
        WriteFragment writeFragment = new WriteFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.write_fragment, writeFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
