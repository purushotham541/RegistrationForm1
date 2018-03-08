package com.example.purushotham.registrationform1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by purushotham on 3/8/2018.
 */

public class Mydatabase extends SQLiteOpenHelper
{
    Context ctx;
    public static String name="MySQLite";
    public static int version=1;

    public Mydatabase(Context context)
    {
        super(context, name, null, version);
        ctx=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String qry="create table mydetails(name TEXT,email TEXT,mobile TEXT,dept TEXT)";
        sqLiteDatabase.execSQL(qry);
        Toast.makeText(ctx,"Database is created..",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
}
