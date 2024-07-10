package rs.ac.metropolitan.cs330_dz11_anteaprimorac5157.ui.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rs.ac.metropolitan.common.DatingUser
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    datingUserList: LiveData<List<DatingUser>>,
    loadDatingUsers: () -> Unit,
    goToNewDatingUser: () -> Unit,
    goToDetails: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Dating Users") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { goToNewDatingUser() }) {
                Icon(Icons.Filled.Add, contentDescription = "Add dating user")
            }
        }
    ) { innerPadding ->
        DatingUserListPage(
            datingUserList = datingUserList,
            paddingValues = innerPadding,
            loadDatingUsers = loadDatingUsers,
            goToDetails = goToDetails
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
        HomeScreen(
            datingUserList = MutableLiveData(
                listOf(
                    DatingUser(
                        "1",
                        "LarryLaffer",
                        "https://www.behindthevoiceactors.com/_img/chars/larry-laffer-leisure-suit-larry-6-shape-up-or-slip-out-43.1.jpg",
                        "Miami",
                        "USA",
                        "Male",
                        "Female",
                        "Looking for love in all the wrong places!",
                    ),
                    DatingUser(
                        "2",
                        "Monokuma",
                        "https://static.wikia.nocookie.net/danganronpa/images/2/2a/Danganronpa_1_Monokuma_Halfbody_Sprite_14.png",
                        "???",
                        "Japan",
                        "Male",
                        "Female",
                        "Unbearable.",
                    ))),
            loadDatingUsers = {},
            goToNewDatingUser = {},
            goToDetails = {}
        )
}