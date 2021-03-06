package models.order

import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.H2Driver.api._
import scala.concurrent.{Future, ExecutionContext}
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

class UnitSoldMapping(tag: Tag) extends Table[UnitSoldMap](tag, "UNITSOLDMAPPING") {

  def orderID = column[Int]("ORDERID")
  def inventoryID = column[Int]("INVENTORYID")
  def amountPaid = column[Double]("AMOUNTPAID")
  def status = column[Int]("STATUS")
  
  def * = (orderID, inventoryID, amountPaid, status) <> (UnitSoldMap.tupled, UnitSoldMap.unapply)

}

case class UnitSoldMap(orderID:Int, inventoryID:Int, amountPaid:Double, status:Int)

object UnitSoldMapping {

  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val unitSoldMapping: TableQuery[UnitSoldMapping] = TableQuery[UnitSoldMapping]

  def listAll = {
    println(unitSoldMapping.result.statements.head)
    dbConfig.db.run(unitSoldMapping.result)
  }

}