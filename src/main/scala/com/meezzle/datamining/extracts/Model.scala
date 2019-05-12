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

case class WikiMovie(@JsonProperty("url") url: String) extends JsonExtractor



case class MovieLink(name: String, url: String) extends MovieExtractor