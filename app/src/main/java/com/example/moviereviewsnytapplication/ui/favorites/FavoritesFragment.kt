package com.example.moviereviewsnytapplication.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviereviewsnytapplication.R
import com.example.moviereviewsnytapplication.databinding.FragmentFavoritesBinding
import com.example.moviereviewsnytapplication.local.model.LocalReview

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private lateinit var favoriteViewModel: FavoritesViewModel
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View {
        favoriteViewModel =  ViewModelProvider(this)[FavoritesViewModel::class.java]
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        val favoriteReviewsList = ArrayList<LocalReview>()
        val reviewsFavoriteAdapter = ReviewsFavoriteAdapter(
            favoriteReviewsList,
            onItemClicked = {favoriteReviewsList -> onItemClicked(favoriteReviewsList)},
            onItemLongClicked = {localMovie ->
                deleteFavoriteReview(localMovie)
            })

        binding.reviewsFavoriteRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FavoritesFragment.requireContext())
            adapter = reviewsFavoriteAdapter
            setHasFixedSize(false)
        }

        favoriteViewModel.loadFavoriteMovies()

        favoriteViewModel.favoriteReviews.observe(viewLifecycleOwner){favoriteReviewsList ->
            reviewsFavoriteAdapter.appendItems(favoriteReviewsList)
        }

        return binding.root
    }

    private fun onItemClicked(favoriteReview: LocalReview) {
        findNavController().navigate(FavoritesFragmentDirections.actionNavigationFavoritesToFavoriteDetailFragment(favoriteReview = favoriteReview))
    }

    private fun deleteFavoriteReview(localReview: LocalReview) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage("Desea Eliminar la review ${localReview.displayTitle} de sus favoritos?")
                setPositiveButton(R.string.accept){ dialog, id ->
                    favoriteViewModel.deleteFavoriteReview(localReview)
                    favoriteViewModel.loadFavoriteMovies()
                }
                setNegativeButton(R.string.cancel){dialog, id ->

                }
            }
            builder.create()
        }
        alertDialog?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        favoriteViewModel.loadFavoriteMovies()
    }
}