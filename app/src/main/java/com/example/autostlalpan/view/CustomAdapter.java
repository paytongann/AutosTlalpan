package com.example.autostlalpan.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.autostlalpan.R;
import com.example.autostlalpan.model.CarsPojo;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> implements Filterable {


    private ArrayList<CarsPojo> dataSet;
    private ArrayList<CarsPojo> dataSetCopy;
    private CustomListener listener;
    private Context context;

    public void setDataSet(ArrayList<CarsPojo> dataSet){
        this.dataSet = dataSet;
        dataSetCopy = new ArrayList<>(dataSet);
        notifyDataSetChanged();
    }

    public void setListener(CustomListener listener){
        this.listener = listener;
    }

    public CustomAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tvCarPrice.setText( "Precio: $" + dataSet.get(position).precio);
        holder.tvCarTitulo.setText(dataSet.get(position).titulo);
        Glide.with(context).load(dataSet.get(position).imagenes.get(0)).into(holder.ivCarPicture);
        holder.onBindViewHolder(dataSet.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.size() : 0 ;
    }

    @Override
    public Filter getFilter() {
        return charFilter;
    }

    private Filter charFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<CarsPojo> filteredList = new ArrayList<>();
            if (charSequence.equals(null)|| charSequence.length()==0){
                filteredList.addAll(dataSetCopy);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (CarsPojo item : dataSetCopy){
                    if (item.titulo.toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    } else if (item.modelo.toString().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            dataSet.clear();
            dataSet.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };
}
