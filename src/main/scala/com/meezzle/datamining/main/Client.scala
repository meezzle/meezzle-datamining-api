package com.meezzle.datamining.main

import java.net.HttpRetryException

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.ActorMaterializer
import com.meezzle.datamining.api.sources.MovieApiSource
import com.meezzle.datamining.records.MovieDetailRecord
import com.typesafe.config.ConfigFactory
import scala.util._

import scala.concurrent.Future

object Client extends App {

  import com.meezzle.datamining.formatters.GlobalFormatter._

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val config = ConfigFactory.load()
  val movieApiSource = MovieApiSource(config)
  val c = movieApiSource.sourceConfig

  val url = s"${c.url}/${c.version.getOrElse(3)}/movie/13?api_key=${c.apiKey.getOrElse("")}"

  val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = "http://akka.io"))

  responseFuture
    .onComplete {
      case Success(res) => println(res)
      case Failure(_)   => sys.error("something wrong")
    }

}
