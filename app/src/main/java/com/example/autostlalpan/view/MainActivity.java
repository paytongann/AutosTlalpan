package com.example.autostlalpan.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autostlalpan.R;
import com.example.autostlalpan.model.CarsPojo;
import com.example.autostlalpan.presenter.Presenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewContract, CustomListener {

    private Presenter presenter;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private TextView tvCompanyTelephone, tvCompanyEmail, tvCompanyAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        presenter = new Presenter(this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomAdapter(this);
        adapter.setListener(MainActivity.this);
        recyclerView.setAdapter(adapter);
        presenter.initRetrofit();
        presenter.onBindView(this);
        tvCompanyTelephone = findViewById(R.id.tvCompanyTelephone);
        SpannableString content = new SpannableString("Teléfono: 5336 - 3962 Y 63");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tvCompanyTelephone.setText(content);
        tvCompanyTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:53363962"));
                startActivity(intent);
            }
        });
        tvCompanyEmail = findViewById(R.id.tvCompanyEmail);
        SpannableString contentEmail = new SpannableString("E-Mail: VENTAS@AUTOTLALPAN.COM");
        contentEmail.setSpan(new UnderlineSpan(), 0, contentEmail.length(), 0);
        tvCompanyEmail.setText(contentEmail);
        tvCompanyEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailintent = new Intent(android.content.Intent.ACTION_SEND);
                emailintent.setType("plain/text");
                emailintent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] {"VENTAS@AUTOTLALPAN.COM" });
                emailintent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                emailintent.putExtra(android.content.Intent.EXTRA_TEXT,"");
                startActivity(Intent.createChooser(emailintent, "Selecciona Gmail para enviar correo electrónico a Autos Tlalpan."));
            }
        });
        tvCompanyAddress = findViewById(R.id.tvCompanyAddress);
        SpannableString contentAddress = new SpannableString("CALZADA DE TLALPAN 2247 BIS, COLONIA CIUDAD\nJARDÍN, COYOACÁN");
        contentAddress.setSpan(new UnderlineSpan(), 0, contentAddress.length(), 0);
        tvCompanyAddress.setText(contentAddress);
        tvCompanyAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4192?q=" + Uri.encode("Autos Certificados Tlalpan"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
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
        searchView.setQueryHint("Buscar Autos...");
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
        intent.putExtra("interior", item.equipoInterior);
        intent.putExtra("exterior", item.equipoExterior);
        String price = item.precio.toString();
        intent.putExtra("price", price);
        startActivity(intent);
    }
}
