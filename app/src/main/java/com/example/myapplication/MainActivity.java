package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> items=getContries("countries.json");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner_layout, R.id.txt,items);
        spinner.setAdapter(adapter);




    }

    public ArrayList<String> getContries(String fileName){
        JSONArray jsonArray = null;
        ArrayList<String> cList = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(fileName);
            int size= is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json=new String(data,"UTF-8");
            jsonArray = new JSONArray(json);
            if (jsonArray!=null){
                for (int i=0; i<jsonArray.length(); i++){
                    cList.add(jsonArray.getJSONObject(i).getString("departement"));
                }
            }
        }
        catch (IOException e) {e.printStackTrace();}
        catch (JSONException js) {js.printStackTrace();}
        return cList;

    }

    public boolean onCreateOtionsMenu(Menu menu){

        // inflate the menu; this adds items to the action bqr if it is present
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

