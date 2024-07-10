package rs.ac.metropolitan.common

data class DatingUser(
    val id: String,
    val username: String,
    val avatar: String,
    val city: String,
    val country: String,
    val sex: String,
    val interestedIn: String,
    val description: String
)