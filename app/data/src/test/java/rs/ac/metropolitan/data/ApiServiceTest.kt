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
    fun testGetDatingUsers() = runBlocking {
        // Given
        val jsonResponse = """
            [
              {
                "id": "fe182abe-80a0-4d39-8a48-88da55de84e3",
                "username": "Lafayette.Jaskolski53",
                "avatar": "https://i.pravatar.cc/300?u=ac4ded46-1a20-4824-836f-2440b00ac50d",
                "city": "Port Ethacester",
                "country": "Samoa",
                "sex": "Female",
                "interestedIn": "Female",
                "description": "Voluptatum iusto cursus veritas. Complectus amo apostolus crur cruciamentum aliquam. Defleo calcar taceo thema allatus."
              },
              {
                "id": "b42414e1-0224-4610-b48f-59acdf9171ce",
                "username": "Jamel15",
                "avatar": "https://i.pravatar.cc/300?u=48eafe68-4263-405c-a0f5-b76408bb85e8",
                "city": "Marvinburgh",
                "country": "Comoros",
                "sex": "Male",
                "interestedIn": "Female",
                "description": "Apud thermae facilis antiquus audeo terra super astrum conforto. Vulticulus ventus vix defungo occaecati celo pectus tondeo repudiandae. Sollicito desino patior caste tempora vitae blanditiis amissio cognomen conitor."
              }
            ]
        """.trimIndent()

        mockServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(jsonResponse)
        )

        // When
        val result = apiService.getDatingUsers()

        // Then
        assert(result.isNotEmpty())
        assertEquals(result[0].id, "fe182abe-80a0-4d39-8a48-88da55de84e3")
        assertEquals(result[0].username, "Lafayette.Jaskolski53")
        assertEquals(result[0].city, "Port Ethacester")
        assertEquals(result[0].country, "Samoa")
        assertEquals(result[0].sex, "Female")
        assertEquals(result[0].interestedIn, "Female")
    }

    @Test
    fun testAddDatingUser() = runBlocking {
        // Given
        val datingUser = DatingUser(
            id = "test-id",
            username = "testuser",
            avatar = "https://i.pravatar.cc/300?u=testuser",
            city = "Test City",
            country = "Test Country",
            sex = "Male",
            interestedIn = "Female",
            description = "Test description"
        )
        val jsonResponse = """
            {
              "id": "test-id",
              "username": "testuser",
              "avatar": "https://i.pravatar.cc/300?u=testuser",
              "city": "Test City",
              "country": "Test Country",
              "sex": "Male",
              "interestedIn": "Female",
              "description": "Test description"
            }
        """.trimIndent()

        // When
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(jsonResponse)
        )
        val result = apiService.addDatingUser(datingUser)

        // Then
        assertEquals(result.id, "test-id")
        assertEquals(result.username, "testuser")
        assertEquals(result.city, "Test City")
        assertEquals(result.country, "Test Country")
        assertEquals(result.sex, "Male")
        assertEquals(result.interestedIn, "Female")
    }
}