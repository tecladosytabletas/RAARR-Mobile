package com.example.appatemporal.framework.view.dialogFragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appatemporal.databinding.DeleteDialogBinding

class DeleteDialogFragment: AppCompatActivity() {
    private lateinit var binding: DeleteDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DeleteDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var myExtras : Bundle? = intent.extras

        binding.textView3.text = myExtras?.getString("nombre_proyecto")
    }
}