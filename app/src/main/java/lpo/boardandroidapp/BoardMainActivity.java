package lpo.boardandroidapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import lpo.boardandroidapp.adapter.BoardMainAdapter;
import lpo.boardandroidapp.android.retrofit2.ContentService;
import lpo.boardandroidapp.response.BoardMainRes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BoardMainActivity extends Activity {

    protected static final String TAG = "BoardMainActivity";
    private final String baseUrl = "http://feelfos.cafe24.com/";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button btn;
    private Context mContext;
    private BoardMainAdapter bmAdapter;
    private ArrayList<BoardMainRes> mBoardMainRes;
    private BoardMainRes test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_main);

        btn = (Button) findViewById(R.id.send_btn);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sending", Toast.LENGTH_SHORT).show();
                retrofitTest();
                bmAdapter = new BoardMainAdapter(mBoardMainRes);
                mRecyclerView.setAdapter(bmAdapter);
            }
        });

        // RecyclerView
        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.main_item_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
//        bmAdapter = new BoardMainAdapter();
        mRecyclerView.setAdapter(bmAdapter);

    }

    // retrofit 테스트
    public void retrofitTest() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContentService service = retrofit.create(ContentService.class);
        Call<BoardMainRes> call = service.getBoard();

        call.enqueue(new Callback<BoardMainRes>() {
            @Override
            public void onResponse(Call<BoardMainRes> call, Response<BoardMainRes> response) {
                if (response.isSuccessful()) {
//                    mBoardMainRes = response.body();
//                    Toast.makeText(getApplicationContext(), "success = " + bmRes.list.get(0).getTitle(), Toast.LENGTH_SHORT).show();
//                    ArrayList<BoardMainRes> dataList = mBoardMainRes;
//                    bmAdapter.updateMainBoardData(mBoardMainRes);

                } else {
                    Toast.makeText(getApplicationContext(), "not success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BoardMainRes> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
