package lpo.boardandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import lpo.boardandroidapp.adapter.TabPagerAdapter;
import lpo.boardandroidapp.fragment.WriteFragment;


public class BoardMainActivity extends AppCompatActivity {

    protected static final String TAG = "BoardMainActivity";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Button mHomeBtn;
    private Button mWriteBtn;
    private Button mMyContentsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Log.d(TAG, "onCreate() savedInstanceState" + savedInstanceState);

        // Setting the FragmentManager


        // Initializing the Button
        mHomeBtn = (Button) findViewById(R.id.btn_home);
        mWriteBtn = (Button) findViewById(R.id.btn_write);
        mMyContentsBtn = (Button) findViewById(R.id.btn_my_contents);

        // Initializing the TabLayout
//        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        mTabLayout.addTab(mTabLayout.newTab().setText("메인"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("내가 쓴 글"));
//        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // 동적 탭 생성 Test
        ArrayList<String> menuList = new ArrayList<>();
        menuList.add(0, "test1");
        menuList.add(1, "test2");
        menuList.add(2, "test3");

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        for (int i = 0 ; i < menuList.size() ; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(menuList.get(i)));
        }
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

        mWriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnPopupClick(v);
            }
        });
    }

    public void mOnPopupClick(View v) {
        Intent intent = new Intent(this, WritePopupActivity.class);
        intent.putExtra("data", "Testing");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode==RESULT_OK) {
                String result = data.getStringExtra("result");
                Log.d(TAG, "Popup Test = " + result);
            }
        }
    }

}
