package rs.ac.metropolitan.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import rs.ac.metropolitan.common.DatingUser
import rs.ac.metropolitan.data.ApiService
import rs.ac.metropolitan.data.RetrofitHelper

class Repository {
    var datingUsersFlow: Flow<List<DatingUser>> = flowOf(listOf())

    suspend fun loadDatingUsers() {
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        val result = apiService.getDatingUsers()
        if (result != null)
            // Checking the results
            datingUsersFlow = flowOf(result)
    }

    suspend fun submitDatingUser(datingUser: DatingUser) {
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val result = apiService.addDatingUser(datingUser)
    }

    suspend fun deleteDatingUser(id: String) {
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val result = apiService.deleteDatingUser(id)
    }
}