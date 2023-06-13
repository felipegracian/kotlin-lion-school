package br.senai.sp.jandira.lionschoolnew.service

import br.senai.sp.jandira.lionschoolnew.model.AlunoList
import br.senai.sp.jandira.lionschoolnew.model.Student
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StudentService {
    @GET("alunos/{matricula}")
    fun getAlunosByMatricula(@Path("matricula") matricula: String): Call<Student>
}