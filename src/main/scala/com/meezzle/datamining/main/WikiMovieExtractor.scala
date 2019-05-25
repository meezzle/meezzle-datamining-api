package com.meezzle.datamining.main

import com.meezzle.datamining.dbs.PostgresDBSource
import com.meezzle.datamining.extracts.InputExtractor._
import com.meezzle.datamining.extracts.parsers.MovieLinkParser
import com.typesafe.config.ConfigFactory

object WikiMovieExtractor extends App {

  val config = ConfigFactory.load()
  val postgresDBSource = PostgresDBSource(config)
  val movieExtractors = getMovies("wikimovies.yaml")
  val movieLinkParser = MovieLinkParser(movieExtractors)



}
