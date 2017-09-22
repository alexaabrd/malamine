package services.inventory
import models.inventory

abstract class InventoryService {
    def getInventoryItem(id:Int)
    def getInventoryBy(invType:Int = 0, color:String = "", collection:Int = 0, intention:String = "")
    
    def getComponentsByType(id:Int)
    def getUnitComponents(id:Int)
 
    def listCollections()
    def getCollection(id:Int)
    def getUnitsInCollection(id:Int)   
}