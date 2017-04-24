package lpo.boardandroidapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import lpo.boardandroidapp.adapter.BoardMainAdapter;
import lpo.boardandroidapp.adapter.TabPagerAdapter;
import lpo.boardandroidapp.android.retrofit2.ContentService;
import lpo.boardandroidapp.fragment.MainTabFragment;
import lpo.boardandroidapp.request.LoginReq;
import lpo.boardandroidapp.request.WriteReq;
import lpo.boardandroidapp.response.BoardMainRes;
import lpo.boardandroidapp.response.TabListRes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BoardMainActivity extends AppCompatActivity {

    protected static final String TAG = "BoardMainActivity";
    private static final String URL = "http://feelfos.cafe24.com/";
    private static final String EXTR_TITLE_ARG = "EXTR_TITLE_ARG";
    private static final String EXTR_CONTENT_ARG = "EXTR_CONTENT_ARG";
    public static final String EXTR_ID_ARG = "EXTR_ID_ARG";
    public static final String EXTR_PW_ARG = "EXTR_PW_ARG";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Button mHomeBtn;
    private Button mWriteBtn;
    private Button mMyContentsBtn;
    private TabListRes mTabListRes;
    private WriteReq mWriteReq;
    private LoginReq mLoginReq;
    private SharedPreferences spf;
    private SharedPreferences.Editor editor;

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
                onWritePopupClick(v);
            }
        });

        mMyContentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogingPopupClick(v);
            }
        });

        new CreateTabTask().execute();

    }

    /**
     * 토큰 존재 유무로 로그인 확인 메서드
     * @return
     */
    public boolean checkLogin() {
        if (spf == null) {
            return false;
        }
        String token = spf.getString("user-token", "0");
        if (token.equals("0")) {
            return false;
        }
        return true;
    }

    /**
     * token 삭제 (아직 사용 X)
     */
    public void deleteToken() {
        editor = spf.edit();
        editor.remove("user-token");
        editor.commit();
    }

    /**
     * 토큰 받아서 Preference에 저장
     * @param token 서버에서 받아온 토큰
     */
    public void setPreferences(String token) {
        Log.d(TAG, "token check = " + token);
        spf = getSharedPreferences("token", MODE_PRIVATE);
        editor = spf.edit();
        editor.putString("user-token", token);
        editor.commit();
    }

    /**
     * 글쓰기 팝업 처리
     * @param v
     */
    public void onWritePopupClick(View v) {
        if (!(checkLogin())) {
            return;
        }
        Intent intent = new Intent(this, WritePopupActivity.class);
        startActivityForResult(intent, 1);
    }

    public void onLogingPopupClick(View v) {
        Intent intent = new Intent(this, LoginPopupActivity.class);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String title = data.getStringExtra(EXTR_TITLE_ARG);
                String content = data.getExtras().getString(EXTR_CONTENT_ARG);
                onBoardUpdate(title, content);
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                String userId = data.getStringExtra(EXTR_ID_ARG);
                String userPw = data.getStringExtra(EXTR_PW_ARG);

                Toast.makeText(this, userId + " || " + userPw, Toast.LENGTH_LONG).show();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ContentService service = retrofit.create(ContentService.class);
                Call<LoginReq> call = service.getToken(userId, userPw);

                call.enqueue(new Callback<LoginReq>() {
                    @Override
                    public void onResponse(Call<LoginReq> call, Response<LoginReq> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "Retrofit Response Success");
                            mLoginReq = response.body();

                            Log.d(TAG, "Login ResultCode = " + mLoginReq.resultCode);
                            Log.d(TAG, "Login token = " + mLoginReq.token);
                            Log.d(TAG, "Login ResultMsg = " + mLoginReq.resultMsg);
                            setPreferences(mLoginReq.token);

//                            mBoardMainAdapter = new BoardMainAdapter(mBoardMainRes);
//                            mRecyclerView.setAdapter(mBoardMainAdapter);
                        } else {
                            Log.d(TAG, "Retrofit Response Not Success");
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginReq> call, Throwable t) {
                        Log.d(TAG, "Retrofit Response Failed");
                    }
                });
            }
        }
    }

    /**
     * 서버에서 게시판 종류 리스트를 받아와서 동적 탭 구성에 사용
     */
    public void getTabList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
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
     * 새로 고침
     * @param title
     * @param content
     */
    public void onBoardUpdate(final String title, final String content) {

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ContentService service = retrofit.create(ContentService.class);
                Call<WriteReq> call = service.getInsertResult("", "", "001", title, "I", content, "");

                try {
                    mWriteReq = call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                Log.d(TAG, "Result Code = " + mWriteReq.resultCode);
                Log.d(TAG, "Result Message = " + mWriteReq.resultMsg);

                MainTabFragment mtFragment = new MainTabFragment();
                mtFragment.onFetchStart();
            }
        }.execute();

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
