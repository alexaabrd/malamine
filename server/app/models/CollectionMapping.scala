package models.inventory

import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.H2Driver.api._
import scala.concurrent.{Future, ExecutionContext}
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

class CollectionMapping(tag: Tag) extends Table[CollectionMap](tag, "COLLECTIONMAPPING") {

  def collectionID = column[Int]("COLLECTIONID")
  def inventoryID = column[Int]("INVENTORYID")

  def * = (collectionID, inventoryID) <> (CollectionMap.tupled, CollectionMap.unapply)

}

case class CollectionMap(collectionID:Int, inventoryID:Int)

object CollectionMapping {

  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val collectionMapping: TableQuery[CollectionMapping] = TableQuery[CollectionMapping]

  def listAll = {
    println(collectionMapping.result.statements.head)
    dbConfig.db.run(collectionMapping.result)
  }

}