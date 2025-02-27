package com.example.appatemporal.framework.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.appatemporal.databinding.ActivityPhoneBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

/**
 * Class that inherits from AppCompatActivity
 */
class PhoneActivity : AppCompatActivity() {

    private lateinit var sendOTPbtn: Button
    private lateinit var phoneNumberET: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var number: String
    private lateinit var mProgressBar : ProgressBar
    private lateinit var binding: ActivityPhoneBinding

    /**
     * Overrides function onCreate and starts the activity
     *
     * @param savedInstanceState: Bundle? -> Saved instance of the activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        sendOTPbtn.setOnClickListener {
            number = phoneNumberET.text.trim().toString()

            if (number.isNotEmpty()){
                if (number.length == 10){
                    number = "+52$number"
                    mProgressBar.visibility = View.VISIBLE
                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)

                }else{
                    Toast.makeText(this,"Ingresa un número teléfonico válido", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Ingresa un número telefónico", Toast.LENGTH_SHORT).show()

            }
        }
    }

    /**
     * Initializes variables declared as lateinit on 'onCreate' function
     */
    private fun init(){
        mProgressBar = binding.phoneProgressBar
        mProgressBar.visibility = View.INVISIBLE
        sendOTPbtn = binding.sendOTPBtn
        phoneNumberET = binding.phoneEditTextNumber
        auth = FirebaseAuth.getInstance()
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        /**
         * Depending on the device used, it will verify the OTP
         * credential by receiving the message
         *
         * @param credential: PhoneAuthCredential -> Credential sent by Firebase
         */
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            //signInWithPhoneAuthCredential(credential)
        }
        /**
         * Logs to console if verification failed due to
         * invalid credentials or excessive amount of requests
         *
         * @param e: FirebaseException -> Exception returned by Firebase when verification fails
         */
        override fun onVerificationFailed(e: FirebaseException) {
            if (e is FirebaseAuthInvalidCredentialsException) {
                Log.d("TAG", "onVerificationFailed: ${e.toString()}")
            } else if (e is FirebaseTooManyRequestsException) {
                Log.d("TAG", "onVerificationFailed: ${e.toString()}")
            }
        }

        /**
         * Sends the SMS code to phone number
         *
         * @param verificationId: String -> The 6 digits of the SMS sent by Firebase Authentication
         * @param token: PhoneAuthProvider.ForceResentingToken -> Token used by Firebase to send the OTP
         */
        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            val intent = Intent(this@PhoneActivity, OTPActivity::class.java)
            intent.putExtra("OTP", verificationId)
            intent.putExtra("resendToken", token)
            intent.putExtra("phoneNumber", number)
            Log.d("CodeSent", "Se envio código SMS")
            startActivity(intent)
            mProgressBar.visibility = View.INVISIBLE
        }
    }
}