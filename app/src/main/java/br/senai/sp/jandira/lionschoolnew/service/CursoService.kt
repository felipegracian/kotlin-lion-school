package br.senai.sp.jandira.lionschoolnew.service

import br.senai.sp.jandira.lionschoolnew.model.CursoList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CursoService {

    @GET("cursos")
    fun getCharacters(): Call<CursoList>

}