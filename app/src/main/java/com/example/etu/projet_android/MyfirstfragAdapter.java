package com.example.etu.projet_android;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;

import com.example.etu.projet_android.stab.SlidingTabLayout;

/**
 * Created by etu on 02/12/15.
 */
public class MyfirstfragAdapter extends FragmentPagerAdapter {

    private String tabT[]= new String[]{"photo","internet", "pdf", "BDD"};
    private Context context;

    public MyfirstfragAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    //renvoit le bon fragment selon l'item
    public Fragment getItem(int pos) {
        if(pos==0){
            return new photofragment();
        }else if(pos==1){
            return new internetfragment();
        } else if(pos==2){
            return new pdffragment();
        }else if(pos==3){
            return new bddfragment();
        }
        return null;
    }

    @Override
    //renvoit le nombre d'item
    public int getCount() {
        return 4;
    }

    //recupère le nom
    @Override
    public CharSequence getPageTitle(int position) {
        return tabT[position];
    }
}
