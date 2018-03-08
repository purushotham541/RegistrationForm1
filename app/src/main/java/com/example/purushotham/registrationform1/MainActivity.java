package com.example.purushotham.registrationform1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    EditText et1,et2,et3,et4;
    Button b1,b2,b3;
    Mydatabase mydatabase;
    StringBuilder stringBuilder;
    ArrayList al;
    ListView listView;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText)findViewById(R.id.name);
        et2=(EditText)findViewById(R.id.email);
        et3=(EditText)findViewById(R.id.mobile);
        et4=(EditText)findViewById(R.id.dpt);
        listView=(ListView)findViewById(R.id.lv);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        b3=(Button)findViewById(R.id.b3);
        al=new ArrayList();
        arrayAdapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,al);


        mydatabase=new Mydatabase(MainActivity.this);

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String name=et1.getText().toString();
                String email=et2.getText().toString();
                String mobile=et3.getText().toString();
                String dept=et4.getText().toString();

                SQLiteDatabase sqLiteDatabase=mydatabase.getWritableDatabase();
                String qry="insert into mydetails values('"+name+"','"+email+"','"+mobile+"','"+dept+"')";
                sqLiteDatabase.execSQL(qry);
                Toast.makeText(getApplicationContext(),"Data inserted successfully..",Toast.LENGTH_LONG).show();


            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                stringBuilder=new StringBuilder();
               final SQLiteDatabase sqLiteDatabase= mydatabase.getReadableDatabase();
               String qry="select * from mydetails";
               Cursor cursor=sqLiteDatabase.rawQuery(qry,null);
               boolean c=cursor.moveToNext();
               if(c==true)
               {
                   do
                   {
                       String name=cursor.getString(0);
                      // stringBuilder.append("\n"+name);
                       al.add(name);

                   }while (cursor.moveToNext());
                  // Toast.makeText(getApplicationContext(),stringBuilder.toString(),Toast.LENGTH_LONG).show();

                   listView.setAdapter(arrayAdapter);
                   listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                   {
                       @Override
                       public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                       {
                           SQLiteDatabase sqLiteDatabase1=mydatabase.getReadableDatabase();
                           String name1=(String)al.get(i);
                           String qry ="select email,mobile from mydetails where name = "+name1;
                           Cursor c=sqLiteDatabase1.rawQuery(qry,null);
                           c.moveToFirst();
                           String email=c.getString(1);
                           String mobile=c.getString(2);
                           Toast.makeText(getApplicationContext(),""+email+mobile,Toast.LENGTH_LONG).show();





                       }
                   });

               }

            }
        });
        b3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SQLiteDatabase sqLiteDatabase=mydatabase.getWritableDatabase();
                String qry="update mydetails set ('name') values ('purusotham')";
                /*String qry="UPDATE users SET ('field1', 'field2', 'field3')
                VALUES ('value1', 'value2', 'value3')"";*/
                sqLiteDatabase.execSQL(qry);
                Toast.makeText(getApplicationContext(),"Database is updated successfully..",Toast.LENGTH_LONG).show();

            }
        });

    }
}
