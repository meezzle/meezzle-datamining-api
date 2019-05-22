package com.meezzle.datamining.dbs.slick.schemas.movies

import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

object MovieSchema {
  val movieUrls = TableQuery[MovieUrlSchema]
}

class MovieUrlSchema(tag: Tag) extends Table[(Int, String, String)](tag, "movie_url") {
  def id = column[Int]("movie_id", O.PrimaryKey)
  def movieName = column[String]("movie_name")
  def movieUrl = column[String]("movie_url")

  override def * = (id, movieName, movieUrl)
}

