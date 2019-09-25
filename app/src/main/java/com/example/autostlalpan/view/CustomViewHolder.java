package com.example.autostlalpan.view;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autostlalpan.R;
import com.example.autostlalpan.model.CarsPojo;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    ImageView ivCarPicture;
    TextView tvCarTitulo, tvCarPrice;
    Button btnDetalles;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        ivCarPicture = itemView.findViewById(R.id.iv_car_picture);
        tvCarTitulo = itemView.findViewById(R.id.tv_titulo);
        tvCarPrice = itemView.findViewById(R.id.tv_price);
        btnDetalles = itemView.findViewById(R.id.btn_details);
    }

    public void onBindViewHolder(final CarsPojo item,
                                 final CustomListener listener) {
        btnDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(item);
            }
        });
    }
}
