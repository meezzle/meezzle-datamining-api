package com.meezzle.datamining.records

case class BelongCollection(id: Int,
                            name: String,
                            poster_path: String,
                            backdrop_path: String)

case class Genre(id: Option[Int],name: Option[String])

case class MovieDetailRecord(adult: Option[Boolean],
                             backdrop_path: Option[String],
                             belongs_to_collection: Option[BelongCollection],
                             budget: Option[Int],
                             genres: Option[Array[Genre]]
                            ) extends ApiRecord
