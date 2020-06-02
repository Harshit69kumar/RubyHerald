package com.example.rubyherald;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

public class DetailedNews extends AppCompatActivity
{
    ConstraintLayout constraintLayout;
    TextView authorTextView, titleTextView, descriptionTextView, publishedAtTextView, contentTextView, articleUrlTextView;
    ImageView imageView;
    String author;
    String title;
    String description;
    String imageUrl;
    String publishedAt;
    String content;
    String articleUrl;

    static String articleDetails[];

    int titleTapCounter=0;

    static ArrayList<String>savedArticleDetails=new ArrayList<>();
    SharedPreferences sharedPreferences;
    HashSet<String>savedNewsSet=new HashSet<>();
    SharedPreferences prefs;
    Gson gson;
    String json;
    Type type;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        constraintLayout=findViewById(R.id.constraintLayout);

        authorTextView=findViewById(R.id.authorTextView);
        titleTextView=findViewById(R.id.titleTextView);
        descriptionTextView=findViewById(R.id.descriptionTextView);
        publishedAtTextView=findViewById(R.id.publishedAtTextView);
        contentTextView=findViewById(R.id.contentTextView);
        articleUrlTextView=findViewById(R.id.articleUrlTextView);
        imageView=findViewById(R.id.mainImageView);




        Intent intent=getIntent();
        articleDetails=intent.getStringArrayExtra("articleDetails");

        author=articleDetails[0];
        title=articleDetails[1];
        description=articleDetails[2];
        imageUrl=articleDetails[3];
        publishedAt=articleDetails[4];
        content=articleDetails[5];
        articleUrl=articleDetails[6];

        authorTextView.setText(author);
        titleTextView.setText(title);
        descriptionTextView.setText(description);
        publishedAtTextView.setText(publishedAt);
        contentTextView.setText(content);



        gson = new Gson();


        sharedPreferences=getApplicationContext().getSharedPreferences("com.example.rubyherald", Context.MODE_PRIVATE);


        Glide.with(this)
                .load(imageUrl)
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView);

    }


    public void onClickArticleTitle(View v)
    {

        Log.i("articleDetails.length", Integer.toString(articleDetails.length));


        HashSet<String>hs= (HashSet<String>) sharedPreferences.getStringSet("savedDetails", null);
        if(hs==null || !hs.contains(title))
        {
            titleTextView.setTextColor(Color.RED);

            Toast.makeText(this, "This article is now saved", Toast.LENGTH_SHORT).show();

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            gson = new Gson();
            json = prefs.getString("messi", null);
            type = new TypeToken<ArrayList<String>>() {}.getType();
            savedArticleDetails= gson.fromJson(json, type);

            //savedArticleDetails.clear();

            if(savedArticleDetails==null)
            {
                //Do nothing
                savedArticleDetails=new ArrayList<>();
            }
            else
            {
                Log.i("savedDetails", savedArticleDetails.toString());
            }

            savedArticleDetails.add(author);
            savedArticleDetails.add(title);
            savedArticleDetails.add(description);
            savedArticleDetails.add(imageUrl);
            savedArticleDetails.add(publishedAt);
            savedArticleDetails.add(content);
            savedArticleDetails.add(articleUrl);


            prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = prefs.edit();
            json = gson.toJson(savedArticleDetails);
            editor.putString("messi", json);
            editor.apply();     // This line is IMPORTANT !!

            HashSet<String> hs2=new HashSet<>(savedArticleDetails);
            sharedPreferences.edit().putStringSet("savedDetails", hs2).apply();


        }
        else
        {
            //Do nothing
            Toast.makeText(this, "This article is alrready saved", Toast.LENGTH_SHORT).show();
        }





    }



    public void onClickArticleUrl(View view)
    {
        Intent webviewIntent=new Intent(getApplicationContext(), WebviewActivity.class);
        webviewIntent.putExtra("url", articleUrl);
        Log.i("articleUrl", articleUrl);
        startActivity(webviewIntent);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.shareButton:
                Intent sharingIntent=new Intent(Intent.ACTION_SEND);

                String shareSubject="Check out this news in the all new RUBY HERALD app";
                String shareBody=title+"\n\n"+shareSubject;

                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareSubject);
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

                sharingIntent.setType("text/plain");
                sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                startActivity(Intent.createChooser(sharingIntent, "Share using"));


                /*Bitmap bm = screenShot(constraintLayout);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                File f = new File(Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg");
                try {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/temporary_file.jpg"));
                startActivity(Intent.createChooser(share, "Share Image"));*/

                break;
        }

        return super.onOptionsItemSelected(item);
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


    private Bitmap screenShot(View view)
    {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }



    private static File saveBitmap(Bitmap bm, String fileName)
    {
        final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots";
        File dir = new File(path);
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dir, fileName);
        try
        {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 90, fOut);
            fOut.flush();
            fOut.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return file;
    }


}
