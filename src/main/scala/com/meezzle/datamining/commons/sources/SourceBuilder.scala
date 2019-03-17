package com.meezzle.datamining.commons.sources

import com.typesafe.config.Config

trait SourceBuilder[T <: SourceConfig] {
  def build(config: Config): T
}
