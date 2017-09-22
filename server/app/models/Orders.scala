package models.order

import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.H2Driver.api._
import scala.concurrent.{Future, ExecutionContext}
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

class Orders(tag: Tag) extends Table[Order](tag, "ORDERS") {

  def id = column[Int]("ID", O.PrimaryKey)
  def emailID = column[Int]("EMAILID")
  def totalCost = column[Double]("TOTALCOST")
  def shippingAddress = column[Int]("SHIPPINGADDRESS")
  def status = column[Int]("STATUS")
  def trackingNumber = column[String]("TRACKINGNUM")
  def date = column[String]("DATE")
  def paymentMethod = column[String]("PAYMENTMETHOD")

  def * = (id, emailID, totalCost, shippingAddress, status, trackingNumber.?, date, paymentMethod) <> (Order.tupled, Order.unapply)

}

case class Order(id:Int, emailID:Int, totalCost:Double, shippingAddress:Int, status:Int, trackingNumber:Option[String], date:String, paymentMethod:String)

object Orders {

  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val orders: TableQuery[Orders] = TableQuery[Orders]

  def listAll = {
    println(orders.result.statements.head)
    dbConfig.db.run(orders.result)
  }

}