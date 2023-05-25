package com.example.moviereviewsnytapplication.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviereviewsnytapplication.R
import com.example.moviereviewsnytapplication.databinding.CardViewReviewItemBinding
import com.example.moviereviewsnytapplication.server.model.Review
import com.squareup.picasso.Picasso
import java.util.ArrayList

class ReviewsAdapter (
    private val reviewsList: ArrayList<Review>,
    private val onItemClicked: (Review) -> Unit
) : RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view_review_item, parent, false)
        return ReviewsViewHolder(itemView)
    }

    override fun getItemCount(): Int = reviewsList.size

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        val review = reviewsList[position]
        holder.bindMovie(review)
        holder.itemView.setOnClickListener { onItemClicked(review) }
    }

    fun appendItems(newList: ArrayList<Review>){
        reviewsList.clear()
        reviewsList.addAll(newList)
        notifyItemInserted(newList.size)
    }

    class ReviewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val binding = CardViewReviewItemBinding.bind(itemView)

        fun bindMovie(review: Review) {
            with(binding) {
                displayTitleTextView.text = review.displayTitle
                reviewerTextView.text = "by " + review.byline
                publicationDateTextView.text = "Publicado: " + review.publicationDate

                Picasso.get().load(review.multimedia.src).into(pictureImageView)
            }
        }
    }


}