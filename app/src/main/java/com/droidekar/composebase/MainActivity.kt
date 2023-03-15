package com.droidekar.composebase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidekar.composebase.ui.theme.ComposeBaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp() {
                //Greeting("Android")
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeBaseTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MyScreenContent()
        }
    }
}

@Composable
fun topBar() {
    TopAppBar(title = {
        Text(
            text = "My Compose App",
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    })
}

@Composable
fun Greeting(name: String) {

    Text(text = "Hello $name!",
        modifier = Modifier
            .padding(14.dp)
    )
}

@Composable
fun MessageCard(msg: String) {
    // Add padding around our message
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.baseline_access_alarm_24),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = "Aloha")
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(100) {"hello $it"}) {
    Column(modifier = Modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally) {

        val ctx = LocalContext.current.applicationContext

        TopAppBar(
            title = {
                Text("My Compose App")
            },
            navigationIcon = {
                IconButton(onClick = {
                    Toast.makeText(ctx, "Navigation Icon Click", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Navigation icon")
                }
            }
        )

        nameList(names = names, modifier = Modifier.weight(1f))

        // tracks state change
        var counterState by remember {
           mutableStateOf(0)
        }

        counter (
            counterState,
            updateCount = { newCount
                -> counterState = newCount
            }
        )

        if (counterState > 5) {
            Text("Count is high")
        }

    }
}

@Composable
fun nameList(names: List<String>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) {
            //Greeting(name = it)
            MessageCard(msg = it)
            Divider(color = Color.Blue, thickness = 2.dp)
        }
    }
}

@Composable
fun counter(count: Int, updateCount: (Int) -> Unit) {
    /*var counter by remember {
        mutableStateOf(0)
    }*/
    Button(onClick = { updateCount(count+1) }) {
        Text(text = "button clicked $count times", color= Color.Cyan,
        modifier = Modifier
            .padding(10.dp),
        )

    }
}