package com.meezzle.datamining.dbs.jdbc

import java.sql.DriverManager

import com.meezzle.datamining.dbs.sources.{DBSource, DBSourceBuilder}
import com.meezzle.datamining.dbs.{MongoDBSource, OrientDBSource, PostgresDBSource, PostgresDBSourceBuilder}

import scala.util.Try

abstract class JDBConnection[T <: DBSourceBuilder](dbSource: DBSource[T]) {

  protected def getConnection = {
    Try(
      dbSource match {
        case m: MongoDBSource => ???
        case o: OrientDBSource => ???
        case p: PostgresDBSource =>
          val cfg = p.sourceConfig
          Class.forName("org.postgresql.Driver")
          DriverManager
            .getConnection(s"jdbc:postgresql://${cfg.host}:${cfg.port}/${cfg.db}",
              cfg.user, cfg.pwd)
        case _ => throw new NotImplementedError("db source is not implemented yet")
      }
    ).toOption
  }

}

case class PostgresConnection(protected val dbSource: DBSource[PostgresDBSourceBuilder])
  extends JDBConnection(dbSource) {

  def put(query: String) = {
    getConnection.map {
      try {
        connection =>
          val statement = connection.createStatement()
          statement.execute(query)
      }
    }
  }

}
