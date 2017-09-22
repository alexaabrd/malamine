package models.inventory

import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.H2Driver.api._
import scala.concurrent.{Future, ExecutionContext}
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

class ComponentMapping(tag: Tag) extends Table[ComponentMap](tag, "COMPONENTMAPPING") {

  def componentID = column[Int]("COMPONENTID")
  def inventoryID = column[Int]("INVENTORYID")

  def * = (componentID, inventoryID) <> (ComponentMap.tupled, ComponentMap.unapply)

}

case class ComponentMap(componentID:Int, inventoryID:Int)

object ComponentMapping {

  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val componentMapping: TableQuery[ComponentMapping] = TableQuery[ComponentMapping]

  def listAll = {
    println(componentMapping.result.statements.head)
    dbConfig.db.run(componentMapping.result)
  }

}