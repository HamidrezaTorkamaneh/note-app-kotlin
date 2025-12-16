package com.example.noteproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.magnifier
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteproject.ui.theme.NoteprojectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

               Scaffold (modifier = Modifier.fillMaxSize()) { innerPadding ->
                   MainScreen(modifier = Modifier.padding(innerPadding))
               }


        }
    }
}


@Preview
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var itemList = remember {
        mutableStateListOf("do this job", "do that job", "do other job")
    }
    var todoName by remember { mutableStateOf("") }
    Column(modifier= modifier.fillMaxSize()) {
        Row(
            modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = todoName,
                onValueChange = { todoName = it },
                label = { Text("Enter Todo") },
                placeholder = { Text("job...") },
                singleLine = true,
            )
            OutlinedButton(onClick = {
                if (todoName.isEmpty()){
                    return@OutlinedButton
                }
                itemList.add(todoName)
                todoName = ""
            }) {
                Text("Add")
            }


        }
        LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
            items(count = itemList.size){
                index -> Text(itemList[index])
            }
        }
    }
}

