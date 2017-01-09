package www.rimes._int.rimes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;


public class PagerAdapterMain extends FragmentStatePagerAdapter {

    private int num_tabs;

    public PagerAdapterMain(FragmentManager fm, int num_tabs) {
        super(fm);
        this.num_tabs = num_tabs;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragment1 tab1 = new TabFragment1(); //Weather Info
                return tab1;
            case 1:
                TabFragment2 tab2 = new TabFragment2(); //Weather Advisory
                return tab2;
            case 2:
                TabFragment3 tab3 = new TabFragment3(); //Crop Info
                return tab3;
            case 3:
                TabFragment4 tab4 = new TabFragment4(); //Crop Advisory
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return num_tabs;
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