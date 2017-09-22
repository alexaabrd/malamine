package services.order
import models.order

abstract class OrderService {
    def getOrder(id:Int)
    def getUserOrders(id:Int)
    def getUnitsSoldPerOrder(id:Int)
    def addOrder(emailID:Int, cost:Double, addressID:Int, paymentMethod:String)
}