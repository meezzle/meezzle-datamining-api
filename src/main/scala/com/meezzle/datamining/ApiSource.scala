package com.meezzle.datamining

import com.meezzle.datamining.records.ApiRecord
import com.typesafe.config.Config

case class ApiSourceConfig(url: String,
                           apiKey: Option[String] = None,
                           version: Option[Int] = None,
                           kvPair: Option[Map[String,String]] = None)

trait ApiSourceBuilder {
  def build(config: Config): ApiSourceConfig
}

abstract class ApiSource[T <: ApiRecord](config: Config,
                                         source: Option[ApiSourceBuilder]){

  def get(url: String): T
  def getList(url: String): Seq[T]

}
