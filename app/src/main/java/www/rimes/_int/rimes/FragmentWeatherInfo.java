package www.rimes._int.rimes;


import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import me.relex.circleindicator.CircleIndicator;


public class FragmentWeatherInfo extends Fragment {

    int id;

    Bundle b;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("--FW onCreate", "!!");

        b = getArguments();



        if (b.getInt("id") == 1) {

            RequestQueue queue = Volley.newRequestQueue(getActivity());
            String url = "http://www.rimes.int/files/mobnew/t2lonlat2.csv";




            JsonObjectRequest jsObjRequest_RAINNC = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            try {

                                Log.d("--SUCCESS", response.toString());

                            } catch (Exception e) {



                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("--FAILURE2", error.toString());
                        }
                    });

            queue.add(jsObjRequest_RAINNC);




            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.

                            Log.d("--SUCCESS", "!!");

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("--ERROR", String.valueOf(error));
                }
            });

            // Add the request to the RequestQueue.
            queue.add(stringRequest);
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("--FW onCreateView", "!!");
        return inflater.inflate(R.layout.fragment_weather_info, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("--FW onViewCreated", "!!");

        TextView txt_forecast = (TextView) view.findViewById(R.id.txt_type);
        WebView wv_map = (WebView) view.findViewById(R.id.weather_webView);
        wv_map.getSettings().setJavaScriptEnabled(true);

        txt_forecast.setText(b.getString("forecasttype"));
        wv_map.loadUrl("file:///android_asset/content.html");


    }

    public static FragmentWeatherInfo newInstance(Bundle myBundle) {

        FragmentWeatherInfo f = new FragmentWeatherInfo();
        f.setArguments(myBundle);
        return f;

    }


    @Override
    public void onDestroyView() {
        Log.d("--FW DestroyView", "!!");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("--FW Destroy", "!!");
        super.onDestroy();
    }


    @Override
    public void onDetach() {
        Log.d("--FW Detatch", "!!");
        super.onDetach();
    }
}
