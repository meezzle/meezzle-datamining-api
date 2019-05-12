package com.meezzle.datamining.main

import com.meezzle.datamining.extracts.InputExtractor._
import com.meezzle.datamining.extracts.WikiMovie
import com.meezzle.datamining.extracts.parsers.MovieLinkParser

object WikiMovieExtractor extends App {

  val movieExtractors = getMovies("wikimovies.yaml")
  val movieLinkParser = MovieLinkParser(movieExtractors)
  movieLinkParser.run()


}
