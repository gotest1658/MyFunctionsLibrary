package tw.idv.test.myfunctions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SQLiteImport_ExportDB extends SQLiteOpenHelper {
    private static Context context;
    private static String DB_PATH;
    private static String DB_NAME;

    public SQLiteImport_ExportDB(Context context, String DB_NAME) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        this.DB_NAME = DB_NAME;
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
    }

    public void importDataBase(String path) {
        InputStream input = null;
        OutputStream output = null;
        try {
            this.getReadableDatabase();
            String inFileName = path + DB_NAME;
            input = new FileInputStream(inFileName);
            String outFileName = DB_PATH + DB_NAME;
            output = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.flush();
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exportDataBase(String path) {
        InputStream input = null;
        OutputStream output = null;
        try {
            String inFileName = DB_PATH + DB_NAME;
            input = new FileInputStream(inFileName);
            String outFileName = path + DB_NAME;
            output = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.flush();
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
