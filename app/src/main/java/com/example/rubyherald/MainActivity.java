package com.example.rubyherald;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.sqlite.SQLiteStatement;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity
{

    private static int SPLASH_SCREEN_TIME_OUT=10000;
    static String arrayOfJsons[]=new String[7];
    int ctr=0;
    InputStream inputStream;
    InputStreamReader inputStreamReader;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
               WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        String urlToDownload="http://newsapi.org/v2/top-headlines?country=in&apiKey=d0ed4b2e92604b8d85b2740d29015468";
        DownloadTask task=new DownloadTask();
        try
        {
            task.execute(urlToDownload);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        Log.i("Trial ", "Deepnika");

        for(int i=0;i<arrayOfJsons.length;i++)
        {
            Log.i("arrayOfJsons", Arrays.toString(arrayOfJsons));
        }

        Log.i("Trial ", "Shyam");


        /*Intent i=new Intent(MainActivity.this, Home.class);
        i.putExtra("articleDetails", arrayOfJsons);
        startActivity(i);
        finish();*/



        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                /*String urlToDownload="http://newsapi.org/v2/top-headlines?country=in&apiKey=d0ed4b2e92604b8d85b2740d29015468";
                DownloadTask task=new DownloadTask();
                try
                {
                    task.execute(urlToDownload);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


                Log.i("Trial ", "Deepnika");

                for(int i=0;i<arrayOfJsons.length;i++)
                {
                    Log.i("arrayOfJsons", Arrays.toString(arrayOfJsons));
                }

                Log.i("Trial ", "Shyam");


                Intent i=new Intent(MainActivity.this, Home.class);
                i.putExtra("articleDetails", arrayOfJsons);
                startActivity(i);
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);*/



    }



    public class DownloadTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... urls)
        {
            String  result="";
            URL url;
            HttpURLConnection httpURLConnection=null;

            try
            {
                Log.i("Trial ", "Nikhil");

                url=new URL(urls[0]);
                httpURLConnection=(HttpURLConnection)url.openConnection();
                inputStream=httpURLConnection.getInputStream();
                inputStreamReader=new InputStreamReader(inputStream);
                int data=inputStreamReader.read();

                while (data!=-1)
                {
                    char current=(char)data;
                    result+=current;
                    data=inputStreamReader.read();
                }

                arrayOfJsons[0]=result;
                Log.i("Result 0", result);


                result="";
                url=new URL("http://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=d0ed4b2e92604b8d85b2740d29015468");
                httpURLConnection=(HttpURLConnection)url.openConnection();
                inputStream=httpURLConnection.getInputStream();
                inputStreamReader=new InputStreamReader(inputStream);
                data=inputStreamReader.read();


                while (data!=-1)
                {
                    char current=(char)data;
                    result+=current;
                    data=inputStreamReader.read();
                }

                arrayOfJsons[1]=result;
                Log.i("Result 1", result);




                result="";
                url=new URL("http://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=d0ed4b2e92604b8d85b2740d29015468");
                httpURLConnection=(HttpURLConnection)url.openConnection();
                inputStream=httpURLConnection.getInputStream();
                inputStreamReader=new InputStreamReader(inputStream);
                data=inputStreamReader.read();

                while (data!=-1)
                {
                    char current=(char)data;
                    result+=current;
                    data=inputStreamReader.read();
                }

                arrayOfJsons[2]=result;
                Log.i("Result 2", result);






                result="";
                url=new URL("http://newsapi.org/v2/top-headlines?country=in&category=science&apiKey=d0ed4b2e92604b8d85b2740d29015468");
                httpURLConnection=(HttpURLConnection)url.openConnection();
                inputStream=httpURLConnection.getInputStream();
                inputStreamReader=new InputStreamReader(inputStream);
                data=inputStreamReader.read();

                while (data!=-1)
                {
                    char current=(char)data;
                    result+=current;
                    data=inputStreamReader.read();
                }

                arrayOfJsons[3]=result;
                Log.i("Result 3", result);





                result="";
                url=new URL("http://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=d0ed4b2e92604b8d85b2740d29015468");
                httpURLConnection=(HttpURLConnection)url.openConnection();
                inputStream=httpURLConnection.getInputStream();
                inputStreamReader=new InputStreamReader(inputStream);
                data=inputStreamReader.read();

                while (data!=-1)
                {
                    char current=(char)data;
                    result+=current;
                    data=inputStreamReader.read();
                }

                arrayOfJsons[4]=result;
                Log.i("Result 4", result);





                result="";
                url=new URL("http://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=d0ed4b2e92604b8d85b2740d29015468");
                httpURLConnection=(HttpURLConnection)url.openConnection();
                inputStream=httpURLConnection.getInputStream();
                inputStreamReader=new InputStreamReader(inputStream);
                data=inputStreamReader.read();

                while (data!=-1)
                {
                    char current=(char)data;
                    result+=current;
                    data=inputStreamReader.read();
                }

                arrayOfJsons[5]=result;
                Log.i("Result 5", result);




                result="";
                url=new URL("http://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=d0ed4b2e92604b8d85b2740d29015468");
                httpURLConnection=(HttpURLConnection)url.openConnection();
                inputStream=httpURLConnection.getInputStream();
                inputStreamReader=new InputStreamReader(inputStream);
                data=inputStreamReader.read();

                while (data!=-1)
                {
                    char current=(char)data;
                    result+=current;
                    data=inputStreamReader.read();
                }

                arrayOfJsons[6]=result;
                Log.i("Result 6", result);


                Log.i("Test ", "Rahul");






                return result;

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }



        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);

            Log.i("Test ", "Ashwin");

            Intent i=new Intent(MainActivity.this, Home.class);
            i.putExtra("articleDetails", arrayOfJsons);
            startActivity(i);
            finish();
        }



    }


}
