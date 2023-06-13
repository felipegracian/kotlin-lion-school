package br.senai.sp.jandira.lionschoolnew

import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschoolnew.model.Student
import br.senai.sp.jandira.lionschoolnew.service.RetrofitFactory
import br.senai.sp.jandira.lionschoolnew.ui.theme.LionSchoolNewTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyStudentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.getStringExtra("matricula")
        setContent {
            LionSchoolNewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MyStudentViewNotes(data.toString())
                }
            }
        }
    }
}





@Composable
fun MyStudentViewNotes(matricula: String) {

    val context = LocalContext.current

    var studentInfo by remember {
        mutableStateOf(Student(
            "",
            "",
            "",
            "",
            emptyList()
        ))
    }



// Check if the list is null before invoking the size() method



    val call = RetrofitFactory().getStudentService().getAlunosByMatricula(matricula)

    call.enqueue(object : Callback<Student> {
        override fun onResponse(
            call: Call<Student>,
            response: Response<Student>
        ) {
             val studentResponse = response.body()
            if (studentResponse != null) {
                studentInfo = studentResponse
            }
        }

        override fun onFailure(call: Call<Student>, t: Throwable) {
            Log.i("ERRO MEU", "onFailure: Sua call deu errado MIKE TYSOOOOOOOOOOOOOOOOOOOON")
        }

    })

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(color = Color(51, 71, 176)),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 15.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_image),
                    contentDescription = null,
                    Modifier.height(75.dp),
                    contentScale = ContentScale.FillHeight
                )

                Spacer(modifier = Modifier.width(15.dp))

                androidx.compose.material.Text(
                    text = "Lion School",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight(700)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            androidx.compose.material.Text(
                text = "Relatório do Aluno",
                color = Color(51, 71, 176),
                fontSize = 35.sp,
                fontWeight = FontWeight(700)
            )
        }

        androidx.compose.material.Card(
            modifier = Modifier
                .width(400.dp)
                .height(500.dp)
                .padding(start = 10.dp, end = 10.dp, top = 20.dp),
            backgroundColor = Color(217, 217, 217),
            shape = RoundedCornerShape(25.dp)
        ) {
            Row(
                modifier = Modifier
                    .height(60.dp)
                    .width(500.dp)
                    .padding(start = 10.dp, top = 10.dp),
                verticalAlignment = Alignment.Top
            ) {
                AsyncImage(
                    model = studentInfo.foto,
                    contentDescription = "student photo",
                    modifier = Modifier.height(80.dp),
                    contentScale = ContentScale.FillHeight
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = studentInfo.nome,
                    color = Color(51, 71, 176),
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    modifier = Modifier.padding(top = 25.dp)
                )
            }

            Column(
                modifier = Modifier
                    .width(400.dp)
                    .height(170.dp)
                    .padding(top = 100.dp, start = 20.dp)
            ) {
                LazyColumn() {
                    items(studentInfo.disciplinas) {
                        Column(
                            modifier = Modifier
                                .width(500.dp)
                                .height(50.dp)
                        ) {
                            Text(
                                text = it.sigla,
                                modifier = Modifier.padding(start = 3.dp),
                                color = Color(51, 71, 176),
                                fontWeight = FontWeight(500)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Surface(
                                color = Color(217, 217, 217)
                            ) {


                                Card(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(275.dp),
                                    backgroundColor = Color.White
                                ) {

                                }

                                Card(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(250.dp),
                                    backgroundColor = Color(51, 71, 176)
                                ) {

                                }

                                Text(
                                    text = it.media,
                                    modifier = Modifier.padding(start = 285.dp),
                                    color = Color(51, 71, 176),
                                    fontWeight = FontWeight(500)
                                )

                            }

                        }
                    }

                }
            }


        }
    }
}