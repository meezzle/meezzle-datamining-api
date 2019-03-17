package com.meezzle.datamining.dbs

import com.typesafe.config.Config

class MongoDBSource(config: Config,
                    protected val mongoDBSourceBuilder: Option[MongoDBSourceBuilder]) {

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