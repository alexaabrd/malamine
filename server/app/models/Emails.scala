package models.user

import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.H2Driver.api._
import scala.concurrent.{Future, ExecutionContext}
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

class Emails(tag: Tag) extends Table[Email](tag, "EMAILS") {

  def id = column[Int]("ID", O.PrimaryKey)
  def email = column[String]("EMAIL")
  def subscribed = column[Char]("SUBSCRIBED")

  def * = (id, email, subscribed) <> (Email.tupled, Email.unapply)

}

case class Email(id:Int, email:String, subscribed:Char)

object Emails {

  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val emails: TableQuery[Emails] = TableQuery[Emails]

  def listAll = {
    println(emails.result.statements.head)
    dbConfig.db.run(emails.result)
  }

}