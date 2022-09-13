package com.example.appatemporal.framework.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import com.example.appatemporal.databinding.ActivityOtpactivityBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class OTPActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityOtpactivityBinding
    private lateinit var verifyBtn : Button
    private lateinit var resendTV : TextView
    private lateinit var inputOTP1 : EditText
    private lateinit var inputOTP2 : EditText
    private lateinit var inputOTP3 : EditText
    private lateinit var inputOTP4 : EditText
    private lateinit var inputOTP5 : EditText
    private lateinit var inputOTP6 : EditText
    private lateinit var progressBar : ProgressBar

    private lateinit var OTP: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var phoneNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        OTP = intent.getStringExtra("OTP").toString()
        resendToken = intent.getParcelableExtra("resendToken")!!
        phoneNumber = intent.getStringExtra("phoneNumber")!!

        init()
        progressBar.visibility = View.INVISIBLE
        addTextChangeListener()
        resendOTPvVIsibility()

        resendTV.setOnClickListener {
            resenderVerificationCode()
            resendOTPvVIsibility()
        }

        verifyBtn.setOnClickListener {
            //colect otp from all the edit text
            val typedOTP = inputOTP1.text.toString() + inputOTP2.text.toString() + inputOTP3.text.toString() +
                    inputOTP4.text.toString() + inputOTP5.text.toString() + inputOTP6.text.toString()
            Log.d("typedOTP", typedOTP)
            if (typedOTP.isNotEmpty()){
                if (typedOTP.length == 6){
                    val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(
                        OTP, typedOTP
                    )
                    progressBar.visibility = View.VISIBLE
                    signInWithPhoneAuthCredential(credential)
                }else{
                    Toast.makeText( this, "Please enter coerrect OTP", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText( this, "Please enter OTP", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun resendOTPvVIsibility(){
        inputOTP1.setText("")
        inputOTP2.setText("")
        inputOTP3.setText("")
        inputOTP4.setText("")
        inputOTP5.setText("")
        inputOTP6.setText("")
        resendTV.isEnabled = false
        resendTV.setTextColor(Color.parseColor("#606060"))

        Handler(Looper.myLooper()!!).postDelayed(Runnable{
            resendTV.setTextColor(Color.parseColor("#E4EBF1"))
            resendTV.isEnabled = true
        },60000)
    }

    private fun resenderVerificationCode(){
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            //signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request\
                Log.d("TAG", "onVerificationFailed: ${e.toString()}")
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Log.d("TAG", "onVerificationFailed: ${e.toString()}")
            }

        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
           OTP = verificationId
            resendToken = token
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    progressBar.visibility = View.VISIBLE
                    Toast.makeText(this,"Authenticate Succesfully", Toast.LENGTH_SHORT).show()
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.d("TAG","SignInWithPhoneAuthCredential: ${task.exception.toString()}")
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                         Log.d("TAG","Incorrect SMS")
                    }
                    // Update UI
                }
            }
    }

    private fun addTextChangeListener(){
        inputOTP1.addTextChangedListener(EditTextWatcher(inputOTP1))
        inputOTP2.addTextChangedListener(EditTextWatcher(inputOTP2))
        inputOTP3.addTextChangedListener(EditTextWatcher(inputOTP3))
        inputOTP4.addTextChangedListener(EditTextWatcher(inputOTP4))
        inputOTP5.addTextChangedListener(EditTextWatcher(inputOTP5))
        inputOTP6.addTextChangedListener(EditTextWatcher(inputOTP6))
    }

    private fun init() {
        auth = FirebaseAuth.getInstance()
        progressBar = binding.otpProgressBar
        verifyBtn = binding.verifyOTPBtn
        resendTV = binding.resendTextView
        inputOTP1 = binding.otpEditText1
        inputOTP2 = binding.otpEditText2
        inputOTP3 = binding.otpEditText3
        inputOTP4 = binding.otpEditText4
        inputOTP5 = binding.otpEditText5
        inputOTP6 = binding.otpEditText6
    }

    inner class EditTextWatcher(private val view : View) : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            val text = p0.toString()
            when(view.id){
                binding.otpEditText1.id -> if(text.length == 1 ) inputOTP2.requestFocus() else if (text.isEmpty()) inputOTP1.requestFocus()
                binding.otpEditText2.id -> if(text.length == 1 ) inputOTP3.requestFocus() else if (text.isEmpty()) inputOTP1.requestFocus()
                binding.otpEditText3.id -> if(text.length == 1 ) inputOTP4.requestFocus() else if (text.isEmpty()) inputOTP2.requestFocus()
                binding.otpEditText4.id -> if(text.length == 1 ) inputOTP5.requestFocus() else if (text.isEmpty()) inputOTP3.requestFocus()
                binding.otpEditText5.id -> if(text.length == 1 ) inputOTP6.requestFocus() else if (text.isEmpty()) inputOTP4 .requestFocus()
                binding.otpEditText6.id -> if(text.isEmpty()) inputOTP5.requestFocus()
            }
        }
    }
}



