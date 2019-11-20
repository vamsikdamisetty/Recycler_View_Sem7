package com.example.recycler_view_sem7;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by USER on 08-Aug-19.
 */

public class MyDataBase extends SQLiteOpenHelper
{
    private static final int dbv =1;
    private static final String dbname = "mydb";

    ArrayList<Person> al;

    Context ct;
    MyDataBase(Context c)
    {
        super(c,dbname,null,dbv);
        ct =c;
        al = new ArrayList<>();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String s = "create table info (name text, reg text )";

        sqLiteDatabase.execSQL(s);

    }

    public void insertValues(Person p)
    {
        al.add(p);

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("name", p.getName());
        cv.put("reg",p.getReg());

        db.insert("info",null, cv);

        Toast.makeText(ct,"Insert Completed and size is"+ al.size(), Toast.LENGTH_SHORT).show();
    }


    public ArrayList<Person> showValues()
    {
        Toast.makeText(ct,"Inside show values", Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = getReadableDatabase();
        String s = "select * from info";

        Cursor cr = db.rawQuery(s, null);

        while (cr.moveToNext())
        {

            String s1 =cr.getString(0);
            int a = cr.getInt(1);
            Person p = new Person(s1,a);
            al.add(p);
        }
        Toast.makeText(ct,"Array Size is "+al.size(), Toast.LENGTH_SHORT).show();
        return al;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void removeValues(int position)
    {
        SQLiteDatabase db = getWritableDatabase();

        String where = "name = ? and reg = ?";

        Person p = al.get(position);
        String name = p.getName();
        int a = p.getReg();
        String [] ss = {name, ""+a};
        al.remove(p);
        db.delete("info",where,ss);


        Toast.makeText(ct,"Deletion Completed", Toast.LENGTH_SHORT).show();


    }
}
