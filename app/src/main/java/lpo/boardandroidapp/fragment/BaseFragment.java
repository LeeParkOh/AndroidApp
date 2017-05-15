package lpo.boardandroidapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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
 * Created by leewoonho on 2017. 3. 17..
 */

public class BaseFragment extends Fragment {

    protected static final String TAG = "BaseFragment";
    private static final String baseUrl = "http://feelfos.cafe24.com/";

    private static Context mContext;
    private static RecyclerView mRecyclerView;
    private static RecyclerView.LayoutManager mLayoutManager;
    private static BoardMainAdapter mBoardMainAdapter;
    private static BoardMainRes mBoardMainRes;
    private String boardCd = "";
    public BaseFragment() {
    }

    /**
     * Tab Position을 boardCd에 맞게 변환 후 리턴시키는 함수
     * @param position Tab Position
     * @return ex) 001, 002... (String)
     */
    public String setBoardCd(int position) {

        int i = position;
        boardCd = "00" + String.valueOf(i);
        return boardCd;
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

    public void checkLogin() {

    }

}
