package rs.ac.metropolitan.cs330_dz11_anteaprimorac5157.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
    DatingUserBasicData(datingUser = datingUser, goBack = goBack, delete = deleteDatingUser)
}

@Composable
fun DatingUserBasicData(datingUser: DatingUser?, goBack: () -> Unit, delete: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
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
            Text(text = "User details", style = MaterialTheme.typography.titleLarge, modifier = Modifier.align(Alignment.Center))

            IconButton(
                modifier = Modifier
                    .scale(1.5f)
                    .align(Alignment.BottomEnd),
                onClick = { delete() }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
        datingUser?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                AsyncImage(
                    model = it.avatar,
                    contentDescription = null,
                    modifier = Modifier
                        .size(240.dp)
                        .clip(CircleShape)
                )
                Text(text = it.username, style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 16.dp))
                Text(
                    text = "Location: ${it.city}, ${it.country}",
                    color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = it.sex,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Interested in: ${it.interestedIn}",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f))
                        .padding(16.dp)
                        .clip(MaterialTheme.shapes.small)
                    ) {
                        Text(
                            text = "Description",
                            color = MaterialTheme.colorScheme.onTertiary,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = it.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onTertiary,
                        )
                    }
                }
            }
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