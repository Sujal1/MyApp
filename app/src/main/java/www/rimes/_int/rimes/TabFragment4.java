package www.rimes._int.rimes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class TabFragment4 extends Fragment {

    private HashMap<Integer, String> forecast_hashmap = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("--TAB FRAGMENT 4", "TRUE");
        return inflater.inflate(R.layout.tab_fragment_4, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createForecastHashMap();

        TextView txt_advisory = (TextView) view.findViewById(R.id.textView_advisory);


        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String advisory = sharedPref.getString("www.rimes.anint.myviewpager.advisory", null);


        if(advisory != null) {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                txt_advisory.setText(Html.fromHtml(advisory, Html.FROM_HTML_MODE_LEGACY));
            } else {
                txt_advisory.setText(Html.fromHtml(advisory));
            }

        } else {

            txt_advisory.setText("NA, Please refresh!");
        }


        Set<String> crop_set = sharedPref.getStringSet("www.rimes.anint.myviewpager.crops", Collections.<String>emptySet());
        String sowing_list = sharedPref.getString("www.rimes.anint.myviewpager.sowing_types", null);
        String forecast_list = sharedPref.getString("www.rimes.anint.myviewpager.reqd_forecasts", null);

        if (sowing_list != null && forecast_list != null) {

            String[] sowing_array = sowing_list.split("|");

            String[] forecast_array = forecast_list.split("|");

            TableLayout table = (TableLayout)view.findViewById(R.id.tablelayout);

            int count = crop_set.size();

            for (String c : crop_set)
            {
                // Inflate your row "template" and fill out the fields.
                TableRow row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.test, null);


                row.setBackgroundResource(R.drawable.table_border);
                row.setPadding(0, 15, 0, 15); //LEFT, TOP, RIGHT, BOTTOM

                ((TextView)row.findViewById(R.id.textView_crop)).setText(c.toUpperCase());
                ((TextView)row.findViewById(R.id.textView_sowing_type)).setText(sowing_array[count]);
                ((TextView)row.findViewById(R.id.textView_reqd_fcst)).setText(forecast_array[count]);

                table.addView(row);

                count = count - 1;
            }

            table.requestLayout();

        }

    }


    private void createForecastHashMap() {

        forecast_hashmap.put(1, "Rainfall Forecast Required");
        forecast_hashmap.put(2, "Temperature Max Forecast Required");
        forecast_hashmap.put(3, "Temperature Min Forecast Required");
        forecast_hashmap.put(4, "Windfall Forecast Required");
        forecast_hashmap.put(5, "Fog Warning Required");
        forecast_hashmap.put(6, "Hailstorm Warning Required");
        forecast_hashmap.put(7, "Thunderstorm Warning Required");

    }

}


