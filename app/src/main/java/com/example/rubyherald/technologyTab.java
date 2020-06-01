package com.example.rubyherald;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.util.Log;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link technologyTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class technologyTab extends Fragment
{
    ListView listView;
    ScrollView scrollView;
    ArrayAdapter arrayAdapter;
    CustomAdapter customAdapter;
    ArrayList<String>titles=new ArrayList<>();
    ArrayList<String>description=new ArrayList<>();
    ArrayList<String>author=new ArrayList<>();
    ArrayList<String>imageUrl=new ArrayList<>();
    ArrayList<String>publishedAt=new ArrayList<>();
    ArrayList<String>content=new ArrayList<>();
    ArrayList<String>url=new ArrayList<>();
    SQLiteDatabase technologyDB;

    //int IMAGES[]={R.drawable.cat1, R.drawable.cat2, R.drawable.supremecourtimage};
    //String TITLE[]={"Cat 1", "Cat 2", "Supreme Court"};
    //String DESCRIPTION[]={"Furry Cat", "Sweet Cat", "Highest Court in India"};


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public technologyTab()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment technologyTab.
     */
    // TODO: Rename and change types and number of parameters
    public static technologyTab newInstance(String param1, String param2) {
        technologyTab fragment = new technologyTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("myStr");
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Log.i("myStr", "Shraddha Kapoor");

        // Inflate the layout for this fragment

        //Log.i("Home.actress", Home.actress);

        technologyDB=getContext().openOrCreateDatabase("technology", Context.MODE_PRIVATE, null);
        technologyDB.execSQL("CREATE TABLE IF NOT EXISTS technology (id INTEGER PRIMARY KEY, articleAuthor VARCHAR, articleTitle VARCHAR, articleDescription VARCHAR, articleImageUrl VARCHAR, articlePublishedAt VARCHAR, articleContent VARCHAR, articleUrl VARCHAR)");

        Log.i("Test ", "Himanshu");

        Log.i("arrayOfJsons[4] ", MainActivity.arrayOfJsons[4]);



        sortOutJsonOrder(MainActivity.arrayOfJsons[4]);




        /*DownloadTask task=new DownloadTask();
        try
        {
            task.execute("http://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=d0ed4b2e92604b8d85b2740d29015468");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/

        Log.i("Test ", "Rithwik");

        //updateListView();

        Log.i("Test ", "Yashas");

        View view =  inflater.inflate(R.layout.fragment_technology_tab, container, false);
        listView=(ListView) view.findViewById(R.id.listView);
        scrollView=(ScrollView)view.findViewById(R.id.scrollView);
        //non_scroll_list = (NonScrollListView)view.findViewById(R.id.lv_nonscroll_list);


        customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);

        Log.i("Test ", "Palash");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), DetailedNews.class);
                intent.putExtra("articleDetails", new String[]{author.get(position), titles.get(position), description.get(position), imageUrl.get(position), publishedAt.get(position), content.get(position), url.get(position)});
                startActivity(intent);
            }
        });

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                int action = event.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_UP:
                        scrollView.requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });


        Log.i("Test ", "Dhoke");

        updateListView();


        Log.i("Test ", "Abhinandan");


        return view;
    }




    class CustomAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return author.size();
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

            if(imageUrl.size()>0 && imageUrl.get(position)!=null)
            {
                Log.i("Test ", imageUrl.get(position));
                String imageUrlAddress=imageUrl.get(position);
                /*ImageDownloader task=new ImageDownloader();
                try
                {
                    Bitmap myImage = task.execute(imageUrlAddress).get();
                    snapshot.setImageBitmap(myImage);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }*/
                Glide.with(getContext())
                        .load(imageUrlAddress)
                        .fitCenter()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(snapshot);
            }


            if(titles.size()>0 && titles.get(position)!=null)
            {
                Log.i("Test ", titles.get(position));
                titleTextView.setText(titles.get(position));
            }
            if(author.size()>0 && author.get(position)!=null)
            {
                Log.i("Test ", author.get(position));
                authorTextView.setText(author.get(position));
            }

            return convertView;
        }
    }



    /*public class DownloadTask extends AsyncTask<String, Void, String>
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
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                int data=inputStreamReader.read();


                while (data!=-1)
                {
                    char current=(char)data;
                    result+=current;
                    data=inputStreamReader.read();
                }

                Log.i("Result ", result);
                JSONObject jsonObjectOld=new JSONObject(result);

                String articlesInfo=jsonObjectOld.getString("articles");

                JSONArray jsonArray=new JSONArray(articlesInfo);

                technologyDB.execSQL("DELETE FROM technology");

                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject=jsonArray.getJSONObject(i);

                    if(!jsonObject.isNull("author") && !jsonObject.isNull("title") && !jsonObject.isNull("description") && !jsonObject.isNull("urlToImage") && !jsonObject.isNull("publishedAt") && !jsonObject.isNull("content"))
                    {
                        String articleAuthor=jsonObject.getString("author");
                        String articleTitle=jsonObject.getString("title");
                        String articleDescription=jsonObject.getString("description");
                        String articleImageUrl=jsonObject.getString("urlToImage");
                        String articlePublishedAt=jsonObject.getString("publishedAt");
                        String articleContent=jsonObject.getString("content");

                        Log.i("Article author: ", articleAuthor);
                        Log.i("Article title: ", articleTitle);
                        Log.i("Article description: ", articleDescription);
                        Log.i("Article image url: ", articleImageUrl);
                        Log.i("Article published at: ", articlePublishedAt);
                        Log.i("Article content: ", articleContent);

                        //Inserting articleId, articleTitle ans articleUrl inside the SQL table
                        //No need to add primary key
                        String  sql="INSERT INTO technology (articleAuthor, articleTitle, articleDescription, articleImageUrl, articlePublishedAt, articleContent) VALUES (?,?,?,?,?,?)";
                        SQLiteStatement statement=technologyDB.compileStatement(sql);
                        Log.i("Test ", "Skanda");
                        statement.bindString(1,articleAuthor);
                        statement.bindString(2,articleTitle);
                        statement.bindString(3,articleDescription);
                        statement.bindString(4,articleImageUrl);
                        statement.bindString(5,articlePublishedAt);
                        statement.bindString(6,articleContent);
                        statement.execute();
                        Log.i("Test ", "Anish");
                    }

                }

                Log.i("Test ", "Srividya");
                updateListView();
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
            Log.i("Test ", "Rahasya");
            updateListView();
        }
    }*/



    public  void sortOutJsonOrder(String result)
    {
        try
        {
            Log.i("Trial ", "Nikhil");


            Log.i("Result ", result);
            JSONObject jsonObjectOld=new JSONObject(result);

            String articlesInfo=jsonObjectOld.getString("articles");

            JSONArray jsonArray=new JSONArray(articlesInfo);

            technologyDB.execSQL("DELETE FROM technology");

            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject=jsonArray.getJSONObject(i);

                if(!jsonObject.isNull("author") && !jsonObject.isNull("title") && !jsonObject.isNull("description") && !jsonObject.isNull("urlToImage") && !jsonObject.isNull("publishedAt") && !jsonObject.isNull("content"))
                {
                    String articleAuthor=jsonObject.getString("author");
                    String articleTitle=jsonObject.getString("title");
                    String articleDescription=jsonObject.getString("description");
                    String articleImageUrl=jsonObject.getString("urlToImage");
                    String articlePublishedAt=jsonObject.getString("publishedAt");
                    String articleContent=jsonObject.getString("content");
                    String articleUrl=jsonObject.getString("url");

                    Log.i("Article author: ", articleAuthor);
                    Log.i("Article title: ", articleTitle);
                    Log.i("Article description: ", articleDescription);
                    Log.i("Article image url: ", articleImageUrl);
                    Log.i("Article published at: ", articlePublishedAt);
                    Log.i("Article content: ", articleContent);
                    Log.i("Article url: ", articleUrl);

                    //Inserting articleId, articleTitle ans articleUrl inside the SQL table
                    //No need to add primary key
                    String  sql="INSERT INTO technology (articleAuthor, articleTitle, articleDescription, articleImageUrl, articlePublishedAt, articleContent, articleUrl) VALUES (?,?,?,?,?,?,?)";
                    SQLiteStatement statement=technologyDB.compileStatement(sql);
                    Log.i("Test ", "Skanda");
                    statement.bindString(1,articleAuthor);
                    statement.bindString(2,articleTitle);
                    statement.bindString(3,articleDescription);
                    statement.bindString(4,articleImageUrl);
                    statement.bindString(5,articlePublishedAt);
                    statement.bindString(6,articleContent);
                    statement.bindString(7,articleUrl);
                    statement.execute();
                    Log.i("Test ", "Anish");
                }

            }

            Log.i("Test ", "Srividya");
            updateListView();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }






    public void updateListView()
    {
        Log.i("Test ", "Arpitha");

        Cursor c=technologyDB.rawQuery("SELECT * FROM technology", null);

        int authorIndex=c.getColumnIndex("articleAuthor");
        Log.i("Test ", Integer.toString(authorIndex));
        int titleIndex=c.getColumnIndex("articleTitle");
        int descriptionIndex=c.getColumnIndex("articleDescription");
        int imageUrlIndex=c.getColumnIndex("articleImageUrl");
        int publishedIndex=c.getColumnIndex("articlePublishedAt");
        int contentIndex=c.getColumnIndex("articleContent");
        int articleUrlIndex=c.getColumnIndex("articleUrl");

        Log.i("Test ", "Deepak");

        if(c.moveToFirst()) {
            author.clear();
            titles.clear();
            description.clear();
            imageUrl.clear();
            publishedAt.clear();
            content.clear();
            url.clear();


            do {

                //Log.i("Trial: ", c.getString(authorIndex));
                author.add(c.getString(authorIndex));
                titles.add(c.getString(titleIndex));
                description.add(c.getString(descriptionIndex));
                imageUrl.add(c.getString(imageUrlIndex));
                publishedAt.add(c.getString(publishedIndex));
                content.add(c.getString(contentIndex));
                url.add(c.getString(articleUrlIndex));


            } while (c.moveToNext());


            Log.i("Test ", "Himani");
            //Log.i("Test ", titles.get(2));

            customAdapter.notifyDataSetChanged();
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
