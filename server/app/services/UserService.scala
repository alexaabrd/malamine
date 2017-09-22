package services.user
import models.user

abstract class UserService {
    def getUser(id:Int)
    def addUser(email:String, password:String)
    def removeUser(id:Int)
    def updateEmail(id:Int, email:String)
    def updateFirst(id:Int, first:String)
    def updateLast(id:Int, last:String)
    def updateUserAddress(id:Int, street1:String, street2:String, city:String, state:String, zip:String, country:String)

    def lookupAddress(street1:String, street2:String, city:String, state:String, zip:String, country:String):Int
    def addAddress(id:Int, street1:String, street2:String, city:String, state:String, zip:String, country:String)
    
    def authenticate(email:String, password:String)
    def login(email:String)
    def logout()

}