package br.senai.sp.jandira.lionschoolnew.model

import android.telecom.Call

data class Student(
    val foto: String,
    val nome: String,
    val matricula: String,
    val nomeCurso: String,
    val disciplinas: List<DisciplinaList>
)
