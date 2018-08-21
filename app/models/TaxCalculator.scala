package model

import play.api.libs.json._


case class ProductDetails(productName: String, quantity: Int, unitPrice: Double)


case class ProductRequest(product: List[ProductDetails])


case class ProductResponseDetails(productName: String, quantity: Int, unitPrice: Double, salesTax: Double, totalProductPrice: Double)

case class ProductResponse(totalPrice: Double, salesTax: Double, product: List[ProductResponseDetails])


object TaxCalculator{

  implicit val productJson = Json.format[ProductDetails]
  implicit val productRequestJson = Json.format[ProductRequest]
  implicit val productResponseDetailsJson = Json.format[ProductResponseDetails]
  implicit val productResponseJson = Json.format[ProductResponse]
}