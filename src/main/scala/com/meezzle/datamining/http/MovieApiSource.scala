package com.meezzle.datamining.http

import com.meezzle.datamining.{ApiSource, ApiSourceBuilder, ApiSourceConfig}
import com.meezzle.datamining.records.ApiRecord
import com.typesafe.config.Config

case class MovieApiSource[M <: ApiRecord](config: Config) extends ApiSource[M](config)

case class MovieApiSourceBuilder(config: Config) extends ApiSourceBuilder {
  override def build(config: Config): ApiSourceConfig = {
    val namespace = "meezzle.api.tmdb"
    val url = config.getString(s"$namespace.url")
    val version = Option(config.getInt(s"$namespace.version"))
    val password = Option(config.getString(s"$namespace.password"))

    ApiSourceConfig(url,password,version)

  }
}
