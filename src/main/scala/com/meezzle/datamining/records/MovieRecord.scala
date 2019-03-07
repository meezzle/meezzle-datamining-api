package com.meezzle.datamining.records

case class BelongCollection(id: Int,
                            name: String,
                            poster_path: String,
                            backdrop_path: String) extends ApiRecord

case class ProductionCompany(name: Option[String],
                             id: Option[Int],
                             logo_path: Option[String],
                             origin_country: Option[String]) extends ApiRecord

case class ProductionCountry(iso_3166_1: Option[String],
                             name: Option[String]) extends ApiRecord

case class Genre(id: Option[Int],name: Option[String]) extends ApiRecord

case class SpokenLanguage(iso_639_1: Option[String], name: Option[String]) extends ApiRecord

case class MovieDetailRecord(adult: Option[Boolean],
                             backdrop_path: Option[String],
                             belongs_to_collection: Option[BelongCollection],
                             budget: Option[Int],
                             genres: Option[Array[Genre]],
                             homepage: Option[String],
                             id: Option[Int],
                             imdb_id: Option[String],
                             original_language: Option[String],
                             original_title: Option[String],
                             overview: Option[String],
                             popularity: Option[Double],
                             poster_path: Option[String],
                             production_companies: Option[Array[ProductionCompany]],
                             production_countries: Option[Array[ProductionCountry]],
                             spoken_languages: Option[Array[SpokenLanguage]],
                             status: Option[String],
                             tagline: Option[String],
                             title: Option[String],
                             video: Option[Boolean],
                             vote_average: Option[Double],
                             cote_count: Option[Int]
                            ) extends ApiRecord
