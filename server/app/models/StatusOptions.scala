package models.order

import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.H2Driver.api._
import scala.concurrent.{Future, ExecutionContext}
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile
import play.api.libs.json._
import play.api.libs.functional.syntax._

class StatusOptions(tag: Tag) extends Table[StatusOption](tag, "STATUSOPTIONS") {

  def id = column[Int]("ID", O.PrimaryKey)
  def statusOption = column[String]("STATUS")

  def * = (id, statusOption) <> (StatusOption.tupled, StatusOption.unapply)

}

case class StatusOption(id:Int, status:String)

object StatusOptions {

  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val statusOptions: TableQuery[StatusOptions] = TableQuery[StatusOptions]

  def listAll = {
    println(statusOptions.result.statements.head)
    dbConfig.db.run(statusOptions.result)
  }

  implicit val writes: Writes[StatusOption] = (
    (JsPath \ "id").write[Int] and
    (JsPath \ "statusOPtion").write[String]
  )(unlift(StatusOption.unapply))
}