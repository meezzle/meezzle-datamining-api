package com.meezzle.datamining.dbs

import com.meezzle.datamining.dbs.sources.{DBSource, DBSourceBuilder, DBSourceConfig}
import com.typesafe.config.Config

case class MongoDBSource(config: Config,
                    sourceBuilder: Option[MongoDBSourceBuilder] = None)
extends DBSource(config, sourceBuilder) {
  override protected def getSourceConfig: DBSourceConfig = {
    sourceBuilder.getOrElse(MongoDBSourceBuilder()).build(config)
  }
}


case class MongoDBSourceBuilder() extends DBSourceBuilder {

  import MongoDBSourceBuilder._

  override def build(config: Config): DBSourceConfig = {
    val host = config.getString(s"$namespace.host")
    val port = config.getInt(s"$namespace.port")
    val db = config.getString(s"$namespace.db")
    val user = config.getString(s"$namespace.username")
    val pwd = config.getString(s"$namespace.password")

    DBSourceConfig(host,port,db,user,pwd)

  }
}

object MongoDBSourceBuilder {
  val namespace = s"${DBSourceBuilder.namespace}.mongo"
}