package www.rimes._int.rimes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapterFrag1 extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    Bundle b0, b1, b2, b3;

    public PagerAdapterFrag1(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {

            case 0:
                b0 = new Bundle();
                b0.putString("forecasttype", "3-days");
                b0.putInt("id", position);
                return FragmentWeatherInfo.newInstance(b0);
            case 1:
                b1 = new Bundle();
                b1.putString("forecasttype", "10-days");
                b1.putInt("id", position);
                return FragmentWeatherInfo.newInstance(b1);
            case 2:
                b2 = new Bundle();
                b2.putString("forecasttype", "1 month");
                b2.putInt("id", position);
                return FragmentWeatherInfo.newInstance(b2);
            case 3:
                b3 = new Bundle();
                b3.putString("forecasttype", "3-months");
                b3.putInt("id", position);
                return FragmentWeatherInfo.newInstance(b3);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}