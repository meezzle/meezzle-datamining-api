package com.meezzle.datamining.extracts


import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.codehaus.jackson.annotate.JsonCreator
import org.joda.time.DateTime
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.slf4j.{Logger, LoggerFactory}

import scala.util.Try

sealed trait WikiExtractor
sealed trait JsonExtractor
sealed trait MovieExtractor extends WikiExtractor

case class JsonWikiMovieUrl(@JsonProperty("url") url: String) extends JsonExtractor

case class WikiMovieLink(title: String, url: String) extends MovieExtractor

case class WikiDataLink(name: String, url: String) extends WikiExtractor

case class WikiMovieData(title: String,
                         imageUrl: Option[String] = None,
                         directedBy: Option[WikiDataLink] = None,
                         producedBy: Option[Seq[WikiDataLink]] = None,
                         writtenBy: Option[Seq[WikiDataLink]] = None,
                         basedOn: Option[Seq[WikiDataLink]] = None,
                         starring: Option[Seq[WikiDataLink]] = None,
                         musicBy: Option[WikiDataLink] = None,
                         cinematography: Option[WikiDataLink] = None,
                         editedBy: Option[WikiDataLink] = None,
                         productionCompany: Option[Seq[WikiDataLink]] = None,
                         distributedBy: Option[Seq[WikiDataLink]] = None,
                         releaseDate: Option[String],
                         runningTime: Option[String],
                         country: Option[String],
                         language: Option[String],
                         budget: Option[String],
                         boxOffice: Option[String]) extends MovieExtractor