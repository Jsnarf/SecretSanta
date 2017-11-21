package com.github.Jsnarf.secretSanta


import org.apache.logging.log4j.scala.Logging

import scala.util.Random


object Treatment extends Logging {

  /**
    * Real treatment, adding to a map, the good mapping found (i.e : giver -> receiver)
    *
    * @param listReceiver
    * @param giver
    * @param mapping
    * @return
    */
  def treatment(listReceiver: java.util.ArrayList[String], giver : String, mapping : collection.mutable.Map[String,String]) : Boolean = {
    var success: Boolean = false
    var count: Int = 0
    while (!success && count < 100) {
      val random = new Random().nextInt(listReceiver.size)
      val receiver = listReceiver.get(random)
      if (!receiver.equalsIgnoreCase(giver) && Utils.conditionOnCouples(receiver, giver) && Utils.conditionOngiverreceiver(giver,receiver, mapping)) {
        mapping += (giver -> receiver)
        listReceiver.remove(random)
        success = true
      }
      count = count + 1
    }
    success
  }

}
