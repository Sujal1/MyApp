package www.rimes._int.rimes;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DataBaseOperation extends SQLiteOpenHelper{


        //The Android's default system path of your application database.
        private static String DB_PATH = "/data/data/www.rimes.anint.myviewpager/databases/";

        private static String DB_NAME = "sesame.db";

        private SQLiteDatabase myDataBase;

       // private Context myContext;

        /**
         * Constructor
         * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
         * @param context
         */
        public DataBaseOperation(Context context) {

            super(context, DB_NAME, null, 1);
           // this.myContext = context;
        }


        public void openDataBase() throws SQLException {

            //Open the database
            String myPath = DB_PATH + DB_NAME;
            myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

        }


        public String[] getLatLon(int city_id) {

            String [] latlon = null;

            Cursor mcursor = myDataBase.rawQuery("SELECT * FROM city WHERE __id=" + city_id, null);

            if (mcursor != null && mcursor.moveToFirst() ) {

                    latlon = new String[2];
                    latlon [0]= String.valueOf(mcursor.getDouble(mcursor.getColumnIndex("lat")));
                    latlon[1] = String.valueOf(mcursor.getDouble(mcursor.getColumnIndex("lon")));

            }

            myDataBase.close();

            return latlon;

        }


        public String[] getDekad(int city_id) {

            String [] dekad_info = null;

            Cursor mcursor = myDataBase.rawQuery("SELECT * FROM dekad WHERE city_id=" + city_id, null);

            if (mcursor != null && mcursor.moveToFirst() ) {

                dekad_info = new String[2];
                dekad_info [0]= mcursor.getString(mcursor.getColumnIndex("dekad_date"));
                dekad_info[1] = mcursor.getString(mcursor.getColumnIndex("advisory"));

            }

            myDataBase.close();

            return dekad_info;

        }


        public void writeDekadInfo (int city_id, String dekad_date, String advisory) {

            String sql = "INSERT INTO dekad (city_id, dekad_date, advisory) VALUES(" + city_id + ",'" + dekad_date + "','" + advisory + "')" ;
            myDataBase.execSQL(sql);
            myDataBase.close();
        }



        public String fetchAdvisory (int city_id) {

            Cursor mcursor = myDataBase.rawQuery("SELECT advisory FROM dekad WHERE city_id=" + city_id, null);

            String  advisory = null;

            if (mcursor != null && mcursor.moveToFirst() ) {

                advisory = mcursor.getString(mcursor.getColumnIndex("advisory"));
            }

            myDataBase.close();

            return advisory;
        }


        public void clearAll() {

            myDataBase.execSQL("DELETE FROM dekad");

            myDataBase.close();
        }

        @Override
        public synchronized void close() {

            if(myDataBase != null)
                myDataBase.close();

            super.close();

        }


        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }


 }


