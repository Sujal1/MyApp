package www.rimes._int.rimes;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity  {

    private static int device_height, device_width;

    private  String city;


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

       // calculateDimensions();

        if (isCitySet()) {

            setMainContent();

        } else {

            setContentView(R.layout.activity_main_info);

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.app_banner);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


    }


    private void setMainContent() {

        Toast.makeText(this, city + "(From Main)", Toast.LENGTH_LONG).show();

        setContentView(R.layout.activity_main);

        PagerTabStrip pgStrip = (PagerTabStrip) findViewById(R.id.pager_title_strip);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(1);

        final PagerAdapterMain adapter = new PagerAdapterMain(getSupportFragmentManager(), 4);
        viewPager.setAdapter(adapter);

    }


    private boolean isCitySet() {

        SharedPreferences sharedPref = this.getSharedPreferences("city", Context.MODE_PRIVATE);

        city = sharedPref.getString("www.rimes._int.sesame.city", null);


        if (city == null) {

            return false;
        }

        return true;
    }


    private void calculateDimensions() {

        WindowManager wm = (WindowManager)    MainActivity.this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        device_height = display.getHeight();
        device_width = display.getWidth();

    }


    public static int getDeviceHeight() {
        return  device_height;
    }

    public static int getDeviceWidth() {
        return  device_width;
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        android.support.v7.widget.SearchView searchView =
                (android.support.v7.widget.SearchView) menu.findItem(R.id.action_search).getActionView();


        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;

    }


    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(MainActivity.this, query + "(New Intent)", Toast.LENGTH_LONG).show();

            final String query_city = getIntent().getStringExtra(SearchManager.QUERY);

            String url = "http://maps.google.com/maps/api/geocode/json?address=" + query_city;

            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            try {


                                double lng = response.getJSONArray("results").getJSONObject(0)
                                        .getJSONObject("geometry").getJSONObject("location")
                                        .getDouble("lng");

                                double lat = response.getJSONArray("results").getJSONObject(0)
                                        .getJSONObject("geometry").getJSONObject("location")
                                        .getDouble("lat");


                                SharedPreferences sharedPref = getSharedPreferences("city", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("www.rimes._int.sesame.city", query_city);
                                editor.commit();

                                Toast.makeText(MainActivity.this, String.valueOf(lat), Toast.LENGTH_LONG).show();
                                Toast.makeText(MainActivity.this, String.valueOf(lng), Toast.LENGTH_LONG).show();

                                city = query_city;

                                setMainContent();

                            } catch (Exception e) {

                                Toast.makeText(MainActivity.this, "Could not find the city!!", Toast.LENGTH_LONG).show();
                                finish();

                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(MainActivity.this, "Could not find the city!!", Toast.LENGTH_LONG).show();
                            finish();

                        }
                    });

            queue.add(jsObjRequest);
        }
    }
}