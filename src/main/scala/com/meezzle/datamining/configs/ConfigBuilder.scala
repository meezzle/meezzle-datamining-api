package com.meezzle.datamining.configs

import com.typesafe.config.Config

abstract class ConfigBuilder(config: Config) {

  lazy val mapNumber = initMapNumber

  protected def initMapNumber: Map[String,Int]

}
