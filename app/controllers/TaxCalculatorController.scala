package controllers

import javax.inject._
import play.api.mvc._
import services.TaxCalculatorService

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import model._
import model.TaxCalculator._
import play.api.libs.json._

@Singleton
class TaxCalculatorController @Inject()(taxCalculatorService: TaxCalculatorService) extends Controller {

  def calculateTax = Action.async(parse.json){ implicit request =>
  
    val productReq = request.body.as[ProductRequest]
	val respose = taxCalculatorService.calculateTax(productReq.product)

    Future{ Ok(Json.toJson(respose)) }

  }

 }
