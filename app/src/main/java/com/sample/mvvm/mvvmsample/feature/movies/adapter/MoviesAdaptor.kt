package com.sample.mvvm.mvvmsample.feature.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.sample.mvvm.mvvmsample.R
import com.vodafone.admin.mvc_movie_notify.features.networking.entities.MovieEntity


/**
 * @author lpereira on 24/07/2017.
 */

class MoviesAdaptor(context: Context, val layoutInflater: LayoutInflater = LayoutInflater.from(context))
    : ArrayAdapter<MovieEntity>(context, R.layout.item_movie) {

    private var movies: ArrayList<MovieEntity> = ArrayList()

    fun refresh(movies: ArrayList<MovieEntity>) {
        this.movies = movies
        clear()
        addAll(movies)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): MovieEntity? {
        return movies[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: ViewHolder
        // Get the data item for this position
        val movie = getItem(position)
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.item_movie, parent, false)
            holder = ViewHolder()

            // Lookup view for data population
            holder.title = view.findViewById(R.id.tvName)
            holder.releaseDate = view.findViewById(R.id.tvReleaseDate)

            view.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            view = convertView
        }

        // Populate the data into the template view using the data object
        movie?.let {
            holder.title!!.text = movie.title
            holder.releaseDate!!.text = movie.release_date
        }

        // Return the completed view to render on screen
        return view
    }

    internal class ViewHolder {
        var title: TextView? = null
        var releaseDate: TextView? = null
    }
}
