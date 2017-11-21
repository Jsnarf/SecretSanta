package com.github.Jsnarf.secretSanta

import com.typesafe.config.ConfigFactory
import org.apache.logging.log4j.scala.Logging
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class ConfigSecretSanta() extends Logging {

  val configuration = ConfigFactory.load()

  val appname = configuration.getString("app-name")

  val personList = configuration.getStringList("namailList")
  val forbidden = configuration.getStringList("forbidden")

  val password = configuration.getString("password")
  val email = configuration.getString("email")
  val subject = configuration.getString("subject")
  val message = configuration.getString("message")

  /**
    * Create a message according to conf with name of receiver and giver
    *
    * @param giver
    * @param receiver
    * @return
    */
  def getMessage(giver : String, receiver: String): String = {
    message.replace("name_giver",s"$giver").replace("name_receiver", s"$receiver")
  }

  /**
    * Get a map of giver -> emailAddress
    * @return
    */
  def getMap() : mutable.HashMap[String,String] = {
    val nameMail = new mutable.HashMap[String,String]()
    personList.forEach(person => {
      val separated = person.split(":")
      nameMail.+=((separated(0),separated(1)))
    })
    nameMail
  }

  /**
    * Get a map of forbidden people : giver cannot give to this List of person
    *
    * @return
    */
  def getMapForbidden() : mutable.HashMap[String,ArrayBuffer[String]] = {
    val peopleForbidden = new mutable.HashMap[String,ArrayBuffer[String]]()
    forbidden.forEach(person => {
      val separated = person.split(":")
      if(separated.length> 1) {
        val names = separated(1).split(",")
        val listNames = new mutable.ArrayBuffer[String](names.length)
        names.foreach(name => listNames.+=(name))
        peopleForbidden.+=((separated(0), listNames))
      }
    })
    peopleForbidden
  }

}

object ConfigSecretSanta {
  private val configSecretSanta = new ConfigSecretSanta()

  def apply() = configSecretSanta
}
