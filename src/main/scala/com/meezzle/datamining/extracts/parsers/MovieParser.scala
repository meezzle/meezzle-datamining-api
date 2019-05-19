package com.meezzle.datamining.extracts.parsers

import com.meezzle.datamining.extracts.{MovieExtractor, MovieLink, ParserRunnable, WikiMovie}
import org.jsoup.Jsoup

import scala.collection.JavaConverters._

object MovieLinkParser {

  val jsoupQuery = "div[class=div-col columns column-width]"

}

case class MovieLinkParser(movieExtractors: Seq[WikiMovie]) extends JsoupParser[Seq[MovieLink]] with ParserRunnable[MovieLink] {
  override protected def jsoupParser(url: String, query: String): Seq[MovieLink] = {
    Option(Jsoup.connect(url).get()).map { doc =>
      doc.select(query).asScala.flatMap { obj =>
        obj.getElementsByTag("ul").asScala.flatMap { li =>
          li.getElementsByTag("i").asScala.flatMap { m =>
            if(childExist(m)) {
              Option(
                MovieLink(
                m.child(0).attr("title"),
                m.child(0).attr("href")
                )
              )
            }
            else None
          }
        }
      }
    }.getOrElse(Seq.empty)
  }

  override def run: Seq[MovieLink] = {
    import MovieLinkParser._
    movieExtractors.flatMap { wikiMovie =>
      jsoupParser(wikiMovie.url, jsoupQuery)
    }
  }
}
