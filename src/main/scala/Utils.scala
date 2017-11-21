package com.github.Jsnarf.secretSanta

import org.apache.logging.log4j.scala.Logging
import scala.collection.mutable.ArrayBuffer


object Utils extends Logging  {

  /**
    * Conditions on one giver cannot give to a person who is already giving him
    * @param giver
    * @param receiver
    * @param mapping
    * @return
    */
  def conditionOngiverreceiver(giver: String,receiver: String, mapping: collection.mutable.Map[String, String]): Boolean ={
    if(mapping.get(receiver).getOrElse("").equalsIgnoreCase(giver)) false else true
  }

  /**
    * Conditions on one giver cannot give to a bunch of people
    * @param receiver
    * @param giver
    * @return
    */
  def conditionOnCouples(receiver: String, giver: String): Boolean = {
    ConfigSecretSanta.apply().getMapForbidden()
    .get(giver).getOrElse(new ArrayBuffer[String]())
      .foreach(forbiddenPeople => {
        if(receiver.equalsIgnoreCase(forbiddenPeople)) return false
      })
    true
  }



}
