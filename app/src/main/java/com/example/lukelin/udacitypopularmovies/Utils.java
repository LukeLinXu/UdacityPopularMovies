package com.example.lukelin.udacitypopularmovies;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;


/**
 * Created by lukelin on 2016-09-15.
 */
public class Utils {

    public static void switchFragment(FragmentManager fragmentManager, Fragment fragment, int fragmentContainerId) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerId, fragment, null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void showToast(int text, FragmentActivity activity) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }
}
