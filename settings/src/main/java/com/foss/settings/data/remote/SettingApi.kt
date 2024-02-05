package com.foss.auth_data.source.remote


import com.foss.settings.data.models.CreateUpdateProfileResponseDTO
import com.foss.settings.data.models.GetuserProfileResponseDTO
import com.foss.settings.data.models.UploadProfileImageResponseDTO
import com.foss.settings.domain.models.CreateUpdateProfileRequestParams
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 *
 * @Description: Interface representing the Auth API endpoints.
 *
 *
 * @Author: Abhishek Kumar
 * Created On: 14-12-2023
 */
interface SettingApi {

    @POST("user/create-update")
    suspend fun createUpdateProfile(
        @Body requestParamsMode: CreateUpdateProfileRequestParams
    ): CreateUpdateProfileResponseDTO

    @GET("user")
    suspend fun getUserProfile(
    ): GetuserProfileResponseDTO

    @Multipart
    @POST("user/upload/profileImage")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part,
    ): UploadProfileImageResponseDTO

}