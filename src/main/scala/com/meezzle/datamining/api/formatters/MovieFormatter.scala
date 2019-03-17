package com.meezzle.datamining.api.formatters

import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import com.meezzle.datamining.api.records._
import spray.json.DefaultJsonProtocol._


object MovieFormatter {

  implicit val belongCollectionFormatter = jsonFormat4(BelongCollection)
  implicit val productionCompanyFormatter = jsonFormat4(ProductionCompany)
  implicit val productionCountryFormatter = jsonFormat2(ProductionCountry)
  implicit val genreFormatter = jsonFormat2(Genre)
  implicit val spokenLanguageFormatter = jsonFormat2(SpokenLanguage)
  implicit val movieDetailRecordFormatter = jsonFormat22(MovieDetailRecord)

}
