package rs.ac.metropolitan.data

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rs.ac.metropolitan.common.DatingUser
import java.util.Date

class ApiServiceTest {
    private lateinit var mockServer: MockWebServer
    private lateinit var apiService: ApiService



    @Before
    fun setup() {
        mockServer = MockWebServer()
        apiService = Retrofit.Builder().baseUrl(mockServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)

    }

    @Test
    fun testGetStudents() = runBlocking{
        //Given
        val jsonResponse = """
            [
{"id":"1","fname":"Petar","lname":"Perić","avatar":"https://avatars.githubusercontent.com/u/6711895","email":"pera@yahoo.com","birthdate":"1970-04-23T22:10:44.931Z","sex":"male","studyYear":4,"startAt":"2019-01-24T15:08:00.272Z"},
{"id":"2","fname":"Ivka","lname":"Miković","avatar":"https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/1063.jpg","email":"ivka@yahoo.com","birthdate":"1986-09-08T03:44:52.320Z","sex":"female","studyYear":4,"startAt":"2020-01-06T01:35:30.949Z"},
{"id":"3","fname":"Jasmina","lname":"Andrić","avatar":"https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/73.jpg","email":"yasminka@yahoo.com","birthdate":"1960-04-26T10:17:27.151Z","sex":"female","studyYear":3,"startAt":"2021-09-22T07:29:10.166Z"},
{"id":"4","fname":"Krsta","lname":"Velićković","avatar":"https://avatars.githubusercontent.com/u/56308322","email":"krst.vel@yahoo.com","birthdate":"1986-11-28T03:38:58.993Z","sex":"male","studyYear":1,"startAt":"2014-11-29T16:17:22.323Z"},
{"id":"5","fname":"Toma","lname":"Petrović","avatar":"https://avatars.githubusercontent.com/u/17730130","email":"toma@yahoo.com","birthdate":"1983-11-16T02:00:57.517Z","sex":"male","studyYear":4,"startAt":"2016-02-23T19:48:27.069Z"}
]
                    """.trimIndent()

        mockServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(jsonResponse)
        )
        //When
        val result = apiService.getStudents()

        //Then
        assert(result.isNotEmpty())
        assertEquals(result[0].id, "1")
        assertEquals(result[0].fname, "Petar")
    }

    @Test
    fun testAddStudent() = runBlocking {
        //Given
        val student = DatingUser(
            id = "1",
            fname = "Peter",
            lname = "Petric",
            avatar = "https://avatars.githubusercontent.com/u/6711895",
            email = "pera@yahoo.com",
            birthdate = Date(),
            sex = "male",
            studyYear = "4",
            startAt = Date(),
        )
        val jsonResponse = """
            {"id":"1","avatar":"https://www.gravatar.com/avatar/406ab6f051ff8548e3fe74d0e8d65113?d=identicon&s=+50","birthdate":"2004-03-17T01:00:00.000Z","email":"yesmine@gmail.com","fname":"Jasminka","lname":"Vladic","sex":"Female","startAt":"2024-03-26T01:00:00.000Z","studyYear":"1"}
                    """.trimIndent()
        //When
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(jsonResponse)
        )
        val result = apiService.addStudent(student)
        //Then
        assert(result.id == "1")
        assert(result.fname == "Jasminka")
        assert(result.lname == "Vladic")
    }
}