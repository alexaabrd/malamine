package scalajs

import org.scalajs.jquery.{jQuery=>$}
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLElement
import dom.document
import scala.scalajs.js
import scala.scalajs.js.Dynamic.global
import scala.scalajs.js
import js.annotation._
import scala.scalajs.js.annotation._

object Start extends js.JSApp {
  def main(): Unit = {
    if (global.console != null) {
      global.console.log("Welcome to MalaMine")
    }

  }


@JSExportTopLevel("showShop")
    def showShop() = {
        document.querySelector(".shop-menu").asInstanceOf[HTMLElement].style.display = "block"
    }

@JSExportTopLevel("hideShop")     
    def hideShop() = {
        dom.document.querySelector(".shop-menu").asInstanceOf[HTMLElement].style.display = "none"
    }
    
@JSExportTopLevel("scroll")
    def scroll() = {
        var $el = dom.document.querySelector(".top-nav").asInstanceOf[HTMLElement];
        var $el2 = dom.document.querySelector(".scrollable").asInstanceOf[HTMLElement];
        var isPositionFixed = ($el.style.position == "fixed")
        global.console.log(isPositionFixed + " ")
        var scrollTop = dom.window.pageYOffset
        global.console.log(scrollTop)
        if (scrollTop > 25.00 && !isPositionFixed) {
        $el.style.position = "fixed"
        $el.style.top = "0px" 
        } else { if (scrollTop <= 25.00 && isPositionFixed) {
        $el.style.position = "absolute"
        $el.style.top = "25px";  }
        }
  }
}