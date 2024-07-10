package rs.ac.metropolitan.common

import java.net.URL
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

class Common {
    companion object{
        fun generateAvatarImage(name: String): URL {
            val digest = java.security.MessageDigest.getInstance("MD5")
                .digest(name.toByteArray())
                .joinToString(separator = "") {
                    String.format("%02x", it)
                }
            val url = "https://www.gravatar.com/avatar/${digest}?d=identicon&s=+50"
            return URL(url)
        }
    }
}