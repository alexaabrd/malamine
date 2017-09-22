package models.inventory

import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.H2Driver.api._
import scala.concurrent.{Future, ExecutionContext}
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

class ComponentTypes(tag: Tag) extends Table[ComponentType](tag, "COMPONENTTYPES") {

  def id = column[Int]("ID", O.PrimaryKey)
  def componentType = column[String]("TYPE")

  def * = (id, componentType) <> (ComponentType.tupled, ComponentType.unapply)

}

case class ComponentType(id:Int, componentType:String)

object ComponentTypes {

  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val componentTypes: TableQuery[ComponentTypes] = TableQuery[ComponentTypes]

  def listAll = {
    println(componentTypes.result.statements.head)
    dbConfig.db.run(componentTypes.result)
  }

}