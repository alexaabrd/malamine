package models.inventory

import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.H2Driver.api._
import scala.concurrent.{Future, ExecutionContext}
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

class Collections(tag: Tag) extends Table[Collection](tag, "COLLECTIONS") {

  def id = column[Int]("ID", O.PrimaryKey)
  def name = column[String]("NAME")
  def description = column[String]("DESCRIPTION")

  def * = (id, name, description) <> (Collection.tupled, Collection.unapply)

}

case class Collection(id:Int, name:String, description:String)

object Collections {

  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val collections: TableQuery[Collections] = TableQuery[Collections]

  def listAll = {
    println(collections.result.statements.head)
    dbConfig.db.run(collections.result)
  }

}