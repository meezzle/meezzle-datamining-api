package com.meezzle.datamining.api.sources

import akka.http.scaladsl.model._
import com.meezzle.datamining.records.ApiRecord
import com.typesafe.config.Config

import scala.concurrent.Future

case class ApiSourceConfig(url: String,
                           apiKey: Option[String] = None,
                           version: Option[Int] = None,
                           kvPair: Option[Map[String,String]] = None)

trait ApiSourceBuilder {
  def build(config: Config): ApiSourceConfig
}

abstract class ApiSource[T <: ApiRecord](config: Config,
                                         source: Option[ApiSourceBuilder]){

  lazy val sourceConfig = getApiSourceConfig

  protected def getApiSourceConfig: ApiSourceConfig
  protected def getHttpVerb(verb: HttpMethod): HttpRequest = ???


  def get(suffix: String): Future[T]

}
