package api

import android.provider.ContactsContract
import models.DefaultResponce
import models.LoginResponce
import retrofit2.Call
import  retrofit2.Retrofit
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded

interface Api {


      @FormUrlEncoded
      @POST("createuser")
      fun createUser(
         @Field("email") email:String,
         @Field("password") password:String,
         @Field("name") name:String,
         @Field("school") school:String

      ):Call<DefaultResponce>


@FormUrlEncoded
@POST("userlogin")
  fun   userLogin(

    @Field("email") email: String,
    @Field("password") password: String
):Call<LoginResponce>


}