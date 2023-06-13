package br.senai.sp.jandira.lionschoolnew

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschoolnew.ui.theme.LionSchoolNewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolNewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    FirstActivity()
                }
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun FirstActivity() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(51, 71, 176))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "Welcome",
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight(700)
            )

            Row(
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_image),
                    contentDescription = null,
                    Modifier.height(250.dp),
                    contentScale = ContentScale.FillHeight
                )
            }

            Row() {
                Text(
                    text = "Lion School",
                    color = Color.White,
                    fontSize = 48.sp,
                    fontWeight = FontWeight(700)
                )
            }

            Row() {
                Button(onClick = { var openCurses = Intent(context, CoursesActivity::class.java)
                    context.startActivity(openCurses) },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .height(50.dp)
                        .width(150.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color(229, 182, 87)
                    )
                ) {
                    Text(
                        text = "Get Started",
                        fontWeight = FontWeight(300),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

