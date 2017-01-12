package www.rimes._int.rimes;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class TabFragment1Copy2 extends Fragment {


    int parameter_id;


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


     /*   Picasso.with(getActivity()).setIndicatorsEnabled(true);

        Picasso.with(getActivity())
                .load("http://www.rimes.int/files/mobtest/last_image.png")
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .into(iv_ion);
    */

        /*PhotoViewAttacher mAttacher = new PhotoViewAttacher(iv_ion);*/


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


       /* mspinner_forecast_parameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("!--ParamItem Selected", "!--");
                parameter_id = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mspinner_forecast_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("!--FcstItem Selected", "!--");
                determineForecastType(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/
    }

    int param_id;

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

                    param_id = position;

                } else {

                    determineForecastType(position);
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

        switch (param_id) {

            case 0:
                Log.d("!--Precipiation"+fcst, "!!");
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

    }
}

