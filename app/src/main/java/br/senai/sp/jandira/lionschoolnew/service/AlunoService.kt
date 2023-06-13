package br.senai.sp.jandira.lionschoolnew.service

import br.senai.sp.jandira.lionschoolnew.model.AlunoList
import br.senai.sp.jandira.lionschoolnew.model.CursoList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AlunoService {
    @GET("alunos")
    fun getAlunosByCurso(
        @Query("curso") curso: String
    ): Call<AlunoList>
}