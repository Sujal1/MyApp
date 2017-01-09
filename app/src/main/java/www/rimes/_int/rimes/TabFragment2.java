package www.rimes._int.rimes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TabFragment2 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab_fragment_2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tt = (TextView) view.findViewById(R.id.textView_test);

        tt.setText("This is the set Test !!");
      /*  ViewPager mviewPager = (ViewPager) view.findViewById(R.id.subPager1);
        mviewPager.setOffscreenPageLimit(4);



        final PagerAdapterFrag1 adapter = new PagerAdapterFrag1
                (getActivity().getSupportFragmentManager(), 4);
        mviewPager.setAdapter(adapter);

        CircleIndicator circleindicator = (CircleIndicator) view.findViewById(R.id.circleindicator);
        circleindicator.setViewPager(mviewPager);*/


    }

}
