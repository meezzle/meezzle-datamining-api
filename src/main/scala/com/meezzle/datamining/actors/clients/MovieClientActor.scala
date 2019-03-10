package com.meezzle.datamining.actors.clients

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.unmarshalling.Unmarshal
import com.meezzle.datamining.api.sources.MovieApiSource
import com.meezzle.datamining.records.MovieDetailRecord
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import scala.concurrent.ExecutionContext.Implicits.global

case class MovieDetailClientActor(movieApiSource: MovieApiSource) extends ClientSourceActor(movieApiSource) {

  import com.meezzle.datamining.formatters.MovieFormatter._

  override def receive: Receive = {
    case response @ HttpResponse(StatusCodes.OK, _, entity,_) =>
      val obj = Unmarshal(response).to[MovieDetailRecord]
      obj.map {println}
  }
}
