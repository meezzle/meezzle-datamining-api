package com.meezzle.datamining.actors.clients.api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, StatusCodes}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.pattern.pipe
import com.meezzle.datamining.actors.clients.ClientSourceActor
import com.meezzle.datamining.api.configs.MovieConfigBuilder
import com.meezzle.datamining.api.records.MovieDetailRecord
import com.meezzle.datamining.api.sources.MovieApiSource

import scala.concurrent.ExecutionContext.Implicits.global

case class MovieDetailClientActor(movieApiSource: MovieApiSource) extends ClientSourceActor(movieApiSource) {

  import MovieConfigBuilder._
  import com.meezzle.datamining.api.formatters.MovieFormatter._

  val movieBuilder =
    movieApiSource.sourceConfig.config.getOrElse(MovieConfigBuilder(movieApiSource.config))
  val sc = movieApiSource.sourceConfig

  def buildUrl(id: Int) = {
    s"${sc.url}/${sc.version.getOrElse(3)}/movie/$id?api_key=${sc.apiKey.getOrElse("")}"
  }


  override def preStart(): Unit = run

  def run = {
    val from: Int = movieBuilder.mapNumber.getOrElse[Int](From,1)
    val `to`: Int = movieBuilder.mapNumber.getOrElse[Int](To,20)
    (from to `to`).map { id =>
      Thread.sleep(500)
      http.singleRequest(HttpRequest(uri = buildUrl(id))).pipeTo(self)
    }
  }

  override def receive: Receive = {
    case response @ HttpResponse(StatusCodes.OK, _, entity,_) =>
      val obj = Unmarshal(response).to[MovieDetailRecord]
      obj.map { mdr =>
        println(mdr.original_title)
        mdr.genres.map( genre => genre.map(println))
      }
    case response @ HttpResponse(code, _, entity, _) => // do nothing
  }



}
