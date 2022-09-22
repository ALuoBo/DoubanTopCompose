package com.sunbio.composemvi.api

data class MovieItem(
    val alias: String,
    val createdAt: Long,
    val data: List<Data>,
    val dateReleased: String,
    val doubanId: String,
    val doubanRating: String,
    val doubanVotes: Int,
    val duration: Int,
    val id: String,
    val imdbId: String,
    val imdbRating: String,
    val imdbVotes: Int,
    val originalName: String,
    val rottenRating: String,
    val rottenVotes: Int,
    val type: String,
    val updatedAt: Long,
    val year: String
)

data class Data(
    val country: String,
    val createdAt: Long,
    val description: String,
    val genre: String,
    val id: String,
    val lang: String,
    val language: String,
    val movie: String,
    val name: String,
    val poster: String,
    val shareImage: String,
    val updatedAt: Long
)
 data class Movie(
     val poster: String,
     val name:String,
     val alias: String,
     val description: String
 )

fun MovieItem.asEntry():Movie{
    return Movie(
        poster = this.data[0].poster,
        name = this.data[0].name,
        alias = this.alias,
        description = this.data[0].description
    )
}