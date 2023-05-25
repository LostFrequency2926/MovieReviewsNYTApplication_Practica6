package com.example.moviereviewsnytapplication.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviereviewsnytapplication.databinding.FragmentListBinding
import com.example.moviereviewsnytapplication.server.model.Review

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {

        val listViewModel = ViewModelProvider(this)[ListViewModel::class.java]

        _binding = FragmentListBinding.inflate(inflater, container, false)

        val reviewsList = ArrayList<Review>()
        val reviewsAdapter = ReviewsAdapter(reviewsList, onItemClicked = {review -> onItemClicked(review)})

        binding.reviewRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.requireContext())
            adapter = reviewsAdapter
            setHasFixedSize(false)
        }

        listViewModel.loadReviews()

        listViewModel.reviewsLoaded.observe(viewLifecycleOwner){listReviews ->
            reviewsAdapter.appendItems(listReviews)
        }

        return binding.root
    }

    private fun onItemClicked(review: Review) {
        findNavController().navigate(ListFragmentDirections.actionNavigationListToNavigationDetail(review = review))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}