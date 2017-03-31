package lpo.boardandroidapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import lpo.boardandroidapp.fragment.BoardFragment;
import lpo.boardandroidapp.fragment.MainTabFragment;
import lpo.boardandroidapp.fragment.MyBoardFragment;

/**
 * Created by Woonho on 2017. 3. 16..
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    protected static final String TAG = "TabPagerAdapter";
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            // 메인
            case 0:
                return new MainTabFragment();
            // 타입이 다른 Fragment 추가 시 필요
            case 1:
                return new BoardFragment();
            // 타입이 같은 Fragment는 모두 default로 생성
            default:
                return new MyBoardFragment(position);
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
