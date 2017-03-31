package lpo.boardandroidapp.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import lpo.boardandroidapp.R;
import lpo.boardandroidapp.adapter.BoardMainAdapter;
import lpo.boardandroidapp.android.retrofit2.ContentService;
import lpo.boardandroidapp.response.BoardMainRes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leewoonho on 2017. 3. 15..
 */

public class MainTabFragment extends Fragment {

    protected static final String TAG = "MainTabFragment";
    private static final String baseUrl = "http://feelfos.cafe24.com/";

    private static Context mContext;
    private static RecyclerView mRecyclerView;
    private static RecyclerView.LayoutManager mLayoutManager;
    private static BoardMainAdapter mBoardMainAdapter;
    private static BoardMainRes mBoardMainRes;
    private String boardCd = "";

    public MainTabFragment() {
    }

//    public MainTabFragment(Context context) {
//        mContext = context;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onFetchStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_main_board, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.main_item_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    /**
     * 게시판 목록 가져오기
     */
    public void onFetchStart() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContentService service = retrofit.create(ContentService.class);
        Call<BoardMainRes> call = service.getPostBoard(boardCd);

        call.enqueue(new Callback<BoardMainRes>() {
            @Override
            public void onResponse(Call<BoardMainRes> call, Response<BoardMainRes> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Retrofit Response Success");
                    mBoardMainRes = response.body();
                    mBoardMainAdapter = new BoardMainAdapter(mBoardMainRes);
                    mRecyclerView.setAdapter(mBoardMainAdapter);
                } else {
                    Log.d(TAG, "Retrofit Response Not Success");
                }
            }

            @Override
            public void onFailure(Call<BoardMainRes> call, Throwable t) {
                Log.d(TAG, "Retrofit Response Failed");
            }
        });
    }


}
