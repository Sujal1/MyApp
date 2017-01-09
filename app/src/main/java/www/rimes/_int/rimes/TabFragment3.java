package www.rimes._int.rimes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.senab.photoview.PhotoViewAttacher;


public class TabFragment3 extends Fragment implements View.OnClickListener {


    private int day, start_day, end_day, param_id;

    private static WebView webview_crop = null;

    private static String dekad_url = "http://agro-api.rimes.int/rest_server/dekads";

    private static String advisory_url = "http://agro-api.rimes.int/rest_server/advisorys/id/1";

    private static String crop_calendar_url = "http://agro-api.rimes.int/rest_server/cropcalendars";

    private static String start_date, end_date, month, advisory;

    private static String param = "raint";

    ImageView imageView_colorbar;

    TextView textView_day;

    private static ViewGroup myParentViewGroup = null;


    Spinner mspinner_crop_parameter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab_fragment_3, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView_day = (TextView) view.findViewById(R.id.textView_day);

        mspinner_crop_parameter = (Spinner) view.findViewById(R.id.spinner_crop_params);

        imageView_colorbar = (ImageView) view.findViewById(R.id.imageView_colorbar);

        PhotoViewAttacher mAttacher = new PhotoViewAttacher(imageView_colorbar);

        if (webview_crop == null || savedInstanceState == null) {

            myParentViewGroup = (ViewGroup) view.findViewById(R.id.tabfragment3_frame_layout);

            webview_crop = (WebView) myParentViewGroup.findViewById(R.id.webviewCrop);

            getDekad();

            param_id = 0;

            param = "raint";

            webview_crop.loadUrl("file:///android_asset/crop_wv_content.html");

            setupSpinner(mspinner_crop_parameter, param_id);

        } else {

            day = savedInstanceState.getInt("www.rimes._int.rimes.day");

            month = savedInstanceState.getString("www.rimes._int.rimes.month");

            param_id = savedInstanceState.getInt("www.rimes._int.rimes.param_id");

            switch (param_id) {
                case 0:
                    param = "raint";
                    imageView_colorbar.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.raintcolor));
                    break;
                case 1:
                    param = "tempt";
                    imageView_colorbar.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.temptcolor_hortz));
                    break;
                case 2:
                    param = "pet";
                    imageView_colorbar.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.petcolor_hortz));
                    break;

            }

            int index = myParentViewGroup.indexOfChild(webview_crop);

            myParentViewGroup.removeView(webview_crop);

            myParentViewGroup = (ViewGroup) view.findViewById(R.id.tabfragment3_frame_layout);

            myParentViewGroup.removeView(myParentViewGroup.findViewById(R.id.webviewCrop));

            myParentViewGroup.addView(webview_crop, index);


            textView_day.setText(month + " " + day);
            setupSpinner(mspinner_crop_parameter, param_id);

        }


        JavaScriptInterface JSInterface = new JavaScriptInterface(getActivity());

        webview_crop.getSettings().setJavaScriptEnabled(true);
        webview_crop.addJavascriptInterface(JSInterface, "JSInterface");

        ImageButton imgBtn_left = (ImageButton) view.findViewById(R.id.imgBtn_left);
        ImageButton imgBtn_right = (ImageButton) view.findViewById(R.id.imgBtn_right);

        imgBtn_left.setOnClickListener(TabFragment3.this);
        imgBtn_right.setOnClickListener(TabFragment3.this);

    }



    private void setupSpinner(Spinner mspinner_crop_parameter, int param_id) {

        List<String> parameters = new ArrayList<String>();
        parameters.add("Precipitation (mm)");
        String DEGREE  = "\u00b0";
        parameters.add("Temperature (" + DEGREE + " " + "C)");
        parameters.add("PET (mm)");


        // Creating adapters for spinner
        ArrayAdapter<String> adapter_parameter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, parameters);

        // Drop down layout style - list view with radio button
        adapter_parameter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attaching data adapter to spinner
        mspinner_crop_parameter.setAdapter(adapter_parameter);

        mspinner_crop_parameter.setSelection(param_id);

        SpinnerTouchListener mlistener = new SpinnerTouchListener();

        mspinner_crop_parameter.setOnTouchListener(mlistener);
        mspinner_crop_parameter.setOnItemSelectedListener(mlistener);

    }


    public class SpinnerTouchListener implements AdapterView.OnItemSelectedListener, View.OnTouchListener {

        boolean spinner_touched = false;

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            spinner_touched = true;
            return false;

        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if (spinner_touched) {

                switch (position) {

                    case 0:
                        param_id = 0;
                        param = "raint";
                        webview_crop.loadUrl("file:///android_asset/crop_wv_content.html");
                        imageView_colorbar.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.raintcolor));
                        //day = start_day;
                        textView_day.setText(month + " " + day);
                        break;

                    case 1:
                        param_id = 1;
                        param = "tempt";
                        webview_crop.loadUrl("file:///android_asset/crop_wv_content.html");
                        imageView_colorbar.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.temptcolor_hortz));
                        //day = start_day;
                        textView_day.setText(month + " " + day);
                        break;

                    case 2:
                        param_id = 2;
                        param = "pet";
                        webview_crop.loadUrl("file:///android_asset/crop_wv_content.html");
                        imageView_colorbar.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.petcolor_hortz));
                        //day = start_day;
                        textView_day.setText(month + " " + day);
                        break;

                }
            }

            spinner_touched = false;

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }




    private void getDekad() {

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest dekadObjRequest = new JsonObjectRequest
                (Request.Method.GET, dekad_url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {


                            start_date = response.getString("start_date");

                            end_date = response.getString("end_date");

                            month = response.getString("month");

                           // year = response.getString("year");


                            start_day = Integer.parseInt(start_date);
                            end_day = Integer.parseInt(end_date);

                            day = start_day;

                            textView_day.setText(month + " " + day);

                            if (isNewDekad()) {

                                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("www.rimes.anint.myviewpager.start_date", start_date);
                                editor.putString("www.rimes.anint.myviewpager.month", month);

                                editor.commit();

                                getAdvisory();

                                getCropCalendar();

                            }

                        } catch (Exception e) {

                            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


        queue.add(dekadObjRequest);

    }



    private boolean isNewDekad() {

        //Check the month and the start date

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        String date_stored = sharedPref.getString("www.rimes.anint.myviewpager.start_date", "#$%");
        String month_stored = sharedPref.getString("www.rimes.anint.myviewpager.month", "#$%");

        if (date_stored == start_date && month_stored == month) {
            return false;
        }

        return true;
    }


    private void getAdvisory() {

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest advisoryObjRequest = new JsonObjectRequest
                (Request.Method.GET, advisory_url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            advisory = response.getString("advisory");

                            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("www.rimes.anint.myviewpager.advisory", advisory);
                            editor.commit();


                        } catch (Exception e) {

                            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


        queue.add(advisoryObjRequest);

    }


    private void getCropCalendar() {

        final Set <String> crops = new HashSet<String>();

        final StringBuilder sowing_string_builder = new StringBuilder();
        final StringBuilder forecast_string_builder = new StringBuilder();


        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonArrayRequest cropCalObjRequest = new JsonArrayRequest
                (Request.Method.GET, crop_calendar_url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            for (int i = 0; i < response.length(); i++) {

                                JSONObject calendar_array = response.getJSONObject(i);

                                crops.add(calendar_array.getString("crop_name"));

                                sowing_string_builder.append(calendar_array.getString("sowing_type")).append("|");

                                forecast_string_builder.append(calendar_array.getString("required_forecast")).append("|");

                            }

                            /*Toast.makeText(getActivity(), String.valueOf(sowing_string_builder), Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(), String.valueOf(forecast_string_builder), Toast.LENGTH_LONG).show();*/

                            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putStringSet("www.rimes.anint.myviewpager.crops", crops);
                            editor.putString("www.rimes.anint.myviewpager.sowing_types", String.valueOf(sowing_string_builder));
                            editor.putString("www.rimes.anint.myviewpager.reqd_forecasts", String.valueOf(forecast_string_builder));
                            editor.commit();



                        } catch (Exception e) {

                            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


        queue.add(cropCalObjRequest);

    }


    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.imgBtn_left) {

            if (day == start_day) {

                Toast.makeText(getActivity(), "First Day!", Toast.LENGTH_LONG).show();

            } else {

                day = day - 1;

                textView_day.setText(month + " " + day);

                webview_crop.loadUrl("file:///android_asset/crop_wv_content.html");

            }


        }

        else if (v.getId() == R.id.imgBtn_right) {

            if (day == end_day) {

                Toast.makeText(getActivity(), "Last Day!", Toast.LENGTH_LONG).show();

            } else {

                day = day + 1;

                textView_day.setText(month + " " + day);

                webview_crop.loadUrl("file:///android_asset/crop_wv_content.html");

            }

        }

    }



    public class JavaScriptInterface {

        Context mContext;

        /** Instantiate the interface and set the context */
        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public String getURL()

        {   return "http://agro.rimes.int/DATA/KML/" + param + "_" + String.valueOf(day-start_day+2) + ".png";  }


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d("!-SAVED", "123");

        outState.putInt("www.rimes._int.rimes.day", day);
        outState.putString("www.rimes._int.rimes.month", month);
        outState.putInt("www.rimes._int.rimes.param_id", param_id);

    }
}
