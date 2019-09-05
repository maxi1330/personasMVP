package com.example.testmachinelearning;

import com.example.testmachinelearning.Entity.PersonaEntity;

import java.util.List;

public interface PersonasInterface {

    interface view{
        void mostrarPersonas(List<PersonaEntity> listaPersonas);
        void mostrarMensajeError(String mensaje);

    }

    interface presenter{
        void pedirPersonas(String gender);

        void onResponsePersonas(List<PersonaEntity> listaPersonas);
        void onResponseError();
    }

    interface interactor{
        void pedirPersonasRetrofit(String gender);
    }
}
