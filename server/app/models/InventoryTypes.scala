package models.inventory

import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.H2Driver.api._
import scala.concurrent.{Future, ExecutionContext}
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

class InventoryTypes(tag: Tag) extends Table[InventoryType](tag, "INVENTORYTYPES") {

  def id = column[Int]("ID", O.PrimaryKey)
  def inventoryType = column[String]("TYPE")

  def * = (id, inventoryType) <> (InventoryType.tupled, InventoryType.unapply)

}

case class InventoryType(id:Int, inventoryType:String)

object InventoryTypes {

  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val inventoryTypes: TableQuery[InventoryTypes] = TableQuery[InventoryTypes]

  def listAll = {
    println(inventoryTypes.result.statements.head)
    dbConfig.db.run(inventoryTypes.result)
  }

}