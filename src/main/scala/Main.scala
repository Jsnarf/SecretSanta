package com.github.Jsnarf.secretSanta

import scala.collection.mutable
import org.apache.logging.log4j.scala.Logging


object Main extends App with Logging {

  val namail = ConfigSecretSanta.apply().getMap()
  var failed = true
  var mapping = collection.mutable.Map[String, String]()
  var appCounter = 0

  while (failed && appCounter < 100) {

    // Initialization
    failed = false
    mapping.clear()
    var listgiver = new mutable.MutableList[String]()
    var listReceiver = new java.util.ArrayList[String]()
    namail.foreach(mapName => {
      listgiver.+=:(mapName._1)
      listReceiver.add(mapName._1)
    })

    // Mapping giver -> receiver
    listgiver.foreach(giver => {
      if(!Treatment.treatment(listReceiver,giver,mapping)) failed = true
    })

    appCounter+=1
  }

  // Sending emails if not failed
  if(failed) logger.error(" A probleme occured, a solution cannot be found ! ")
  else {
    mapping.foreach(r => {
     Mailing.sendEmail(r._1, r._2,namail)
    })
  }
}

// TODO : fix runtime warning (WARN Unable to instantiate org.fusesource.jansi.WindowsAnsiOutputStream)
