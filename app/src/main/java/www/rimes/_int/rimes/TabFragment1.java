package www.rimes._int.rimes;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;


public class TabFragment1 extends Fragment implements View.OnClickListener {


    int forecast_param_id = 0; //Precipitation = 0, Temperature = 1, Humidity = 2, PET = 3

    int forecast_type_id = 0;   //3-days = 0, 10-days = 1, 1-month = 2, 3-months = 3

    ImageView imageView_map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab_fragment_1, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView txt_city_name = (TextView) view.findViewById(R.id.textView_cityname_frag1);

        if(MainActivity.getCity() != null) {
            txt_city_name.setText(MainActivity.getCity());
        }

        imageView_map = (ImageView) view.findViewById(R.id.imageView_map);
        PhotoViewAttacher mAttacher = new PhotoViewAttacher(imageView_map);

        Picasso.with(getActivity()).setIndicatorsEnabled(true);

        loadContent("3 days");

        ImageButton imageButton_showmap = (ImageButton) view.findViewById(R.id.imageButton_showmap);
        ImageButton imageButton_showgraph = (ImageButton) view.findViewById(R.id.imageButton_showgraph);

        imageButton_showmap.setOnClickListener(TabFragment1.this);
        imageButton_showgraph.setOnClickListener(TabFragment1.this);

        Spinner mspinner_forecast_type = (Spinner) view.findViewById(R.id.spinner_forecast_type);
        Spinner mspinner_forecast_parameter = (Spinner) view.findViewById(R.id.spinner_forecast_parameter);

        List<String> categories_forecast_parameter = new ArrayList<String>();
        categories_forecast_parameter.add("Precipitation");
        categories_forecast_parameter.add("Temperature");
        categories_forecast_parameter.add("Humidity");
        categories_forecast_parameter.add("PET");

        List<String> categories_forecast_type = new ArrayList<String>();
        categories_forecast_type.add("3 days");
        categories_forecast_type.add("10 days");
        categories_forecast_type.add("1 month");
        categories_forecast_type.add("03 months");

        // Creating adapters for spinner
        ArrayAdapter<String> adapter_forecast_parameter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories_forecast_parameter);
        ArrayAdapter<String> adapter_forecast_type = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories_forecast_type);

        // Drop down layout style - list view with radio button
        adapter_forecast_parameter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_forecast_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mspinner_forecast_parameter.setAdapter(adapter_forecast_parameter);
        mspinner_forecast_type.setAdapter(adapter_forecast_type);


        SpinnerTouchListener mlistener_forecast_parameter = new SpinnerTouchListener(false);
        SpinnerTouchListener mlistener_forecast_type = new SpinnerTouchListener(true);

        mspinner_forecast_parameter.setOnTouchListener(mlistener_forecast_parameter);
        mspinner_forecast_parameter.setOnItemSelectedListener(mlistener_forecast_parameter);

        mspinner_forecast_type.setOnTouchListener(mlistener_forecast_type);
        mspinner_forecast_type.setOnItemSelectedListener(mlistener_forecast_type);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.imageButton_showgraph) {


        } else if (v.getId() == R.id.imageButton_showmap) {

            determineForecastType(forecast_type_id);

        }
    }


    public class SpinnerTouchListener implements AdapterView.OnItemSelectedListener, View.OnTouchListener {

        private boolean spinner_touched = false;
        private boolean fetch_web_content;


        SpinnerTouchListener(Boolean param) {
            this.fetch_web_content = param;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            spinner_touched = true;
            return false;

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if (spinner_touched) {

                if (!fetch_web_content) {

                    forecast_param_id = position;

                } else {

                    forecast_type_id = position;
                }


            }

            spinner_touched = false;

        }
    }


    public void determineForecastType(int position) {

        Log.d("!--doTask", "123");
        switch (position) {

            case 0:
                loadContent("3-days");
                break;
            case 1:
                loadContent("10-days");
                break;
            case 2:
                loadContent("1-month");
                break;
            case 3:
                loadContent("3-months");
                break;
            default:
                break;

        }

    }


    public void loadContent(String fcst) {

        switch (forecast_param_id) {

            case 0:

                Picasso.with(getActivity())
                    .load("http://www.rimes.int/files/mobtest/ppt.png")
                    .placeholder(R.mipmap.ic_launcher)

                    .into(imageView_map);

                Log.d("!--Precipiation" + fcst, "!!");
                break;

            case 1:

                Picasso.with(getActivity())
                        .load("http://www.rimes.int/files/mobtest/tmp.png")
                        .placeholder(R.mipmap.ic_launcher)
                        //.centerInside()
                        .into(imageView_map);

                Log.d("!--Temp" + fcst, "!!");
                break;

            case 2:

                Picasso.with(getActivity())
                        .load("http://www.rimes.int/files/mobtest/humid.png")
                        .placeholder(R.mipmap.ic_launcher)

                        .into(imageView_map);
                Log.d("!--Humidity" + fcst, "!!");
                break;

            case 3:

                Picasso.with(getActivity())
                        .load("http://www.rimes.int/files/mobtest/pet.png")
                        .placeholder(R.mipmap.ic_launcher)
                        //.resize(MainActivity.getDeviceWidth(), MainActivity.getDeviceHeight())
                       // .onlyScaleDown()
                        .into(imageView_map);

                Log.d("!--PET" + fcst, "!!");
                break;

            default:
                break;
        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}

