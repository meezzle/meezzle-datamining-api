package com.meezzle.datamining.extracts.parsers

import com.meezzle.datamining.extracts._
import org.jsoup.Jsoup

import scala.collection.JavaConverters._

object MovieLinkParser {

  val jsoupQuery = "div[class=div-col columns column-width]"

}

case class MovieLinkParser(movieExtractors: Seq[JsonWikiMovieUrl])
  extends JsoupParser[Seq[WikiMovieLink]] with ParserRunnable[WikiMovieLink] {
  override protected def jsoupParser(url: String, query: String): Seq[WikiMovieLink] = {
    Option(Jsoup.connect(url).get()).map { doc =>
      doc.select(query).asScala.flatMap { obj =>
        obj.getElementsByTag("ul").asScala.flatMap { li =>
          li.getElementsByTag("i").asScala.flatMap { m =>
            if(childExist(m)) {
              Option(
                WikiMovieLink(
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

  override def run: Seq[WikiMovieLink] = {
    import MovieLinkParser._
    movieExtractors.flatMap { wikiMovie =>
      jsoupParser(wikiMovie.url, jsoupQuery)
    }
  }
}
