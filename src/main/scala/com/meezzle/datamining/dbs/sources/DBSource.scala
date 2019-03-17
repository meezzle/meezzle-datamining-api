package com.meezzle.datamining.dbs.sources

import com.meezzle.datamining.commons.sources.CommonSource
import com.typesafe.config.Config

abstract class DBSource[T <: DBSourceBuilder](config: Config,
                        sourceBuilder: Option[T])
extends CommonSource[DBSourceConfig]
