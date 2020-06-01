package com.example.rubyherald;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter
{
    int numberOfTabs;

    public PageAdapter(@NonNull FragmentManager fm, int numberOfTabs)
    {
        super(fm);
        this.numberOfTabs=numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                Log.i("Trial", "0");
                return new headlinesTab();
            case 1:
                Log.i("Trial", "1");
                return new businessTab();
            case 2:
                Log.i("Trial", "2");
                return new sportsTab();
            case 3:
                Log.i("Trial", "3");
                return new scienceTab();
            case 4:
                Log.i("Trial", "4");
                return new technologyTab();
            case 5:
                Log.i("Trial", "5");
                return new healthTab();
            case 6:
                Log.i("Trial", "6");
                return new entertainmentTab();
            default: return null;
        }
    }

    @Override
    public int getCount()
    {
        return numberOfTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object)
    {
        return POSITION_NONE;
    }

}
