package www.rimes._int.rimes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
//import android.util.Log;
import android.view.ViewGroup;

public class PagerAdapterMain extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterMain(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

       // Log.d("--InstantiateItem"+String.valueOf(position+1), "!!");

        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {

       // Log.d("--GetItem"+String.valueOf(position+1), "!!");

        switch (position) {
            case 0:
                TabFragment1 tab1 = new TabFragment1();
                return tab1;
            case 1:
                TabFragment2 tab2 = new TabFragment2();
                return tab2;
            case 2:
                TabFragment3 tab3 = new TabFragment3();
                return tab3;
            case 3:
                TabFragment4 tab4 = new TabFragment4();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Weather Info";

            case 1:
                return "Weather Advisory";

            case 2:
                return "Crop Info";

            case 3:
                return "Crop Advisory";

            default:
                return null;

        }

    }
}