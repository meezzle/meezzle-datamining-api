package com.meezzle.datamining.extracts

trait ParserRunnable[T <: WikiExtractor] {

  def run: Seq[T]

}
