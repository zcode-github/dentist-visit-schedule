package com.example.bracesteeth;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewListContents extends AppCompatActivity {
    DatabaseHelper myDB;
    ListView listView;
    TextView r;
    ArrayList<Braces> bracesList;
    Braces braces;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);

        myDB = new DatabaseHelper(this);

        bracesList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(this,"The Database is empty  :(.",Toast.LENGTH_LONG).show();
        }else{
            int i=0;
            while(data.moveToNext()){
                braces = new Braces(data.getString(0),data.getString(1),data.getString(2));
                bracesList.add(i,braces);
                System.out.println(data.getString(0)+" "+data.getString(1)+" "+data.getString(2));
                System.out.println(bracesList.get(i).getId());
                i++;
            }
            ThreeColumn_ListAdapter adapter =  new ThreeColumn_ListAdapter(this,R.layout.list_adapter_view, bracesList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    myDB.delete(bracesList.get(position).getId());
                    Intent intent = new Intent(ViewListContents.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(ViewListContents.this,"Successfully Deleted  :).",Toast.LENGTH_LONG).show();
                    return false;
                }
            });
        }
        r=(TextView) findViewById(R.id.res);
        if(myDB.countPrix()==null)
        {
            r.setText("Total: 0 DH");
        }
        else {
            r.setText("Total: "+myDB.countPrix()+" DH");
        }
    }
}
