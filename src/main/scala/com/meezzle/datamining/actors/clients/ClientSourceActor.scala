package com.meezzle.datamining.actors.clients

import akka.actor.{Actor, ActorLogging}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, StatusCodes}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.{ActorMaterializer, ActorMaterializerSettings}
import com.meezzle.datamining.api.sources.ApiSource
import com.meezzle.datamining.api.records.ApiRecord

abstract class ClientSourceActor[T <: ApiRecord](apiSource: ApiSource[T]) extends Actor with ActorLogging {

  import akka.pattern.pipe
  import context.dispatcher

  final implicit val materializer = ActorMaterializer(ActorMaterializerSettings(context.system))
  protected val http = Http(context.system)

}
