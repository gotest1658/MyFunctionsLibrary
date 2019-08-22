package tw.idv.test.myfunctions;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

//So far, there are six functions which made by me on 2019/08/22
public class MyFunctions {
    public static Toast toast;
    public static Context context;

    //display a custom message with Toast widget
    public static void showToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }

    //display a date picker screen
    public static void getDate(Context context, final TextView textView) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textView.setTextColor(Color.BLUE);
                textView.setText("" + year + "/" + (month + 1) + "/" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    //display Author name
    public static void displayAuthor(Context context, String authorName) {
        new AlertDialog.Builder(context)
                .setTitle("Information")
                .setMessage("Designed by " + authorName)
                .setPositiveButton("OK", null)
                .setCancelable(false)
                .show();
    }

    //display version
    public static void displayVersion(Context context, String version) {
        new AlertDialog.Builder(context)
                .setTitle("Information")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setMessage("Version:" + version)
                .setPositiveButton("OK", null)
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    public static void exportSharedPreferences(Context context, String path) {
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            String SP_PATH = "/data/data/" + context.getPackageName() + "/shared_prefs/";
            String SP_FILENAME = context.getPackageName() + "_preferences.xml";

            String inFileName = SP_PATH + SP_FILENAME;
            myInput = new FileInputStream(inFileName);
            String outFileName = path + SP_FILENAME;
            myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
        } catch (Exception e) {
        }
    }

    public static void importSharedPreferences(Context context, String path) {
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            String SP_FILENAME = context.getPackageName() + "_preferences.xml";
            String SP_PATH = "/data/data/" + context.getPackageName() + "/shared_prefs/";

            String inFileName = path + SP_FILENAME;
            myInput = new FileInputStream(inFileName);
            String outFileName = SP_PATH + SP_FILENAME;
            myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
        } catch (Exception e) {
        }
    }

}
