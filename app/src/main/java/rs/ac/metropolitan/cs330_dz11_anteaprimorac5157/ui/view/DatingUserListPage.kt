package rs.ac.metropolitan.cs330_dz11_anteaprimorac5157.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.compose.AsyncImage
import rs.ac.metropolitan.common.DatingUser

@Composable
fun DatingUserListPage(
    datingUserList: LiveData<List<DatingUser>>,
    paddingValues: PaddingValues,
    loadDatingUsers: () -> Unit,
    goToDetails: (String) -> Unit
) {
    val datingUsers = datingUserList.observeAsState()

    LaunchedEffect(Unit) {
        loadDatingUsers()
    }

    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        datingUsers.value?.let {
            items(it) { datingUser ->
                DatingUserCardView(datingUser) {
                    goToDetails(it)
                }
            }
        }
    }
}

@Composable
fun DatingUserCardView(datingUser: DatingUser, onSelected: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onSelected(datingUser.id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = datingUser.avatar,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = datingUser.username,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${datingUser.city}, ${datingUser.country}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                text = datingUser.sex,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun ListStudentViewPreview() {
    DatingUserListPage(
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
        paddingValues = PaddingValues(8.dp),
        loadDatingUsers = {},
        goToDetails = {}
    )
}