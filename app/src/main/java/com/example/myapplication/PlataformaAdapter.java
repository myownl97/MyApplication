package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PlataformaAdapter extends RecyclerView.Adapter<PlataformaAdapter.PeliculaViewHolder> {
    private Context context;
    private List<Pelicula> peliculas;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public PlataformaAdapter(Context context, List<Pelicula> peliculas) {
        this.context = context;
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_xml, parent, false);
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula pelicula = peliculas.get(position);
        holder.bind(pelicula);

        // Cargar la imagen usando Picasso en onBindViewHolder()
        Picasso.get().load(pelicula.getFotoUrl()).into(holder.imagen_pelicula);
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public class PeliculaViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombre;
        private ImageView imagen_pelicula;
        private TextView fecha_peli;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.txt_movie_name);
            imagen_pelicula = itemView.findViewById(R.id.imag_pelic);
            fecha_peli = itemView.findViewById(R.id.tv_fecha);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void bind(Pelicula pelicula) {
            textViewNombre.setText(pelicula.getNombre());
        }
    }
}