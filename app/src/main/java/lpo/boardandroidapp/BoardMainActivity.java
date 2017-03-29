package lpo.boardandroidapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import lpo.boardandroidapp.adapter.BoardMainAdapter;
import lpo.boardandroidapp.adapter.TabPagerAdapter;
import lpo.boardandroidapp.android.retrofit2.ContentService;
import lpo.boardandroidapp.fragment.WriteFragment;
import lpo.boardandroidapp.response.BoardMainRes;
import lpo.boardandroidapp.response.TabListRes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BoardMainActivity extends AppCompatActivity {

    protected static final String TAG = "BoardMainActivity";
    private static final String baseUrl = "http://feelfos.cafe24.com/";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Button mHomeBtn;
    private Button mWriteBtn;
    private Button mMyContentsBtn;
    private TabListRes mTabListRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Log.d(TAG, "onCreate() savedInstanceState" + savedInstanceState);

        mHomeBtn = (Button) findViewById(R.id.btn_home);
        mWriteBtn = (Button) findViewById(R.id.btn_write);
        mMyContentsBtn = (Button) findViewById(R.id.btn_my_contents);

        mWriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnPopupClick(v);
            }
        });

        new CreateTabTask().execute();

    }

    /**
     * 글쓰기 팝업 처리
     * @param v
     */
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

    /**
     * 서버에서 게시판 종류 리스트를 받아와서 동적 탭 구성에 사용
     */
    public void getTabList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String grpCd = "BD001";
        ContentService service = retrofit.create(ContentService.class);
        Call<TabListRes> call = service.getTab(grpCd);

        try {
            mTabListRes = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 서버를 찔러서 리스트를 받아와야 하기 때문에 UI Thread 말고 별도 Thread 로 분리
     * 동시에 UI도 갱신해야 하기 때문에 Async 를 사용
     */
    private class CreateTabTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // ProgressBar 띄우기
        }

        @Override
        protected Object doInBackground(Object[] params) {
            getTabList();
            return null;
        }

        // UI 갱신
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
            for (int i = 0 ; i < mTabListRes.cmnCdList.size() ; i ++) {
                View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_tab_view, null, false);
                TextView tabText = (TextView) v.findViewById(R.id.tabTextView);
                tabText.setText(mTabListRes.cmnCdList.get(i).dtlCdNm);
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(v));
            }
            mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
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
    }
}
