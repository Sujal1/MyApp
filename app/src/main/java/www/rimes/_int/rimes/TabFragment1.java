package www.rimes._int.rimes;


import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import uk.co.senab.photoview.PhotoViewAttacher;


public class TabFragment1 extends Fragment {


    int parameter_id;

    private static  WebView wv_map = null;
    private static ViewGroup myParentViewGroup = null;

    Boolean orientationChange = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            orientationChange = savedInstanceState.getBoolean("www.rimes.anint.orientationWeather");
        }
        Log.d("!--TF1 onCreate", String.valueOf(orientationChange));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.ion_test, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView iv_ion = (ImageView) view.findViewById(R.id.imgView_ion);

        Ion.with(getActivity())
                .load("http://www.rimes.int/files/mobtest/last_image.png")
                .withBitmap()
                .placeholder(R.drawable.common_plus_signin_btn_text_light)
                .error(R.mipmap.ic_launcher)
                //.animateLoad(spinAnimation)
                //.animateIn(fadeInAnimation)
                .intoImageView(iv_ion);


        PhotoViewAttacher mAttacher = new PhotoViewAttacher(iv_ion);


       /* if (wv_map == null) {

            myParentViewGroup = (ViewGroup) view.findViewById(R.id.tab_frag1_viewgroup);

            wv_map = (WebView) myParentViewGroup.findViewById(R.id.webview_test);

            wv_map.clearCache(true);
            wv_map.getSettings().setJavaScriptEnabled(true);


        } else {


            Log.d("!--URL Not Loaded", "123");

            int index = myParentViewGroup.indexOfChild(wv_map);

            myParentViewGroup.removeView(wv_map);

            myParentViewGroup = (ViewGroup) view.findViewById(R.id.tab_frag1_viewgroup);

            myParentViewGroup.removeView(myParentViewGroup.findViewById(R.id.webview_test));

            myParentViewGroup.addView(this.wv_map, index);


            Log.d("!WWTT", String.valueOf(((LinearLayout.LayoutParams) wv_map.getLayoutParams()).weight));


        }


        Spinner mspinner_forecast = (Spinner) view.findViewById(R.id.spinner_forecast);
        Spinner mspinner_parameter = (Spinner) view.findViewById(R.id.spinner_parameter);


        List<String> categories_parameter = new ArrayList<String>();
        categories_parameter.add("Precipitation");
        categories_parameter.add("Temperature");
        categories_parameter.add("Humidity");
        categories_parameter.add("PET");

        List<String> categories_forecast = new ArrayList<String>();
        categories_forecast.add("3 days");
        categories_forecast.add("10 days");
        categories_forecast.add("1 month");
        categories_forecast.add("03 months");


        // Creating adapters for spinner
        ArrayAdapter<String> adapter_parameter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories_parameter);
        ArrayAdapter<String> adapter_forecast = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories_forecast);


        // Drop down layout style - list view with radio button
        adapter_parameter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_forecast.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mspinner_parameter.setAdapter(adapter_parameter);
        mspinner_forecast.setAdapter(adapter_forecast);


        mspinner_parameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("!--ParamItem Selected", "!--");
                parameter_id = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        mspinner_forecast.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

        {
            @Override
            public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){
                Log.d("!--FcstItem Selected", "!--");
                determineForecastType(position);
            }

            @Override
            public void onNothingSelected (AdapterView < ? > parent){

            }
        }

    );
*/
}
    public void determineForecastType(int position) {

        if (orientationChange) {
            Log.d("!--OrntChange,No doTask", "123");
            orientationChange = false;
        }
        else {
            Log.d("!--doTask", "123");
            switch (position) {

                case 0:
                    doTask("3-days");
                    break;
                case 1:
                    doTask("10-days");
                    break;
                case 2:
                    doTask("1-month");
                    break;
                case 3:
                    doTask("3-months");
                    break;
                default:
                    break;

            }
        }
    }


    public void doTask(String fcst) {

        switch (parameter_id) {

            case 0:
                Log.d("!--Precipiation"+fcst, "!!");
                wv_map.loadUrl("file:///android_asset/content.html");

                break;
            case 1:
                Log.d("!--Temp"+fcst, "!!");
                break;
            case 2:
                Log.d("!--Humidity"+fcst, "!!");
                break;
            case 3:
                Log.d("!--PET"+fcst, "!!");
                break;
            default:
                break;
        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("!--SaveInst", "123");
        outState.putBoolean("www.rimes.anint.orientationWeather", true);
    }
}
