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
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem headlinesTab,businessTab,sportsTab,scienceTab,technologyTab,healthTab,entertainmentTab;
    PageAdapter pagerAdapter;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mToggle;
    AppBarConfiguration mAppBarConfiguration;
    NavigationView navigationView;

    static String actress;

    static String arrayOfJsons[];


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        actress="Ilena D Cruz";

        Log.i("Trial ", "Sandeep");

        /*Intent intent=getIntent();
        arrayOfJsons=intent.getStringArrayExtra("articleDetails");

        for(int i=0;i<arrayOfJsons.length;i++)
        {
            Log.i("arrayOfJsons", arrayOfJsons[i]);
        }*/

        Log.i("Trial ", "Kiran");

        tabLayout=(TabLayout)findViewById(R.id.tabLayout);

        headlinesTab=(TabItem)findViewById(R.id.headlinesTab);
        businessTab=(TabItem)findViewById(R.id.businessTab);
        sportsTab=(TabItem)findViewById(R.id.sportsTab);
        scienceTab=(TabItem)findViewById(R.id.scienceTab);
        technologyTab=(TabItem)findViewById(R.id.technologyTab);
        healthTab=(TabItem)findViewById(R.id.healthTab);
        entertainmentTab=(TabItem)findViewById(R.id.entertainmentTab);
        viewPager=findViewById(R.id.viewPager);


        /*//FragmentManager fragmentManager=getSupportFragmentManager();
        Bundle bundle = new Bundle();
        headlinesTab myFrag = new headlinesTab();
        //fragmentManager.beginTransaction().replace(R.id.container, myFrag).commit();
        bundle.putString("my_key", "Alia Bhatt");
        myFrag.setArguments(bundle);*/

        actress="Alia Bhatt";


        pagerAdapter=new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        actress="Shraddha Kapoor";


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition()==0)
                {
                    pagerAdapter.notifyDataSetChanged();
                }
                else if(tab.getPosition()==1)
                {
                    pagerAdapter.notifyDataSetChanged();
                }
                else if(tab.getPosition()==2)
                {
                    pagerAdapter.notifyDataSetChanged();
                }
                else if(tab.getPosition()==3)
                {
                    pagerAdapter.notifyDataSetChanged();
                }
                else if(tab.getPosition()==4)
                {
                    pagerAdapter.notifyDataSetChanged();
                }
                else if(tab.getPosition()==5)
                {
                    pagerAdapter.notifyDataSetChanged();
                }
                else if(tab.getPosition()==6)
                {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        actress="Pooja Hegde";

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        actress="Deepika Padukone";




        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationView);
        mToggle=new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);





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
                Intent i = new Intent(Home.this, Home.class);
                startActivity(i);
                break;
            case R.id.saved:
                Intent i1 = new Intent(Home.this, SavedArticles.class);
                startActivity(i1);
                break;

        }
        return true;
    }



}
