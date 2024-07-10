package rs.ac.metropolitan.cs330_dz11_anteaprimorac5157.ui.view

import SegmentedControl
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rs.ac.metropolitan.common.Common
import rs.ac.metropolitan.common.DatingUser
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewDatingUserScreen(
    paddingValues: PaddingValues = PaddingValues(16.dp),
    submitDatingUser: (DatingUser) -> Unit,
    goBack: () -> Unit,
) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var city by remember { mutableStateOf(TextFieldValue("")) }
    var country by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var selectedSex by rememberSaveable { mutableStateOf("") }
    var selectedInterestedIn by rememberSaveable { mutableStateOf("") }
    val genders = listOf("Male", "Female")

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .scale(1.5f),
                        onClick = { goBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = "New Dating User", style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            item {
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    placeholder = { Text("Enter your username") },
                )
            }
            item {
                TextField(
                    value = city,
                    onValueChange = { city = it },
                    label = { Text("City") },
                    placeholder = { Text("Enter your city") },
                )
            }
            item {
                TextField(
                    value = country,
                    onValueChange = { country = it },
                    label = { Text("Country") },
                    placeholder = { Text("Enter your country") },
                )
            }
            item {
                Text("Sex", style = MaterialTheme.typography.titleMedium)
                SegmentedControl(
                    items = genders,
                    defaultSelectedItemIndex = 0,
                ) { index ->
                    selectedSex = if (index == 0) "Male" else "Female"
                }
            }
            item {
                Text("Interested In", style = MaterialTheme.typography.titleMedium)
                SegmentedControl(
                    items = genders,
                    defaultSelectedItemIndex = 0,
                ) { index ->
                    selectedInterestedIn = if (index == 0) "Male" else "Female"
                }
            }
            item {
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    placeholder = { Text("Enter your description") },
                    modifier = Modifier.height(100.dp)
                )
            }
            item {
                Button(onClick = {
                    submitDatingUser(
                        DatingUser(
                            id = UUID.randomUUID().toString(),
                            username = username.text,
                            avatar = Common.generateAvatarImage(username.text).toString(),
                            city = city.text,
                            country = country.text,
                            sex = selectedSex,
                            interestedIn = selectedInterestedIn,
                            description = description.text
                        )
                    )
                    goBack()
                }) {
                    Text(text = "Submit")
                }
            }
        }
    }
}

@Preview
@Composable
fun NewDatingUserScreenPreview() {
    NewDatingUserScreen(
        paddingValues = PaddingValues(0.dp),
        submitDatingUser = {},
        goBack = {}
    )
}