package lpo.boardandroidapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lpo.boardandroidapp.R;

/**
 * Created by leewoonho on 2017. 3. 15..
 */

public class MyBoardFragment extends BaseFragment {

    protected static final String TAG = "MyBoardFragment";
    private int mPosition;
    private static String mBoardType;

    public MyBoardFragment() {
    }
    @SuppressLint("ValidFragment")
    public MyBoardFragment(int position) {
        mPosition = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBoardType = setBoardCd(mPosition);
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
    }
}
