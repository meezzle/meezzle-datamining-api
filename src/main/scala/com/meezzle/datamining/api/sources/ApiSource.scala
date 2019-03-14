package com.meezzle.datamining.api.sources

import akka.http.scaladsl.model._
import com.meezzle.datamining.configs.ConfigBuilder
import com.meezzle.datamining.records.ApiRecord
import com.typesafe.config.Config

import scala.concurrent.Future

case class ApiSourceConfig(url: String,
                           apiKey: Option[String] = None,
                           version: Option[Int] = None,
                           config: Option[ConfigBuilder] = None)

trait ApiSourceBuilder {
  def build(config: Config): ApiSourceConfig
}

abstract class ApiSource[T <: ApiRecord](config: Config,
                                         source: Option[ApiSourceBuilder]){

  lazy val sourceConfig = getApiSourceConfig
  protected def getApiSourceConfig: ApiSourceConfig

}
