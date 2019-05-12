package com.meezzle.datamining.main

import akka.actor.{ActorSystem, Props}
import akka.stream.ActorMaterializer
import akka.stream.alpakka.orientdb.{OrientDbReadResult, OrientDbSourceSettings, OrientDbWriteMessage}
import akka.stream.alpakka.orientdb.scaladsl.OrientDbSource
import com.meezzle.datamining.api.sources.MovieApiSource
import com.meezzle.datamining.dbs.{MongoDBSource, OrientDBSource}
import com.typesafe.config.ConfigFactory
import com.orientechnologies.orient.core.db.OPartitionedDatabasePool
import com.orientechnologies.orient.core.record.impl.ODocument


class DBClient {

  implicit val system = ActorSystem("orient-Actor")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val config = ConfigFactory.load()
  val orientdbSource = OrientDBSource(config)
//  val movieAPi = system.actorOf(Props(MovieDetailClientActor(movieApiSource)), name = "movie-detail-record")
  case class Num(_id: Int)

}
