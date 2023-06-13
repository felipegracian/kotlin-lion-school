package br.senai.sp.jandira.lionschoolnew

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschoolnew.model.Aluno
import br.senai.sp.jandira.lionschoolnew.model.AlunoList
import br.senai.sp.jandira.lionschoolnew.model.Curso
import br.senai.sp.jandira.lionschoolnew.model.CursoList
import br.senai.sp.jandira.lionschoolnew.service.RetrofitFactory
import br.senai.sp.jandira.lionschoolnew.ui.theme.LionSchoolNewTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dado = intent.getStringExtra("sigla")
        setContent {
            LionSchoolNewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    StudentsAdminActivity(dado.toString())
                }
            }
        }
    }
}





@Composable
fun StudentsAdminActivity(sigla: String) {

    val context = LocalContext.current

    var listAluno by remember {
        mutableStateOf(listOf<(Aluno)>())
    }

    var nomeCurso by remember {
        mutableStateOf("")
    }

    val call = RetrofitFactory().getAlunoService().getAlunosByCurso(sigla)

    //Executar a chamada
    call.enqueue(object : Callback<AlunoList> {
        override fun onResponse(
            call: Call<AlunoList>,
            response: Response<AlunoList>
        ) {
            listAluno = response.body()!!.aluno
            nomeCurso = response.body()!!.NomeCurso

        }

        override fun onFailure(call: Call<AlunoList>, t: Throwable) {

        }

    })


        Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                onClick = { },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.White)

            ) {
                androidx.compose.material.Text(
                    text = "You are in",
                    fontWeight = FontWeight(800),
                    fontSize = 36.sp,
                    color = Color(51, 71, 176)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rectangle20),
                    contentDescription = "",
                    modifier = Modifier
                        .height(80.dp)
                        .width(80.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = nomeCurso,
                    color = Color(229, 182, 87),
                    fontWeight = FontWeight(700),
                    fontSize = 20.sp
                )
            }

            OutlinedTextField(

                value = "",
                modifier = Modifier.padding(start = 55.dp),
                onValueChange = {},
                label = {
                    androidx.compose.material.Text(
                        text = "find your student",
                        color = Color.DarkGray
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.baseline_search_24
                        ),
                        contentDescription = "",
                        modifier = Modifier.height(20.dp),
                        tint = Color.DarkGray
                    )
                }
            )
            Spacer(modifier = Modifier.height(50.dp))

            LazyColumn() {
                items(listAluno) {
                    Button(onClick = {
                        var StudentInThisListActivity = Intent(context, MyStudentActivity::class.java)
                        StudentInThisListActivity.putExtra("matricula", it.matricula)
                        context.startActivity(StudentInThisListActivity)
                    }){
                        androidx.compose.material.Card(
                            modifier = Modifier
                                .height(120.dp)
                                .width(400.dp)
                                .padding(
                                    start = 15.dp,
                                    end = 15.dp
                                ),
                            backgroundColor = Color(51, 71, 176),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = it.foto,
                                    contentDescription = "student photo",
                                    modifier = Modifier
                                        .height(100.dp)
                                        .width(80.dp)
                                )
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(
                                    text = it.nome,
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight(600)
                                )
                            }
                        }
                    }

                }
            }

        }
    }
}