package com.example.moviereviewsnytapplication.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviereviewsnytapplication.R
import com.example.moviereviewsnytapplication.databinding.CardViewFavoriteItemsBinding
import com.example.moviereviewsnytapplication.databinding.CardViewReviewItemBinding
import com.example.moviereviewsnytapplication.local.model.LocalReview
import com.squareup.picasso.Picasso
import java.util.ArrayList

class ReviewsFavoriteAdapter (
    private val reviewsList: ArrayList<LocalReview>,
    private val onItemClicked: (LocalReview) -> Unit,
    private val onItemLongClicked: (LocalReview) -> Unit,
) : RecyclerView.Adapter<ReviewsFavoriteAdapter.ReviewsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_favorite_items, parent, false)
        return ReviewsViewHolder(itemView)
    }

    override fun getItemCount(): Int = reviewsList.size

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        val review = reviewsList[position]
        holder.bindMovie(review)
        holder.itemView.setOnClickListener { onItemClicked(review) }
        holder.itemView.setOnLongClickListener { onItemLongClicked(review)
            true
        }
    }

    fun appendItems(newList: ArrayList<LocalReview>){
        reviewsList.clear()
        reviewsList.addAll(newList)
        notifyDataSetChanged()
    }

    class ReviewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val binding = CardViewFavoriteItemsBinding.bind(itemView)

        fun bindMovie(review: LocalReview) {
            with(binding) {
                displayTitleTextView.text = review.displayTitle
                reviewerTextView.text = "by " + review.reviewer
                publicationDateTextView.text = "Publicado: " + review.publicationDate
            }
        }
    }

}