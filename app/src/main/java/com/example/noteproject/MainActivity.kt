package com.example.noteproject

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteproject.ui.theme.NoteprojectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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

    var editNoteText by remember { mutableStateOf("") }

    var editingIndex by remember { mutableStateOf<Int?>(null) }

    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
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
            OutlinedButton(
                onClick = {
                    if (todoName.isEmpty()) {
                        return@OutlinedButton
                    }
                    itemList.add(todoName)
                    todoName = ""
                },
                shape = RoundedCornerShape(8.dp),
            ) {
                Text("Add")
            }


        }
        LazyColumn(
            modifier = modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(count = itemList.size) { index ->
                Card(
                    Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(width = 1.dp, Color.Gray)
                ) {

                    if (editingIndex == index) {
                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(Modifier.height(8.dp))
                            OutlinedTextField(
                                value = editNoteText,
                                onValueChange = { editNoteText = it },
                                label = { Text("Edit Todo") },
                                singleLine = true,

                                )
                            Spacer(Modifier.height(8.dp))
                            Row() {
                                OutlinedButton(
                                    onClick = {
                                        itemList[index] = editNoteText
                                        editingIndex = null

                                    },
                                    shape = RoundedCornerShape(8.dp),
                                ) {
                                    Text("done")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                OutlinedButton(
                                    onClick = {
                                        editingIndex = null

                                    },
                                    shape = RoundedCornerShape(8.dp),
                                ) {
                                    Text("cancel")
                                }


                            }
                            Spacer(Modifier.height(8.dp))
                        }
                    } else {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                itemList[index], fontSize = 20.sp,
                                modifier = Modifier.weight(1f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )


                            IconButton(onClick = {
                                editingIndex = index
                                editNoteText = itemList[index]

                            }) {
                                Icon(Icons.Outlined.Edit, contentDescription = "edit")
                            }

                            IconButton(onClick = {
                                itemList.removeAt(index)

                            }) {
                                Icon(Icons.Outlined.Delete, contentDescription = "delete")
                            }
                        }
                    }


                }
            }
        }
    }
}

