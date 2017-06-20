package com.zakiadev.firebasetest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Gndx on 3/19/2017.
 */

public class Pager extends FragmentPagerAdapter{

    int tabCount,saldo;
    String username,email,password, key;

    public Pager(String username, String email, String password, int saldo, String key, FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
        this.username = username;
        this.email = email;
        this.password = password;
        this.saldo = saldo;
        this.key = key;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                Tab1 tab1 = new Tab1();
                return tab1;
            case 1 :
                Tab2 tab2 = new Tab2();
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                bundle.putString("email",email);
                bundle.putString("password",password);
                bundle.putInt("saldo",saldo);
                bundle.putString("key",key);
                tab2.setArguments(bundle);
                return tab2;
            case 2 :
                Tab3 tab3 = new Tab3();
                Bundle bundle3 = new Bundle();
                bundle3.putInt("saldo",saldo);
                bundle3.putString("key",key);
                tab3.setArguments(bundle3);
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "History";
            case 1:
                return "Status";
            case 2:
                return "Beli Saldo";
        }
        return null;
    }
}
