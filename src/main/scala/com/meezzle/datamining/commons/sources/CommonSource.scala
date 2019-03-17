package com.meezzle.datamining.commons.sources

trait CommonSource[T <: SourceConfig] {

  lazy val sourceConfig = getSourceConfig
  protected def getSourceConfig: T

}
