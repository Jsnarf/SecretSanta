package com.github.Jsnarf.secretSanta


class ConfigSecretSantaTest extends Testing {

  val receiver : String = "toto"
  val giver : String = "titi"
  val giverMail : String = "titi@gmail.com"
  val receiverMail : String = "toto@gmail.com"

  "getMessage response" should "have name receiver and name giver in its message" in {
    assert(ConfigSecretSanta.apply().getMessage(giver,receiver).contains(giver))
    assert(ConfigSecretSanta.apply().getMessage(giver,receiver).contains(receiver))
  }

  it should "equals the message in configuration with replacement of values" in {
    assert(ConfigSecretSanta.apply().getMessage(giver,receiver).equalsIgnoreCase(giver + " " + receiver))
  }

  "getmap response" should "contains emails passed by configuration" in {
    assert(ConfigSecretSanta.apply().getMap().get(giver).get.equalsIgnoreCase(giverMail))
    assert(ConfigSecretSanta.apply().getMap().get(receiver).get.equalsIgnoreCase(receiverMail))
  }

  "getMapForbidden" should "contains constraints made in configuration" in {
    assert(ConfigSecretSanta.apply().getMapForbidden().get(giver).get.size==1)
    assert(ConfigSecretSanta.apply().getMapForbidden().get(giver).get.indexOf(receiver)==0)
  }

}
