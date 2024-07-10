package rs.ac.metropolitan.data

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import rs.ac.metropolitan.common.DatingUser

interface ApiService {
    @GET(Constants.DATING_USER_URL)
    suspend fun getDatingUsers(): List<DatingUser>

    @POST(Constants.DATING_USER_URL)
    suspend fun addDatingUser(@Body datingUser: DatingUser): DatingUser

    @DELETE(Constants.DATING_USER_URL+"/{id}")
    suspend fun deleteDatingUser(@Path("id") id: String)
}