package com.example.rubyherald;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

public class SavedArticles extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mToggle;
    AppBarConfiguration mAppBarConfiguration;
    NavigationView navigationView;


    CustomAdapter customAdapter;
    ListView listView;

    String articleDetails[];
    String author, title, description, imageUrl, publishedAt, content, url;

    SQLiteDatabase savedDB;
    int numberOfEntriesInTable;

    ArrayList<String>savedAuthor=new ArrayList<>();
    ArrayList<String>savedTitle=new ArrayList<>();
    ArrayList<String>savedDescription=new ArrayList<>();
    ArrayList<String>savedImageUrl=new ArrayList<>();
    ArrayList<String>savedPublishedAt=new ArrayList<>();
    ArrayList<String>savedContent=new ArrayList<>();
    ArrayList<String>savedUrl=new ArrayList<>();

    static  ArrayList<String>notes=new ArrayList<>();
    SharedPreferences sharedPreferences;
    ArrayAdapter arrayAdapter;
    HashSet<String>hashTitle=new HashSet<>();
    ArrayList<String>savedArticleDetails69;
    SharedPreferences prefs;
    Gson gson;
    String json;
    Type type;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_articles);

        Intent intent=getIntent();


        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationView);
        mToggle=new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);

        listView=(ListView) findViewById(R.id.listView);
        customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);

        /*if(savedArticleDetails69==null)
        {
            savedArticleDetails69=new ArrayList<String>();
        }*/



        /*SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        gson = new Gson();
        json = prefs.getString("messi", null);
        type = new TypeToken<ArrayList<String>>() {}.getType();
        savedArticleDetails69= gson.fromJson(json, type);*/



        /*Log.i("savedDetails69.size() ",Integer.toString(savedArticleDetails69.size()/6));
        int ctr=0;

        for(int i=0;i<(savedArticleDetails69.size())/6;i++)
        {
            //Log.i("savedArticle.get(0)", savedArticleDetails69.get(ctr+0));
            savedAuthor.add(savedArticleDetails69.get(ctr+0));
            Log.i("savedArticle.get(1)", savedArticleDetails69.get(ctr+1));
            savedTitle.add(savedArticleDetails69.get(ctr+1));
            //Log.i("savedArticle.get(2)", savedArticleDetails69.get(ctr+2));
            savedDescription.add(savedArticleDetails69.get(ctr+2));
            //Log.i("savedArticle.get(3)", savedArticleDetails69.get(ctr+3));
            savedImageUrl.add(savedArticleDetails69.get(ctr+3));
            //Log.i("savedArticle.get(4)", savedArticleDetails69.get(ctr+4));
            savedPublishedAt.add(savedArticleDetails69.get(ctr+4));
            //Log.i("savedArticle.get(5)", savedArticleDetails69.get(ctr+5));
            savedContent.add(savedArticleDetails69.get(ctr+5));

            ctr=ctr+6;
        }*/



        Log.i("savedDetails.size() ",Integer.toString(DetailedNews.savedArticleDetails.size()/6));
        int ctr=0;

        for(int i=0;i<(DetailedNews.savedArticleDetails.size())/7;i++)
        {
            //Log.i("savedArticle.get(0)", savedArticleDetails69.get(ctr+0));
            savedAuthor.add(DetailedNews.savedArticleDetails.get(ctr+0));
            //Log.i("savedArticle.get(1)", DetailedNews.savedArticleDetails.get(ctr+1));
            savedTitle.add(DetailedNews.savedArticleDetails.get(ctr+1));
            //Log.i("savedArticle.get(2)", savedArticleDetails69.get(ctr+2));
            savedDescription.add(DetailedNews.savedArticleDetails.get(ctr+2));
            //Log.i("savedArticle.get(3)", savedArticleDetails69.get(ctr+3));
            savedImageUrl.add(DetailedNews.savedArticleDetails.get(ctr+3));
            //Log.i("savedArticle.get(4)", savedArticleDetails69.get(ctr+4));
            savedPublishedAt.add(DetailedNews.savedArticleDetails.get(ctr+4));
            //Log.i("savedArticle.get(5)", savedArticleDetails69.get(ctr+5));
            savedContent.add(DetailedNews.savedArticleDetails.get(ctr+5));
            //Log.i("savedArticle.get(6)", savedArticleDetails69.get(ctr+6));
            savedContent.add(DetailedNews.savedArticleDetails.get(ctr+6));

            ctr=ctr+7;
        }




        //Log.i("savedTitle.size()", Integer.toString(savedTitle.size()));


        //arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, savedTitle);
        //listView.setAdapter(arrayAdapter);

        customAdapter.notifyDataSetChanged();


        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(), DetailedNews.class);
                intent.putExtra("articleDetails", new String[]{savedArticleDetails69.get(position*6+0), savedArticleDetails69.get(position*6+1), savedArticleDetails69.get(position*6+2), savedArticleDetails69.get(position*6+3), savedArticleDetails69.get(position*6+4), savedArticleDetails69.get(position*6+5)});
                startActivity(intent);
            }
        });*/


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(), DetailedNews.class);
                intent.putExtra("articleDetails", new String[]{DetailedNews.savedArticleDetails.get(position*7+0), DetailedNews.savedArticleDetails.get(position*7+1), DetailedNews.savedArticleDetails.get(position*7+2), DetailedNews.savedArticleDetails.get(position*7+3), DetailedNews.savedArticleDetails.get(position*7+4), DetailedNews.savedArticleDetails.get(position*7+5), DetailedNews.savedArticleDetails.get(position*7+6)});
                startActivity(intent);
            }
        });


    }










    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.navigation_drawer_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.home:
                Intent i = new Intent(SavedArticles.this, Home.class);
                startActivity(i);
                break;
            case R.id.saved:
                Intent i1 = new Intent(SavedArticles.this, SavedArticles.class);
                startActivity(i1);
                break;

        }
        return true;
    }



    class CustomAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            Log.i("numberOfEntriesInTable", Integer.toString(savedAuthor.size()));
            return (savedAuthor.size());
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=getLayoutInflater().inflate(R.layout.custom_layout,null);
            ImageView snapshot=(ImageView) convertView.findViewById(R.id.snapshot);
            TextView titleTextView= (TextView) convertView.findViewById(R.id.titleTextView);
            TextView authorTextView= (TextView) convertView.findViewById(R.id.authorTextView);

            Log.i("Test ", "Dwijesh");

            //Log.i("Test ", titles.get(0));

            if(savedImageUrl.size()>0 && savedImageUrl.get(position)!=null)
            {
                Log.i("Test ", savedImageUrl.get(position));
                String imageUrlAddress=savedImageUrl.get(position);
                ImageDownloader task=new ImageDownloader();
                try
                {
                    Bitmap myImage = task.execute(imageUrlAddress).get();
                    snapshot.setImageBitmap(myImage);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }


            if(savedTitle.size()>0 && savedTitle.get(position)!=null)
            {
                Log.i("Test ", savedTitle.get(position));
                titleTextView.setText(savedTitle.get(position));
            }
            if(savedAuthor.size()>0 && savedAuthor.get(position)!=null)
            {
                Log.i("Test ", savedAuthor.get(position));
                authorTextView.setText(savedAuthor.get(position));
            }

            return convertView;
        }

        public void remove(int position)
        {
            savedImageUrl.remove(savedImageUrl.get(position));
            savedTitle.remove(savedTitle.get(position));
            savedAuthor.remove(savedAuthor.get(position));
        }

    }




    public class ImageDownloader extends AsyncTask<String,Void, Bitmap>         //nested class
    {
        @Override
        protected Bitmap doInBackground(String... urls)
        {
            try {
                URL url=new URL(urls[0]);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.connect();
                InputStream in=httpURLConnection.getInputStream();
                Bitmap bitmap= BitmapFactory.decodeStream(in);
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        }
    }








}
