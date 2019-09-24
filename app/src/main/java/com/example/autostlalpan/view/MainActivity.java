package com.example.autostlalpan.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.autostlalpan.R;
import com.example.autostlalpan.model.CarsPojo;
import com.example.autostlalpan.presenter.Presenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewContract, CustomListener {

    private Presenter presenter;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter(this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomAdapter(this);
        adapter.setListener(MainActivity.this);
        recyclerView.setAdapter(adapter);
        presenter.initRetrofit();
        presenter.onBindView(this);
    }

    @Override
    public void onDataPopulated(ArrayList<CarsPojo> data) {
        adapter.setDataSet(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public void onClick(CarsPojo item) {
        Intent intent = new Intent(this, DetailedCarActivity.class);
        setResult(RESULT_OK, intent);
        String [] photos = new String[item.imagenes.size()];
        for (int i=0; i<item.imagenes.size(); i++){
            photos[i] = item.imagenes.get(i);
        }
        intent.putExtra("photos", photos);
        intent.putExtra("titulo", item.titulo);
        intent.putExtra("modelo", item.modelo.toString());
        intent.putExtra("kilometraje", item.kilomeraje.toString());
        intent.putExtra("color exterior", item.colorExterior);
        intent.putExtra("color interior", item.colorInterior);
        intent.putExtra("transmision", item.motor.transmision);
        intent.putExtra("inyeccion", item.motor.inyeccion);
        intent.putExtra("drivetrain", item.motor.drivetrain);
        startActivity(intent);
    }
}
