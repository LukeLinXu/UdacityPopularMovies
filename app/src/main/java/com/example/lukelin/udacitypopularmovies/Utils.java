package com.example.lukelin.udacitypopularmovies;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


/**
 * Created by lukelin on 2016-09-15.
 */
public class Utils {

    public static void switchFragment(FragmentManager fragmentManager, Fragment fragment, int fragmentContainerId) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerId, fragment, null);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
