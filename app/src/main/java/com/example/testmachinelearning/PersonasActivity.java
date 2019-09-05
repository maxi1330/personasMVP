package com.example.testmachinelearning;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testmachinelearning.Adapter.AdapterRecycler;
import com.example.testmachinelearning.Entity.PersonaEntity;

import java.util.List;

public class PersonasActivity extends Activity implements PersonasInterface.view, AdapterRecycler.Seleccionado {

    private PersonasInterface.presenter presenter;
    private AdapterRecycler adapterRecycler;
    private RecyclerView listadoDePersonas;
    private CheckBox mujer;
    private CheckBox hombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        presenter = new PersonasPresenter(this);
        initRecyclerView();
        presenter.pedirPersonas("");
        mujer.setOnCheckedChangeListener(listener);
        hombre.setOnCheckedChangeListener(listener);
    }

    CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(mujer.isChecked() && hombre.isChecked()){
                presenter.pedirPersonas("");
            } else if(mujer.isChecked() && !hombre.isChecked()){
                presenter.pedirPersonas("female");
            } else {
                presenter.pedirPersonas("male");
            }
        }
    };

    private void initViews() {
        listadoDePersonas = findViewById(R.id.listadoDePersonas);
        mujer = findViewById(R.id.mujer);
        hombre = findViewById(R.id.hombre);
    }

    private void initRecyclerView() {
        adapterRecycler = new AdapterRecycler(this);
        listadoDePersonas.setAdapter(adapterRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        listadoDePersonas.setLayoutManager(layoutManager);
        listadoDePersonas.setHasFixedSize(true);
    }

    @Override
    public void mostrarPersonas(List<PersonaEntity> listaPersonas) {
        adapterRecycler.cargarPersonas(listaPersonas);
    }

    @Override
    public void mostrarMensajeError(String mensaje) {
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }

    public void showDialog(Activity activity, PersonaEntity personaEntity){
        ImageView imagenDetalle;
        TextView nombreCompletoDetalle;
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialogo_detalle);

        imagenDetalle = dialog.findViewById(R.id.imagenDetalle);
        nombreCompletoDetalle = dialog.findViewById(R.id.nombreCompletoDetalle);

        nombreCompletoDetalle.setText(
                personaEntity.getName().getFirst() + " " + personaEntity.getName().getLast()
        );
        Glide.with(activity).load(personaEntity.getPicture().getLarge()).into(imagenDetalle);
        TextView close = dialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    @Override
    public void personaElegida(PersonaEntity persona) {
        showDialog(this,persona);
    }
}
