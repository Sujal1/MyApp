package www.rimes._int.rimes;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;

import static android.support.v4.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER;


public class MainActivity extends AppCompatActivity {

    private static int device_height, device_width;

    private static  String city_name, city_lat, city_lon;

    private static int city_id;

    private DataBaseHelper myDbHelper;


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // calculateDimensions();

        if (! setUpDatabase()) {

            Toast.makeText(this, "Error setting up database!", Toast.LENGTH_LONG).show();

        } else {

            if (isCitySet()) {

                setMainContent();

            } else {

                setContentView(R.layout.activity_main_info);
                setToolbar();

            }
        }
    }


    private boolean setUpDatabase() {

        myDbHelper = new DataBaseHelper(this);

        try {

            myDbHelper.createDataBase();
            return true;

        } catch (IOException ioe) {

            return false;
        }
    }


    private void setMainContent() {

        setContentView(R.layout.activity_main);

        PagerTabStrip pgStrip = (PagerTabStrip) findViewById(R.id.pager_title_strip);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(1);

        final PagerAdapterMain adapter = new PagerAdapterMain(getSupportFragmentManager(), 4);
        viewPager.setAdapter(adapter);

        setToolbar();
    }


    private void setToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.app_banner);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private boolean isCitySet() {

        SharedPreferences sharedPref = this.getSharedPreferences("www.rimes._int.sesame.CITY", Context.MODE_PRIVATE);

        city_id = sharedPref.getInt("www.rimes._int.sesame.CITY.city_id", 0);

        if (city_id == 0) {

            return false; //city does not exist in our shared preference
        }

        city_name = sharedPref.getString("www.rimes._int.sesame.CITY.city", null);

        city_lat = sharedPref.getString("www.rimes._int.sesame.CITY.city_lat", null);

        city_lon = sharedPref.getString("www.rimes._int.sesame.CITY.city_lon", null);

        return true;
    }


    private void calculateDimensions() {

        WindowManager wm = (WindowManager) MainActivity.this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        device_height = display.getHeight();
        device_width = display.getWidth();

    }


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        final SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        String[] columnNames = {"_id", "text"};

        MatrixCursor cursor = new MatrixCursor(columnNames);
        String[] array = getResources().getStringArray(R.array.cities);

        String[] temp = new String[2];

        int id = 0;

        for (String item : array) {
            temp[0] = Integer.toString(id++);
            temp[1] = item;
            cursor.addRow(temp);
        }

        String[] from = {"text"};
        int[] to = {R.id.textView_search};
        final SimpleCursorAdapter busStopCursorAdapter = new SimpleCursorAdapter(MainActivity.this, R.layout.search_suggestion, cursor, from, to, FLAG_REGISTER_CONTENT_OBSERVER);


        searchView.setSuggestionsAdapter(busStopCursorAdapter);

        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {

                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_LONG).show();

                Cursor cursor = (Cursor) searchView.getSuggestionsAdapter().getItem(position);

                String query_city = cursor.getString(1);

                searchView.clearFocus();

                city_id = position + 1;

                searchView.setQuery(query_city, true);

              //  searchView.clearFocus();

                return true; //indicates that the submit request has been handled, default false.

            }


        });

        return true;

    }


    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            String query_city = intent.getStringExtra(SearchManager.QUERY);

            Toast.makeText(MainActivity.this, query_city + "(New Intent)", Toast.LENGTH_LONG).show();

            DataBaseOperation db_operation = new DataBaseOperation(this);
            db_operation.openDataBase();

            String[] latlon = db_operation.getLatLon(city_id);

            if (latlon == null) {

                city_id = 0;

                Toast.makeText(MainActivity.this, "Entered city not available in database!", Toast.LENGTH_LONG).show();

            } else {

                SharedPreferences sharedPref = getSharedPreferences("www.rimes._int.sesame.CITY", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("www.rimes._int.sesame.CITY.city_id", city_id);
                editor.putString("www.rimes._int.sesame.CITY.city", query_city);
                editor.putString("www.rimes._int.sesame.CITY.lat", latlon[0]);
                editor.putString("www.rimes._int.sesame.CITY.lon", latlon[1]);
                editor.commit();

                city_name = query_city;
                city_lat = latlon[0];
                city_lon = latlon[1];

                setMainContent();
            }

        }
    }

    public static int getCityId() {
        return city_id;
    }


    public static String getCity() {
        return city_name;
    }

    public static String getLat() {
        return city_lat;
    }

    public static String getLon() {
        return city_lon;
    }



    public static int getDeviceHeight() {
        return device_height;
    }

    public static int getDeviceWidth() {
        return device_width;
    }

    @Override
    public void onStop() {
        super.onStop();

    }
}