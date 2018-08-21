package services
import model._

class TaxCalculatorService{

  val listOfMedicalProduct = List("Box of Tooth Ache Pills")

  def isMedicalProduct(productName: String) = {
    List(productName).exists(listOfMedicalProduct.contains)
  }

  def calculateTax(productList: List[ProductDetails]) = {

    val productDel = productList map { product =>
      val productPrice = product.quantity * product.unitPrice
      val salesTax = if(isMedicalProduct(product.productName)){
        0
      } else {
        (productPrice * 20) / 100
      }
      val totalProductPrice = productPrice + salesTax
      ProductResponseDetails(product.productName, product.quantity, product.unitPrice, salesTax, totalProductPrice)
    }
    val totalSalesTax = productDel.map(_.salesTax).sum
    val totalPrice = productDel.map(_.totalProductPrice).sum
    ProductResponse(totalPrice, totalSalesTax, productDel)

  }

}
