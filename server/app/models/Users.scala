package models.user

import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.H2Driver.api._
import scala.concurrent.{Future, ExecutionContext}
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

class Users(tag: Tag) extends Table[User](tag, "USERS") {

  def id = column[Int]("ID", O.PrimaryKey)
  def first = column[String]("FIRST")
  def last = column[String]("LAST")
  def emailID = column[Int]("EMAILID")
  def password = column[String]("PASSWORD")
  def address = column[Int]("ADDRESSID")

  def * = (id, first, last, emailID, password, address.?) <> (User.tupled, User.unapply)

}

case class User(id:Int, first:String, last:String, emailID:Int, password:String, address:Option[Int])

object Users {

  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val users: TableQuery[Users] = TableQuery[Users]

  def listAll = {
    println(users.result.statements.head)
    dbConfig.db.run(users.result)
  }

}