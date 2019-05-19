package com.meezzle.datamining.dbs

import com.meezzle.datamining.dbs.sources.{DBSource, DBSourceBuilder, DBSourceConfig}
import com.typesafe.config.Config

case class PostgresDBSource(config: Config,
                            optSourceBuilder: Option[PostgresDBSourceBuilder] = None)
  extends DBSource(config, optSourceBuilder) {
  override protected def getSourceConfig: DBSourceConfig = {
    optSourceBuilder.getOrElse(PostgresDBSourceBuilder()).build(config)
  }
}


case class PostgresDBSourceBuilder() extends DBSourceBuilder {

  import PostgresDBSourceBuilder._

  override def build(config: Config): DBSourceConfig = {
    val host = config.getString(s"$namespace.host")
    val port = config.getInt(s"$namespace.port")
    val db = config.getString(s"$namespace.db")
    val user = config.getString(s"$namespace.username")
    val pwd = config.getString(s"$namespace.password")

    DBSourceConfig(host,port,db,user,pwd)
  }
}


object PostgresDBSourceBuilder {
  val namespace = s"${DBSourceBuilder.namespace}.postgres"
}