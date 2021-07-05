package controllers

import play.mvc.Controller
import play.api._
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import javax.inject.Inject
import scala.Console.println
import scala.io.Source.fromFile
import scala.util.Try
//import play.api.mvc.Action
import play.api.libs.json._
import models.Rubles
import play.api.mvc.Results.Ok

class Application @Inject()(cc: ControllerComponents) extends AbstractController(cc){
  def index = Action {
//    Ok(Json.toJson("Hi!" ++ (fromFile("C:/Users/asd/IdeaProjects/untitled/simple/tmp/currencysRate.csv")))) // just for debugging
    Ok(Json.toJson("Hi!"))
  }

  def getConvert(to: String, number: Int) = Action {
    currencyConvertation(to, number) match {
      case None => Ok(Json.toJson("Haven't this currency in csv"))
      case    v => Ok(Json.toJson(Rubles(v.get)))
    }
  }

  def currencyConvertation(to: String, number: Int) : Option[Double] = {
    val bufferedSource = Try(fromFile("C:/Users/asd/IdeaProjects/untitled/simple/tmp/currencysRate.csv")).toOption

    if (bufferedSource.isEmpty) return None

    val res =
      for (line <- bufferedSource.get.getLines();
           Array(crrncy, rate) = line.split(";").map(_.trim)
           if (crrncy == to))
      yield (rate)

    Try(res.nextOption().get.toDouble * number).toOption
  }
}
