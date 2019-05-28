package com.meezzle.datamining.extracts.parsers

import com.meezzle.datamining.extracts._
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

import scala.collection.JavaConverters._

object WikiMovieLinkParser {

  val jsoupQuery = "div[class=div-col columns column-width]"

}

case class WikiMovieLinkParser(movieExtractors: Seq[JsonWikiMovieUrl])
  extends JsoupParser[WikiMovieLink] with ParserRunnable[WikiMovieLink] {
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
    import WikiMovieLinkParser._
    movieExtractors.flatMap { wikiMovie =>
      jsoupParser(wikiMovie.url, jsoupQuery)
    }
  }
}

object WikiMovieDataParser {

  val jsoupQuery = "table[class=infobox vevent]"

}

case class WikiMovieDataParser(wikiMovieLink: WikiMovieLink)
  extends JsoupParser[WikiMovieData] with ParserRunnable[WikiMovieData] {
  override protected def jsoupParser(url: String, query: String): Seq[WikiMovieData] = {
    Option(Jsoup.connect(url).get()).map { doc =>
      doc.select(query).asScala.map { obj =>
          jsoupParser(obj)
      }
    }
    Seq.empty
  }

  protected def jsoupParser(el: Element) = {
    val td = el.getElementsByTag("td").asScala

  }


  override def run: Seq[WikiMovieData] = {
    import WikiMovieDataParser._
    jsoupParser(wikiMovieLink.url, jsoupQuery)
  }
}