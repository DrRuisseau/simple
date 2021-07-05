import controllers.Application
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class ApplicationSpec extends PlaySpec with Results {

  "Example Page#index" should {
    "/ (index) should be valid" in {
      val controller             = new Application(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.index().apply(FakeRequest())
      val bodyText: String       = contentAsString(result)
      bodyText mustBe "\"Hi!\""
    }

    "/convert?to=USD&number=0" in {
      val controller             = new Application(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.getConvert("USD", 0).apply(FakeRequest())
      val bodyText: String       = contentAsString(result)
      bodyText mustBe "{\"result\":0}"
    }

    "/convert?to=&number=0" in {
      val controller             = new Application(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.getConvert("", 0).apply(FakeRequest())
      val bodyText: String       = contentAsString(result)
      bodyText mustBe "\"Haven't this currency in csv\""
    }
  }
}