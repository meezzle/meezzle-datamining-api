package com.meezzle.datamining.actors.clients

import akka.http.scaladsl.model.{HttpRequest, HttpResponse, StatusCodes}
import akka.http.scaladsl.unmarshalling.Unmarshal
import com.meezzle.datamining.api.sources.{MovieApiSource, MovieApiSourceBuilder}
import com.meezzle.datamining.records.MovieDetailRecord
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import com.meezzle.datamining.configs.MovieConfigBuilder
import akka.pattern.pipe

import scala.concurrent.ExecutionContext.Implicits.global

case class MovieDetailClientActor(movieApiSource: MovieApiSource) extends ClientSourceActor(movieApiSource) {

  import com.meezzle.datamining.formatters.MovieFormatter._
  import MovieConfigBuilder._

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
    println(from)
    (from to `to`).map { id =>
      http.singleRequest(HttpRequest(uri = buildUrl(id))).pipeTo(self)
    }
  }

  override def receive: Receive = {
    case response @ HttpResponse(StatusCodes.OK, _, entity,_) =>
      val obj = Unmarshal(response).to[MovieDetailRecord]
      obj.map {println}
    case response @ HttpResponse(code, _, entity, _) => // do nothing
  }



}
