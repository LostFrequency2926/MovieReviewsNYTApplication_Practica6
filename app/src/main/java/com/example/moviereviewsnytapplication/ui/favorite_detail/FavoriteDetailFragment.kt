package com.example.moviereviewsnytapplication.ui.favorite_detail

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.moviereviewsnytapplication.databinding.FragmentFavoriteDetailBinding
import com.example.moviereviewsnytapplication.ui.detail.DetailViewModel
import com.squareup.picasso.Picasso

class FavoriteDetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var favoriteDetailViewModel: FavoriteDetailViewModel
    private lateinit var favoriteDetailBinding: FragmentFavoriteDetailBinding
    private val args: FavoriteDetailFragmentArgs by navArgs()
    private var isReviewFavorite = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        favoriteDetailViewModel = ViewModelProvider(this)[FavoriteDetailViewModel::class.java]
        favoriteDetailBinding = FragmentFavoriteDetailBinding.inflate(inflater, container, false)

        val favoriteReview = args.favoriteReview

        detailViewModel.searchReview(favoriteReview?.displayTitle)

        val displayTitle = favoriteReview?.displayTitle ?: ""
        val reviewer = favoriteReview?.reviewer ?: ""
        val publicationDate = favoriteReview?.publicationDate ?: ""
        val imgSource = favoriteReview?.imgSource ?: ""
        val summaryShort = favoriteReview?.summaryShort ?: ""
        val articleUrl = favoriteReview?.articleUrl ?: ""

        with(favoriteDetailBinding){
            displayTitleTextView.text = displayTitle
            publicationDateTextView.text = "by: " + reviewer
            reviewerTextView.text = "Publicado: " + publicationDate
            summaryShortTextView.text = summaryShort
            urlArticleTextView.text = articleUrl
            Picasso.get().load(imgSource).into(pictureImageView)
        }

        favoriteDetailBinding.urlArticleTextView.setOnClickListener {
            val url = favoriteDetailBinding.urlArticleTextView.text.toString()
            openUrlExternally(url)
        }

        return favoriteDetailBinding.root
    }

    private fun openUrlExternally(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}