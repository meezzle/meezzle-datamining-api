package com.meezzle.datamining.api.configs

import com.typesafe.config.Config

object MovieConfigBuilder {
  val Range = "range"
  val From = "from"
  val To = "to"
}

case class MovieConfigBuilder(config: Config) extends ConfigBuilder(config) {

  import MovieConfigBuilder._
  import com.meezzle.datamining.api.sources.MovieApiSourceBuilder._
  lazy val range = s"$namespace.$Range"

  override protected def initMapNumber: Map[String, Int] = {
    lazy val from = config.getInt(s"$range.$From")
    lazy val to = config.getInt(s"$range.$To")
    Map(From -> from, To -> to)
  }



}
