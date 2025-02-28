package com.example.ecomproject.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecomproject.R
import com.example.ecomproject.clients.ApiClient
import com.example.ecomproject.databinding.ActivityRegisterBinding
import com.example.ecomproject.interfaces.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity(),CompoundButton.OnCheckedChangeListener{

    private lateinit var binding: ActivityRegisterBinding
    private var gender1 = ""
    lateinit var apiInterface: ApiInterface

    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    var mobilePattern = "[0-9]"
    var alphabetpattern = "[a-zA-Z]+"
    var passpattern : String =  ("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (Build.VERSION.SDK_INT >= 21 )
        {
            val window = this.window

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            window.statusBarColor = this.resources.getColor(R.color.black)
        }

        binding.rbMale.setOnCheckedChangeListener(this)
        binding.rbFemale.setOnCheckedChangeListener(this)

        binding.btnsignup.setOnClickListener{

            val fname = binding.edtFirstName.text.toString()
            val lname = binding.edtLastName.text.toString()
            val email = binding.edtEmail.text.toString()
            val phone = binding.edtPhone.text.toString()
            val pass = binding.edtPassword.text.toString()
            val gender = gender1

            if (pass.isEmpty())
            {
                Toast.makeText(applicationContext, "Plase enter propper password ", Toast.LENGTH_SHORT).show()
            }
            else{

                if (fname.isEmpty())
                {
                    binding.edtFirstName.error = "Please enter propper firstname "
                }
                else if(!isalphabet(fname))
                {
                    binding.edtFirstName.error = "Please Enter characer only "
                }
                else if(lname.isEmpty())
                {
                    binding.edtLastName.error = "Please Enter Proper Lastname "
                }
                else if(!isalphabet(lname))
                {
                    binding.edtLastName.error = "Please enter Character Only"
                }
                else if (email.isEmpty())
                {
                    binding.edtEmail.error = "please Enter Email"
                }
                else if (!isValidEmail(email))
                {
                    binding.edtEmail.error = "Please ENter Propper Email "
                }
                else if (phone.length != 10)
                {
                    binding.edtPhone.error = "Please Enter Proper Phone Number"
                }
                else if (isnum(phone))
                {
                    binding.edtPhone.error = "Please Enter Proper Phone Number"
                }
                else if (pass.isEmpty())
                {
                    binding.edtPassword.error = "Please Enter Password"
                }
                else if(!ispass(pass))
                {
                    binding.edtPassword.error = "Please Enter Proper Password"
                }
                else{

                    apiInterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)

                    val registercall : Call<Void> = apiInterface.registerdetail(fname,lname,gender,email,phone,pass)
                    registercall.enqueue(object :Callback<Void>
                    {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            startActivity(Intent(applicationContext,LoginActivity::class.java))
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
                        }

                    }

                    )


                }

            }

            binding.textView3.setOnClickListener{
                startActivity(Intent(applicationContext,RegisterActivity::class.java))
            }

        }

    }

    fun isValidEmail(email: String): Boolean
    {
        return email.matches(emailPattern.toRegex())
    }
    fun isalphabet(alpha: String): Boolean
    {
        return alpha.matches(alphabetpattern.toRegex())
    }
    fun isnum(num: String): Boolean
    {
        return num.matches(mobilePattern.toRegex())
    }
    fun ispass(pass: String): Boolean
    {
        return pass.matches(passpattern.toRegex())
    }

    override fun onBackPressed() {
        finishAffinity()
        super.onBackPressed()
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {

        if (binding.rbMale.isChecked){
            gender1 = "Male"
        }
        if (binding.rbFemale.isChecked){
            gender1 = "Female"
        }
    }
}