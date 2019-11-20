package com.example.recycler_view_sem7;



import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    EditText etName, etReg;
    RecyclerView rv;
    RecyclerView.LayoutManager lm;
    MyAdapter md;
    ArrayList<Person> al;

    MyDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etName = findViewById(R.id.etname);
        etReg = findViewById(R.id.etreg);

        rv = findViewById(R.id.rv);

        al = new ArrayList<>();

        dataBase = new MyDataBase(this);

        al = dataBase.showValues();

        md = new MyAdapter(this,al, dataBase);

        lm = new LinearLayoutManager(this);

        rv.setLayoutManager(lm);

        rv.setAdapter(md);

        rv.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL));
    }

    public void dothis(View view)
    {
        String name = etName.getText().toString();
        int reg = Integer.parseInt(etReg.getText().toString());

        Person p = new Person(name,reg);

        dataBase.insertValues(p);

        md.notifyDataSetChanged();
    }


}

