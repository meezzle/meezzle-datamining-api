package com.meezzle.datamining.dbs.jdbc

import com.meezzle.datamining.dbs.PostgresDBSourceBuilder
import com.meezzle.datamining.dbs.sources.{DBSource, DBSourceBuilder}

abstract class JDBConnection[T <: DBSourceBuilder](DBSource: DBSource[T]) {



}

class PostgresConnection(dbSource: DBSource[PostgresDBSourceBuilder])
  extends JDBConnection(dbSource) {

}
