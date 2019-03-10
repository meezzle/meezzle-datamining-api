package com.meezzle.datamining.main

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, StatusCodes}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import com.meezzle.datamining.api.sources.MovieApiSource
import com.meezzle.datamining.records.MovieDetailRecord
import com.typesafe.config.ConfigFactory
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import com.meezzle.datamining.actors.clients.MovieDetailClientActor

import scala.concurrent.Future

object Client extends App {

  import com.meezzle.datamining.formatters.MovieFormatter._

  implicit val system = ActorSystem("Client-Api-Actor")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val config = ConfigFactory.load()
  val movieApiSource = MovieApiSource(config)
  /*val c = movieApiSource.sourceConfig

  val url = s"${c.url}/${c.version.getOrElse(3)}/movie/13?api_key=${c.apiKey.getOrElse("")}"

  val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = url))


  responseFuture.map {
    case response @ HttpResponse(StatusCodes.OK, _,_,_) =>
      println(response)
      println(response.entity)
      val movie = Unmarshal(response).to[MovieDetailRecord]
      movie.map { m =>
        println(m)
      }
    case _ => sys.error("http query is not available")
  }*/

  system.actorOf(Props(MovieDetailClientActor(movieApiSource)), name = "movie-detail-record")


}
