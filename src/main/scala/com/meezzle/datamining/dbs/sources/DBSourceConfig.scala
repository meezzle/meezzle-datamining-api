package com.meezzle.datamining.dbs.sources

import com.meezzle.datamining.commons.sources.SourceConfig

case class DBSourceConfig(host: String,
                          port: Int,
                          db: String,
                          user: String,
                          pwd: String) extends SourceConfig
