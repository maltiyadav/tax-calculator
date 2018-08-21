import controllers.TaxCalculatorController
import org.scalatestplus.play._
import play.api.libs.json.{JsValue, Json}
import play.api.test.FakeRequest
import play.api.test.Helpers._
import services.TaxCalculatorService


class TaxCalculatorControllerTest extends PlaySpec{

  val taxCalculatorService: TaxCalculatorService = new TaxCalculatorService()

  val controller = new TaxCalculatorController(taxCalculatorService)


  private def taxCalFakeRequest(payload: JsValue): FakeRequest[JsValue] = {

    val request = FakeRequest(POST, "/taxCalculator").withBody(payload)
      .withHeaders(
        "Content-Type" -> "application/json"
      )
    request
  }

  "Calculate the tax of product and product does not have medicine product" should {
    "return OK response" in {
      val payload = Json.parse(s"""
        {
          "product": [{
              "productName": "Book",
              "quantity": 1,
              "unitPrice": 100
            },
            {
              "productName": "Chocolate Snacks",
              "quantity": 1,
              "unitPrice": 100
            }
          ]
        }""")

      val request = taxCalFakeRequest(payload)
      val result = controller.calculateTax.apply(request)
      val jsonResponse = Json.parse(contentAsString(result))
      println("Json Response>>>>>"+jsonResponse)
      (jsonResponse \ "totalPrice").as[Int] mustBe 240
      (jsonResponse \ "salesTax").as[Int] mustBe 40
    }
  }

  "Calculate the tax of product and product have medicine product" should {
    "return OK response" in {
      val payload = Json.parse(s"""
        {
          "product": [{
              "productName": "Book",
              "quantity": 1,
              "unitPrice": 100
            },
            {
              "productName": "Box of Tooth Ache Pills",
              "quantity": 1,
              "unitPrice": 100
            }
          ]
        }""")

      val request = taxCalFakeRequest(payload)
      val result = controller.calculateTax.apply(request)
      val jsonResponse = Json.parse(contentAsString(result))
      println("Json Response>>>>>"+jsonResponse)
      (jsonResponse \ "totalPrice").as[Int] mustBe 220
      (jsonResponse \ "salesTax").as[Int] mustBe 20
    }
  }


}