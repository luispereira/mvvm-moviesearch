package com.sample.mvvm.mvvmsample.feature.movies

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import com.sample.mvvm.mvvmsample.R
import com.sample.mvvm.mvvmsample.feature.movies.adapter.MoviesAdaptor
import com.vodafone.admin.mvc_movie_notify.features.networking.entities.MovieEntity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MovieActivity : AppCompatActivity() {
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MoviesAdaptor

    var list: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initialize()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Refreshing upcomming movies", Snackbar.LENGTH_SHORT).setAction("Action", null).show()
            viewModel.fetchMovies().subscribe({ movies -> refreshListView(movies) }, { error -> onError(error) })
        }
    }

    private fun onError(error: Throwable) {
        Snackbar.make(fab, "There was an error...", Snackbar.LENGTH_SHORT).setAction("Action", null).show()
    }

    private fun refreshListView(movies: List<MovieEntity>) {
        adapter.refresh(ArrayList<MovieEntity>(movies))
    }

    private fun initialize() {
        viewModel = MovieViewModel()
        adapter = MoviesAdaptor(this)
        list = findViewById(R.id.list)
        list?.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
