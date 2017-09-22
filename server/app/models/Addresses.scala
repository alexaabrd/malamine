package models.user

import slick.driver.H2Driver.api._
import scala.concurrent.{Future, ExecutionContext}
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

class Addresses(tag: Tag) extends Table[Address](tag, "ADDRESSES") {

  def id = column[Int]("ID", O.PrimaryKey)
  def street1 = column[String]("STREET1")
  def street2 = column[String]("STREET2")
  def city = column[String]("CITY")
  def state = column[String]("STATE")
  def zip = column[String]("ZIP")

  def * = (id, street1, street2, city, state, zip) <> (Address.tupled, Address.unapply)

}

case class Address(id: Int, street1: String, street2: String, city: String, state:String, zip: String)

object Addresses {

  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val addresses: TableQuery[Addresses] = TableQuery[Addresses]

  def listAll:Future[Seq[Address]] = {
    dbConfig.db.run(addresses.result)
  }

}