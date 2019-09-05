package com.example.testmachinelearning;

import com.example.testmachinelearning.Entity.P;
import com.example.testmachinelearning.Service.ServiceApi;
import com.example.testmachinelearning.Service.ServiceRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonasInteractor implements PersonasInterface.interactor {

    private PersonasInterface.presenter presenter;

    public PersonasInteractor(PersonasInterface.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void pedirPersonasRetrofit(String gender) {
        ServiceApi serviceApi = ServiceRetrofit.getInstance().create(ServiceApi.class);
        final Call<P> personas = serviceApi.listPersonas(gender,50);
        personas.enqueue(new Callback<P>() {
            @Override
            public void onResponse(Call<P> call, Response<P> response) {
                if(response.isSuccessful()){
                    presenter.onResponsePersonas(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<P> call, Throwable t) {
                presenter.onResponseError();
            }
        });
    }
}
