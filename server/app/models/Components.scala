package models.inventory

import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.H2Driver.api._
import scala.concurrent.{Future, ExecutionContext}
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

class Components(tag: Tag) extends Table[Component](tag, "COMPONENTS") {

  def id = column[Int]("ID", O.PrimaryKey)
  def name = column[String]("NAME")
  def componentType = column[Int]("TYPE")

  def * = (id, name, componentType) <> (Component.tupled, Component.unapply)

}

case class Component(id:Int, name:String, componentType:Int)

object Components {

  protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val components: TableQuery[Components] = TableQuery[Components]

  def listAll = {
    println(components.result.statements.head)
    dbConfig.db.run(components.result)
  }

}