package com.meezzle.datamining.dbs.sources

import com.meezzle.datamining.commons.sources.SourceBuilder

object DBSourceBuilder {
  val namespace = "meezzle.dbs"
}

trait DBSourceBuilder extends SourceBuilder[DBSourceConfig]
