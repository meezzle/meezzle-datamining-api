package com.meezzle.datamining.main

import akka.actor.{ActorSystem, Props}
import akka.stream.ActorMaterializer
import com.meezzle.datamining.api.actors.clients.MovieDetailClientActor
import com.meezzle.datamining.api.sources.MovieApiSource
import com.typesafe.config.ConfigFactory

object Client extends App {

  implicit val system = ActorSystem("Client-Api-Actor")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val config = ConfigFactory.load()
  val movieApiSource = MovieApiSource(config)
  val movieAPi = system.actorOf(Props(MovieDetailClientActor(movieApiSource)), name = "movie-detail-record")


}
