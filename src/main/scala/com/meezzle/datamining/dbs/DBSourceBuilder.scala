package com.meezzle.datamining.dbs

import com.typesafe.config.Config

object DBSourceBuilder {
  val namespace = "meezzle.dbs"
}

trait DBSourceBuilder {
  def build(config: Config): DBSourceConfig
}
