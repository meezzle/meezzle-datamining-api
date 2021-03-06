package com.meezzle.datamining.api.sources

import akka.http.scaladsl.model.HttpMethods
import com.meezzle.datamining.api.configs.MovieConfigBuilder
import com.meezzle.datamining.api.records.MovieDetailRecord
import com.typesafe.config.Config

import scala.concurrent.Future

case class MovieApiSource(config: Config, protected val movieApiSourceBuilder: Option[ApiSourceBuilder] = None)
  extends ApiSource[MovieDetailRecord](config, movieApiSourceBuilder) {
  override protected def getSourceConfig: ApiSourceConfig = {
    movieApiSourceBuilder.getOrElse(MovieApiSourceBuilder()).build(config)
  }

}

case class MovieApiSourceBuilder() extends ApiSourceBuilder {
  override def build(config: Config): ApiSourceConfig = {
    import MovieApiSourceBuilder._
    val url = config.getString(s"$namespace.url")
    val version = Option(config.getInt(s"$namespace.version"))
    val password = Option(config.getString(s"$namespace.password"))
    ApiSourceConfig(url,password,version, Option(MovieConfigBuilder(config)))
  }
}

object MovieApiSourceBuilder {
  val namespace = "meezzle.datamining.apis.tmdb"
}