package com.example.appatemporal.framework.view.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appatemporal.R
import com.example.appatemporal.data.constants.Constantes.Companion.comentario
import com.example.appatemporal.databinding.ItemCommentBinding
import com.example.appatemporal.domain.models.CommentModel

class GetCommentsViewHolder (view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemCommentBinding.bind(view)


    fun render(comment: CommentModel) {
        binding.Comentario.text = comment.comentario
    }
}