package com.github.Jsnarf.secretSanta


import courier._
import org.apache.logging.log4j.scala.Logging
import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}


object Mailing extends Logging {

  /**
    * Send an email to everyone
    *
    * @param giver
    * @param receiver
    * @param namail
    */
  def sendEmail(giver : String, receiver : String, namail: mutable.HashMap[String,String]): Unit ={
    val email = namail.get(giver).getOrElse("secretsantaperlimpinpin@gmail.com").split("@")
    if(email.length>1) {
      val (emailPre, emailSuff) = (email(0), email(1))
      val mailer = Mailer("smtp.gmail.com", 587)
        .auth(true)
        .as(ConfigSecretSanta.apply().email, ConfigSecretSanta.apply().password)
        .startTtls(true)()
      mailer(Envelope.from("secretsantaperlimpinpin" `@` "gmail.com")
        .to(emailPre `@` emailSuff)
        .subject(s"Secret Santa : ${ConfigSecretSanta.apply().subject}")
        .content(Text(ConfigSecretSanta.apply().getMessage(giver,receiver)))).onComplete {
        case Success(value) => logger.info(s"Message delivered to ${emailPre}@${emailSuff}")
        case Failure(e) => logger.error(s"Message not delivered to ${emailPre}@${emailSuff}")
          logger.warn(e.getMessage)
          e.getStackTrace.foreach(e => logger.warn(e))
      }
    } else logger.info("This is not a valid email address ! ")
  }

}
