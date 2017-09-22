package controllers

import javax.inject._
import scala.concurrent.{Future, ExecutionContext}
import models.user._
import play.api.mvc._
import play.api.libs.json.{Json, Writes}
import play.api.libs.json.Json
import play.api.libs.json._
import ExecutionContext.Implicits.global
import scala.util.{Success, Failure}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class APIcontroller extends Controller {


    def convertTweetsToJsonOrig(tweets: Seq[User]): JsValue = {
        Json.toJson(
            tweets.map { t =>
                Map( "tweet" -> t.id)
            }
        )
    }

    def response: Action[AnyContent] = Action.async {  request => 
        {
        val x:Future[Seq[User]] = Users.listAll
        x.map(i => Ok(convertTweetsToJsonOrig(i)))
        //x onComplete {
        //case Success(posts) => println(convertTweetsToJsonOrig(posts))
        //case Failure(t) => println("An error has occured: " + t.getMessage)
        //}
          }
    }
    
    def option(id:Int) = Action {
        if (id == 1) {
            Ok(views.html.option1());
        } else {
            Ok(views.html.option2());
        }
    }
    
    def index() = Action { 
        Ok(views.html.index());
    }
 
    def about() = Action { 
        Ok(views.html.about())
    }
 
    def shop() = Action { 
        Ok(views.html.shop.shop())
    }
    
    def create() = Action { 
        Ok(views.html.create())
    }
    
    def inspiration() = Action { 
        Ok(views.html.inspiration())
    }
    
    def learn() = Action {
        Ok(views.html.learn())
    }
    
    def todo = TODO
  
}