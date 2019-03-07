package com.meezzle.datamining.api.sources

import akka.http.scaladsl.model.HttpMethods
import com.meezzle.datamining.records.MovieDetailRecord
import com.typesafe.config.Config

import scala.concurrent.Future

case class MovieApiSource(config: Config, protected val movieApiSourceBuilder: Option[ApiSourceBuilder] = None)
  extends ApiSource[MovieDetailRecord](config, movieApiSourceBuilder) {
  override protected def getApiSourceConfig: ApiSourceConfig = {
    movieApiSourceBuilder.getOrElse(MovieApiSourceBuilder()).build(config)
  }

  override def get(suffix: String): Future[MovieDetailRecord] = {
    ???
  }
}

case class MovieApiSourceBuilder() extends ApiSourceBuilder {
  override def build(config: Config): ApiSourceConfig = {
    val namespace = "meezzle.api.tmdb"
    val url = config.getString(s"$namespace.url")
    val version = Option(config.getInt(s"$namespace.version"))
    val password = Option(config.getString(s"$namespace.password"))

    ApiSourceConfig(url,password,version)

  }
}