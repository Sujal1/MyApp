/*
package www.rimes.anint.myviewpager;

import android.app.IntentService;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import static android.R.attr.password;

*/
/**
 * Created by seasonal on 9/26/2016.
 *//*

public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS

  */
/*  SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";*//*


    public static final String PARAM_OUT_MSG_1 = "bundle1";

    double my_lat, my_lng;

    public MyIntentService() {
        super("MyIntentService");
    }




    @Override
    protected void onHandleIntent(Intent intent) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        RestHttpUrlConnection request = new RestHttpUrlConnection(new URL(restUrl), headers, username, password);
        return request.doGetRequest();


        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet("http://download.finance.yahoo.com/d/quotes.csv?s=msft&f=sl1p2");
        HttpResponse response = httpClient.execute(httpGet, localContext);
        String result = "";

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        response.getEntity().getContent()
                )
        );

            */
/*----------Extracting Humidity-------------------*//*

            */
/*JsonObjectRequest jsObjRequest_PSFC = new JsonObjectRequest
                    (Request.Method.GET, url_PSFC, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                int num_days = 10;

                                JSONObject jObject1 = response.getJSONObject("table");

                                JSONArray jArray1 = jObject1.getJSONArray("rows");

                                for (int i = 0; i < num_days*4+3; i++ ) {

                                    JSONArray jArray2 = jArray1.getJSONArray(i);

                                    //Log.d("!!Array"+String.valueOf(i), jArray2.toString());

                                    Log.d("---!!ArrayPSFC"+String.valueOf(i), String.valueOf(jArray2.get(jArray2.length()-1)));
                                    // String myArray[] = jArray2.toString().split(",");

                                    humidity[i] = (Double) jArray2.get(jArray2.length()-1);
                                }

                                for (int i = 0; i < humidity.length; i++) {

                                    humidity[i] = humidity[i]/1300;
                                }


                                gb.setPSFC(humidity);

                                Intent broadcastIntent = new Intent();
                                broadcastIntent.setAction(MainActivity.ResponseReceiver.ACTION_RESP);
                                broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
                                broadcastIntent.putExtra(PARAM_OUT_MSG_1, 3);

                                sendBroadcast(broadcastIntent);

                            } catch (Exception e) {

                                // Log.d("!!!", e.toString());
                                Toast.makeText(MyIntentService.this, "Failed to load data", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Log.d("!!!", "FAILURE4" + error.toString());
                        }
                    });

            queue.add(jsObjRequest_PSFC);
*//*



    }
}
*/
