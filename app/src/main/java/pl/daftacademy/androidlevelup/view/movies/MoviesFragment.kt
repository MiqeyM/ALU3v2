package pl.daftacademy.androidlevelup.view.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_movies.*
import pl.daftacademy.androidlevelup.R
import pl.daftacademy.androidlevelup.view.viewmodel.MoviesViewModel


class MoviesFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this)[MoviesViewModel::class.java] }
    private val adapter = MoviesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_movies, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.adapter = adapter
        val filter = arguments?.getString("CATEGORY")?: throw IllegalStateException("Required category argument")
        activity!!.setTitle(filter)
        adapter.items = viewModel.getFilteredMovies(filter)
    }

    companion object {
        fun create(category: String) : MoviesFragment{
            val fragment = MoviesFragment()
            val args = Bundle()
            args.putString("CATEGORY", category)
            fragment.arguments = args
            return fragment

        }
    }
}
