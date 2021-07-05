package models

case class Rubles(c: Double)

object Rubles {

  import play.api.libs.json._

  implicit object RublesFormat extends Format[Rubles] {

    def reads(json: JsValue): JsResult[Rubles] = ???

    def writes(r: Rubles): JsValue =
      JsObject(Seq("result" -> JsNumber(r.c)))
  }
}

