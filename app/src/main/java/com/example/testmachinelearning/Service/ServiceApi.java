package com.example.testmachinelearning.Service;

import com.example.testmachinelearning.Entity.P;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {

    @GET("api/?")
    Call<P> listPersonas(@Query("gender") String gender, @Query("results") Integer cantidadPersonas);

}
