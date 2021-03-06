package activities

import Storage.SharedPrefManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import api.RetrofitClient
import com.example.sample.R
import kotlinx.android.synthetic.main.activity_login_activities.*
import kotlinx.android.synthetic.main.activity_login_activities.editTextEmail
import kotlinx.android.synthetic.main.activity_login_activities.editTextPassword
import kotlinx.android.synthetic.main.activity_main.*
import models.LoginResponce
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivities : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activities)

        buttonLogin.setOnClickListener {

            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (email.isEmpty()){

                editTextEmail.error = "Email is Required"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){

                editTextPassword.error = "password is Required"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instances.userLogin(email, password)
                .enqueue(object: Callback<LoginResponce> {
                    override fun onFailure(call: Call<LoginResponce>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<LoginResponce>,
                        response: Response<LoginResponce>
                    ) {
                        if (!response.body()?.error!!){
                       SharedPrefManager.getInstance(applicationContext).saveUser(response.body()?.user!!)

                            val intent = Intent(applicationContext, ProfileActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                            startActivity(intent)
                        }else{

                            Toast.makeText(applicationContext, response.body()?.message,Toast.LENGTH_LONG).show()
                        }
                    }


                })
        }
    }


}
