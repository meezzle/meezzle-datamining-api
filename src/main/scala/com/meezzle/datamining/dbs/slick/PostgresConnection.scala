package com.meezzle.datamining.dbs.slick

import com.meezzle.datamining.dbs.sources.{DBSource, DBSourceBuilder}
import com.meezzle.datamining.dbs.{MongoDBSource, OrientDBSource, PostgresDBSource, PostgresDBSourceBuilder}
import slick.jdbc.PostgresProfile.api._

abstract class JDBConnection[T <: DBSourceBuilder](dbSource: DBSource[T]) {

  protected def getConnection = {
    dbSource match {
      case m: MongoDBSource => ???
      case o: OrientDBSource => ???
      case p: PostgresDBSource =>
        val cfg = p.sourceConfig
        val connectionUrl = s"jdbc:postgresql://${cfg.host}:${cfg.port}/${cfg.db}"
        Database.forURL(connectionUrl, cfg.user, cfg.pwd, driver = "org.postgresql.Driver")
      case _ => throw new NotImplementedError("db source is not implemented yet")
    }
  }

}

case class PostgresConnection(protected val dbSource: DBSource[PostgresDBSourceBuilder])
  extends JDBConnection(dbSource) {



}
