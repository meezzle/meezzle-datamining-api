package com.meezzle.datamining.extracts

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory

object InputExtractor {

  def getExtractor[T](filename: String): Seq[T] = {
    val mapper = new ObjectMapper(new YAMLFactory())
    val inputStream = getClass.getClassLoader.getResource(filename)
    mapper.readValue(inputStream, classOf[Array[T]]).toSeq
  }

  def getMovies(filename: String): Seq[WikiMovie] = {
    val mapper = new ObjectMapper(new YAMLFactory())
    val inputStream = getClass.getClassLoader.getResource(filename)
    mapper.readValue(inputStream, classOf[Array[WikiMovie]]).toSeq
  }

}
