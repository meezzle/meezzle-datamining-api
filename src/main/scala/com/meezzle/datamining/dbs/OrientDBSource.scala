package com.meezzle.datamining.dbs

import com.meezzle.datamining.dbs.sources.{DBSource, DBSourceBuilder, DBSourceConfig}
import com.typesafe.config.Config

case class OrientDBSource(config: Config,
                          sourceBuilder: Option[OrientDBSourceBuilder] = None)
extends DBSource(config, sourceBuilder){
  override protected def getSourceConfig: DBSourceConfig = {
    sourceBuilder.getOrElse(OrientDBSourceBuilder()).build(config)
  }
}

case class OrientDBSourceBuilder() extends DBSourceBuilder {

  import OrientDBSourceBuilder._

  override def build(config: Config): DBSourceConfig = {
    val host = config.getString(s"$namespace.host")
    val port = config.getInt(s"$namespace.port")
    val db = config.getString(s"$namespace.db")
    val user = config.getString(s"$namespace.username")
    val pwd = config.getString(s"$namespace.password")

    DBSourceConfig(host,port,db,user,pwd)
  }
}

object OrientDBSourceBuilder {
  val namespace = s"${DBSourceBuilder.namespace}.orient"
}