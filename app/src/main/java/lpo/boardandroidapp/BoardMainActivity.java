package lpo.boardandroidapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    private static final String baseUrl = "http://feelfos.cafe24.com/";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button btn;
    private Context mContext;
    private BoardMainAdapter bmAdapter;
    private BoardMainRes mBoardMainRes;

    private final String params = "FEELFOS";
    private final String contetns = "abcdefghijklmnopqrstuvwxyz. ABCDEFGHIJKLMNOPQRSTUVWXYZ. 가나다라마바사아자차카타파하. 1234567890. ㄱㄴㄷㄹㅁㅂㅅㅇㅈㅊㅋㅌㅍㅎ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_main);

        btn = (Button) findViewById(R.id.send_btn);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override

            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sending", Toast.LENGTH_SHORT).show();
                // GET
                retrofitGetTest();
                // POST
                retrofitPostTest();
            }
        });

        // RecyclerView
        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.main_item_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    /**
     * Retrofit2 Get 방식
     */
    public void retrofitGetTest() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContentService service = retrofit.create(ContentService.class);

//        Call<BoardMainRes> call = service.getBoard();
        // 파라미터 테스트
        Call<BoardMainRes> call = service.getBoard(params);

        call.enqueue(new Callback<BoardMainRes>() {
            @Override
            public void onResponse(Call<BoardMainRes> call, Response<BoardMainRes> response) {
                if (response.isSuccessful()) {
                    mBoardMainRes = response.body();
                    bmAdapter = new BoardMainAdapter(mBoardMainRes);
                    mRecyclerView.setAdapter(bmAdapter);
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Not Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BoardMainRes> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Retrofit2 Post 방식
     */
    public void retrofitPostTest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContentService service = retrofit.create(ContentService.class);
        Call<BoardMainRes> call = service.getPostBoard(params, contetns);

        call.enqueue(new Callback<BoardMainRes>() {
            @Override
            public void onResponse(Call<BoardMainRes> call, Response<BoardMainRes> response) {
                if (response.isSuccessful()) {
                    mBoardMainRes = response.body();
                    bmAdapter = new BoardMainAdapter(mBoardMainRes);
                    mRecyclerView.setAdapter(bmAdapter);
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Not Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BoardMainRes> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
