package com.meezzle.datamining.extracts.parsers

import com.meezzle.datamining.extracts.WikiExtractor
import org.jsoup.nodes.Element

import scala.util.{Failure, Success, Try}

trait JsoupParser[T <: WikiExtractor] {

  protected def jsoupParser(url: String, query: String): Seq[T]
  protected def childExist(element: Element, ind: Int = 0) = {
    Try(element.child(ind)) match {
      case Success(value) => true
      case Failure(exception) => false
    }
  }

}
