package com.example.testmachinelearning.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testmachinelearning.Entity.PersonaEntity;
import com.example.testmachinelearning.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter {
    private List<PersonaEntity> listaDePersonas;
    private Seleccionado seleccionado;

    public AdapterRecycler(Seleccionado seleccionado) {
        this.seleccionado = seleccionado;
        listaDePersonas = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View cellInflated = layoutInflater.inflate(R.layout.celda_persona, parent, false);
        PersonaViewHolder viewHolder = new PersonaViewHolder(cellInflated);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PersonaEntity persona = listaDePersonas.get(position);
        PersonaViewHolder personaViewHolder = (PersonaViewHolder) holder;
        personaViewHolder.setData(persona);
    }

    @Override
    public int getItemCount() {
        return listaDePersonas.size();
    }

    public void cargarPersonas(List<PersonaEntity> listado){
        this.listaDePersonas.clear();
        this.listaDePersonas.addAll(listado);
        notifyDataSetChanged();
    }

    public class PersonaViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenPersona;
        TextView textViewNombre;
        TextView textViewApellido;

        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenPersona = itemView.findViewById(R.id.imagenPersona);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewApellido = itemView.findViewById(R.id.textViewApellido);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    seleccionado.personaElegida(listaDePersonas.get(getAdapterPosition()));
                }
            });
        }

        public void setData(PersonaEntity personaEntity){
            Glide.with(itemView).load(personaEntity.getPicture().getLarge()).into(imagenPersona);
            textViewNombre.setText(personaEntity.getName().getFirst());
            textViewApellido.setText(personaEntity.getName().getLast());
        }
    }

    public interface Seleccionado {
        void personaElegida(PersonaEntity persona);
    }
}
