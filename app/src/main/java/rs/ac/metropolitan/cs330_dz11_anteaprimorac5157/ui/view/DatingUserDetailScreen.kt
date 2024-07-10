package rs.ac.metropolitan.cs330_dz11_anteaprimorac5157.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import rs.ac.metropolitan.common.DatingUser

@Composable
fun DatingUserDetailScreen(
    datingUser: DatingUser?,
    goBack: () -> Unit,
    deleteDatingUser: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TopBar(goBack, deleteDatingUser)
                if (datingUser != null) {
                    LazyColumn {
                        item { UserInfo(datingUser) }
                        item { UserInterestBox(datingUser.sex, datingUser.interestedIn) }
                        item { UserDescription(datingUser.description) }
                    }
                }
            }
        }
    }
}

@Composable
fun TopBar(goBack: () -> Unit, delete: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .scale(1.5f),
            onClick = goBack
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            text = "User details",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.Center)
        )
        IconButton(
            modifier = Modifier
                .scale(1.5f)
                .align(Alignment.CenterEnd),
            onClick = delete
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun UserInfo(user: DatingUser) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = user.avatar,
            contentDescription = null,
            modifier = Modifier
                .size(240.dp)
                .clip(CircleShape)
        )
        Text(
            text = user.username,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "Location: ${user.city}, ${user.country}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun UserInterestBox(sex: String, interestedIn: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = sex,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Text(
            text = "Interested in: $interestedIn",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
    }
}

@Composable
fun UserDescription(description: String) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .padding(16.dp)
        ) {
            Text(
                text = "Description",
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
            )
        }
    }
}

@Preview
@Composable
fun DatingUserDetailScreenPreview() {
        DatingUserDetailScreen(
            datingUser = DatingUser(
                        "1",
                        "LarryLaffer",
                        "https://www.behindthevoiceactors.com/_img/chars/larry-laffer-leisure-suit-larry-6-shape-up-or-slip-out-43.1.jpg",
                        "Miami",
                        "USA",
                        "Male",
                        "Female",
                        "Looking for love in all the wrong places!",
                    ),
            goBack = {},
            deleteDatingUser = {}
        )
}