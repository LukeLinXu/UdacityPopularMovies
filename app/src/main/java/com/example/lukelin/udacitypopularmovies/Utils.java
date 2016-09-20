package com.example.lukelin.udacitypopularmovies;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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

    public static void showDialog(Context context, String messageId, String titleId, DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(messageId)
                .setTitle(titleId)
                .setCancelable(true)
                .setPositiveButton(R.string.ok, onClickListener);
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void watchYoutubeVideo(Context context, String id){
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        context.startActivity(webIntent);
    }
}
