package com.azamat.retrofitexample.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azamat.retrofitexample.R;
import com.azamat.retrofitexample.model.CountryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.MyViewHolder> {

    List<CountryModel> countries;

    public CountryListAdapter(List<CountryModel> countries) {
        this.countries = countries;
    }

    public void updateCountries(List<CountryModel> newCountries) {
        countries.clear();
        countries.addAll(newCountries);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.bind(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView countryName;

        @BindView(R.id.capital)
        TextView capital;

        @BindView(R.id.imageView)
        ImageView flag;

        public MyViewHolder(@NonNull View v) {
            super(v);

            ButterKnife.bind(this, v);

        }

        void bind(CountryModel country) {
            countryName.setText(country.getCountryName());
            capital.setText(country.getCapital());

            Util.loadImage(flag, country.getFlag(), Util.getProgressDrawable(flag.getContext()));
        }
    }
}
