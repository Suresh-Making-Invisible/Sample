package activities

import Storage.SharedPrefManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import api.RetrofitClient
import com.example.sample.R
import kotlinx.android.synthetic.main.activity_main.*
import models.DefaultResponce
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        textViewLogin.setOnClickListener {

            startActivity(Intent(this@MainActivity,LoginActivities::class.java))
        }
        buttonSignUp.setOnClickListener {

            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val name = editTextName.text.toString().trim()
            val school = editTextSchool.text.toString().trim()



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
            if (name.isEmpty()){

                editTextName.error = "Name is Required"
                editTextName.requestFocus()
                return@setOnClickListener
            }
            if (school.isEmpty()){

                editTextSchool.error = "School is Required"
                editTextSchool.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instances.createUser(email, password, name, school)
                .enqueue(object:Callback<DefaultResponce>{
                    override fun onFailure(call: Call<DefaultResponce>, t: Throwable) {
                     Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<DefaultResponce>,
                        response: Response<DefaultResponce>
                    ) {
                        Toast.makeText(applicationContext,response.body()?.message,Toast.LENGTH_LONG).show()
                    }


                })
        }
    }


}
