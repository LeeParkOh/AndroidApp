package lpo.boardandroidapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import lpo.boardandroidapp.fragment.MainTabFragment;
import lpo.boardandroidapp.fragment.MyBoardFragment;

/**
 * Created by Woonho on 2017. 3. 16..
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MainTabFragment mainTabFragment = new MainTabFragment();
//                return mainTabFragment;
            case 1:
                MyBoardFragment myBoardFragment = new MyBoardFragment();
//                return myBoardFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
