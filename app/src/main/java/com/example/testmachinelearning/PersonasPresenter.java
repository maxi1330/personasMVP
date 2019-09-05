package com.example.testmachinelearning;

import com.example.testmachinelearning.Entity.PersonaEntity;

import java.util.List;

public class PersonasPresenter implements PersonasInterface.presenter {

    private PersonasInterface.view view;
    private PersonasInterface.interactor interactor;

    public PersonasPresenter(PersonasInterface.view view) {
        this.view = view;
        interactor = new PersonasInteractor(this);
    }

    @Override
    public void pedirPersonas(String gender) {
        interactor.pedirPersonasRetrofit(gender);
    }

    @Override
    public void onResponsePersonas(List<PersonaEntity> listaPersonas) {
        if(!listaPersonas.isEmpty()){
            view.mostrarPersonas(listaPersonas);
        } else {
            view.mostrarMensajeError("No se encontraron Personas!");
        }
    }

    @Override
    public void onResponseError() {
        view.mostrarMensajeError("Error, vuelva a intentarlo mas tarde!");
    }
}
