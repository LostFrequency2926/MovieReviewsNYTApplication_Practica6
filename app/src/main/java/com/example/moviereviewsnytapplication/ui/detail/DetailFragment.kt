package com.example.moviereviewsnytapplication.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.moviereviewsnytapplication.R
import com.example.moviereviewsnytapplication.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var detailBinding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private var isReviewFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {

        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)

        detailViewModel.isReviewFavorite.observe(viewLifecycleOwner){isReviewFavorite ->
            this.isReviewFavorite = isReviewFavorite
            if(isReviewFavorite){
                detailBinding.addFavoriteIcon.setImageDrawable(resources.getDrawable(R.drawable.favoritos_icon_2))
            }else{
                detailBinding.addFavoriteIcon.setImageDrawable(resources.getDrawable(R.drawable.favoritos_icon_1))
            }
        }

        val review = args.review

        detailViewModel.searchReview(review.displayTitle)

        val displayTitle = review.displayTitle ?: ""
        val reviewer = review.byline ?: ""
        val publicationDate = review.publicationDate ?: ""
        val imgSource = review.multimedia.src ?: ""
        val summaryShort = review.summaryShort ?: ""
        val articleUrl = review.link.url ?: ""

        with(detailBinding){
            displayTitleTextView.text = displayTitle
            publicationDateTextView.text = reviewer
            reviewerTextView.text = publicationDate
            summaryShortTextView.text = summaryShort
            articleUrlTextView.text = articleUrl
            Picasso.get().load(imgSource).into(pictureImageView)

            addFavoriteIcon.setOnClickListener{
                if(isReviewFavorite)
                    Toast.makeText(context, "${review.displayTitle} ya esta en tus favoritos", Toast.LENGTH_LONG).show()
                else{
                    isReviewFavorite = true
                    addFavoriteIcon.setImageDrawable(resources.getDrawable(R.drawable.favoritos_icon_2))
                    detailViewModel.saveReview(review)
                }
            }
        }

        return detailBinding.root
    }

}
