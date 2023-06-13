package br.senai.sp.jandira.lionschoolnew.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitFactory {
    private val BASE_URL = "https://tired-slug-hat.cyclic.app/v1/lion-school/"

    private val BASE_URL2 = "https://api-lion-school-2023.cyclic.app/v1/lion-school/"

    private val retrofitFactory =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val retrofitFactory2 = Retrofit
        .Builder()
        .baseUrl(BASE_URL2)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCursoService(): CursoService{
        return retrofitFactory.create(CursoService::class.java)
    }

    fun getAlunoService(): AlunoService{
        return  retrofitFactory.create(AlunoService::class.java)
    }

    fun getStudentService(): StudentService{
        return retrofitFactory2.create(StudentService::class.java)
    }


}