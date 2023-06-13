package br.senai.sp.jandira.lionschoolnew

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschoolnew.model.Curso
import br.senai.sp.jandira.lionschoolnew.model.CursoList
import br.senai.sp.jandira.lionschoolnew.service.RetrofitFactory
import br.senai.sp.jandira.lionschoolnew.ui.theme.LionSchoolNewTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoursesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolNewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CoursesAdminActivity()
                }
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun CoursesAdminActivity() {

    val context = LocalContext.current

    var listCurso by remember {
        mutableStateOf(listOf<(Curso)>())
    }

    val call = RetrofitFactory().getCursoService().getCharacters()

    //Executar a chamada
    call.enqueue(object : Callback<CursoList> {
        override fun onResponse(
            call: Call<CursoList>,
            response: Response<CursoList>
        ) {
            listCurso = response.body()!!.cursos


        }

        override fun onFailure(call: Call<CursoList>, t: Throwable) {

        }

    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(51, 71, 176))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .height(107.dp)
                    .fillMaxWidth()
                    .padding(top = 7.dp),
                colors = ButtonDefaults.buttonColors(Color.White)

            ) {
                Text(
                    text = "Choose the course you want to work",
                    fontWeight = FontWeight(800),
                    fontSize = 24.sp,
                    color = Color(51, 71, 176)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(

                    value = "",
                    onValueChange = {},
                    label = {
                        Text(
                            text = "find your course",
                            color = Color.White
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.baseline_search_24
                            ),
                            contentDescription = "",
                            modifier = Modifier.height(20.dp),
                            tint = Color.White
                        )
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(start = 15.dp, top = 30.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Image(
                    painter = painterResource(id = R.drawable.rectangle21),
                    contentDescription = null,
                    Modifier.height(30.dp),
                    contentScale = ContentScale.FillHeight
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = "Courses", color = Color(229, 182, 87), fontSize = 32.sp,
                    fontWeight = FontWeight(700)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LazyColumn() {
                    items(listCurso) {
                        Button(onClick = {
                            var StudentsInThisCourseActivity = Intent(context, StudentsActivity::class.java)
                            StudentsInThisCourseActivity.putExtra("sigla", it.sigla)
                            context.startActivity(StudentsInThisCourseActivity)
                        }) {
                            Card(
                                modifier = Modifier
                                    .size(height = 350.dp, width = 300.dp)
                                    .padding(start = 15.dp, top = 30.dp),
                                shape = RoundedCornerShape(20.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                ) {
                                    AsyncImage(
                                        model = it.icone,
                                        contentDescription = "Character avatar",
                                        modifier = Modifier
                                            .clip(shape = CircleShape)
                                            .height(100.dp))

                                    Spacer(modifier = Modifier.width(40.dp))
                                    Text(
                                        text = it.sigla,
                                        color = Color(229, 182, 87),
                                        fontSize = 32.sp,
                                        fontWeight = FontWeight(700),
                                        modifier = Modifier.padding(top = 40.dp)
                                    )
                                }

                                Text(
                                    text = it.nome,
                                    color = Color(51, 71, 176),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(700),
                                    modifier = Modifier.padding(top = 110.dp, start = 10.dp)
                                )

                                Text(
                                    text = "Resumo e apresentão do curso.",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(300),
                                    modifier = Modifier.padding(top = 160.dp, start = 10.dp)
                                )

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(30.dp)
                                        .padding(top = 215.dp, start = 11.8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Card(
                                        modifier = Modifier.size(height = 40.dp, width = 40.dp),
                                        backgroundColor = Color(229, 182, 87),
                                        shape = RoundedCornerShape(50.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.yellowwatch1),
                                            contentDescription = null,
                                            contentScale = ContentScale.FillHeight
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = "Carga Horária: " + it.carga,
                                        color = Color.Black,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(300)
                                    )
                                }
                            }
                        }



                    }


                }


            }
        }
    }
}