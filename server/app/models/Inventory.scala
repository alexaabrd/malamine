package models.inventory

import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.H2Driver.api._
import scala.concurrent.{Future, ExecutionContext}
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

class Inventory(tag: Tag) extends Table[InventoryItem](tag, "INVENTORY") {

  def id = column[Int]("ID", O.PrimaryKey)
  def name = column[String]("NAME")
  def description = column[String]("DESCRIPTION")
  def inventoryType = column[Int]("TYPE")
  def cost = column[Double]("COST")
  def inStock = column[Char]("INSTOCK")

  def * = (id, name, description, inventoryType, cost, inStock) <> (InventoryItem.tupled, InventoryItem.unapply)

}

case class InventoryItem(id:Int, name:String, description:String, inventoryType:Int, cost:Double, inStock:Char)

object Inventory {

  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val inventory: TableQuery[Inventory] = TableQuery[Inventory]

  def listAll = {
    println(inventory.result.statements.head)
    dbConfig.db.run(inventory.result)
  }

}