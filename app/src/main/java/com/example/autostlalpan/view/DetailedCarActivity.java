package com.example.autostlalpan.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.autostlalpan.R;

import me.relex.circleindicator.CircleIndicator;

public class DetailedCarActivity extends AppCompatActivity {

    ViewPager viewPager;
    MyCustomPagerAdapter myCustomPagerAdapter;
    private CircleIndicator circleIndicator;
    private TextView tvCarTitulo, tvCarModelo, tvCarKilometraje, tvCarColorExterior, tvCarColorInterior, tvCarTransmision, tvCarInyeccion, tvCarDriveTrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_car);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewPager = findViewById(R.id.viewPager);
        String[] photos = getIntent().getStringArrayExtra("photos");

        for (int i = 0; i < photos.length; i++) {
        }
        myCustomPagerAdapter = new MyCustomPagerAdapter(DetailedCarActivity.this, photos);
        viewPager.setAdapter(myCustomPagerAdapter);
        CircleIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        tvCarTitulo = findViewById(R.id.tv_car_titulo);
        tvCarTitulo.setText(getIntent().getStringExtra("titulo"));
        tvCarModelo = findViewById(R.id.tv_car_modelo);
        tvCarModelo.setText(getIntent().getStringExtra("modelo"));
        tvCarKilometraje = findViewById(R.id.tv_car_kilometraje);
        tvCarKilometraje.setText(getIntent().getStringExtra("kilometraje"));
        tvCarColorExterior = findViewById(R.id.tv_car_exterior);
        tvCarColorExterior.setText(getIntent().getStringExtra("color exterior"));
        tvCarColorInterior = findViewById(R.id.tv_car_interior);
        tvCarColorInterior.setText(getIntent().getStringExtra("color interior"));
        tvCarTransmision = findViewById(R.id.tv_car_transmision);
        tvCarTransmision.setText(getIntent().getStringExtra("transmision"));
        tvCarInyeccion = findViewById(R.id.tv_car_inyeccion);
        tvCarInyeccion.setText(getIntent().getStringExtra("inyeccion"));
        tvCarDriveTrain = findViewById(R.id.tv_car_drivetrain);
        tvCarDriveTrain.setText(getIntent().getStringExtra("drivetrain"));
    }
}
