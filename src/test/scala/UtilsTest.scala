package com.github.Jsnarf.secretSanta

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import org.mockito.Mockito._


class UtilsTest extends Testing {

  val mapping = collection.mutable.Map[String, String]()
  val receiver : String = "toto"
  val giver : String = "titi"
  val mockConfigSecretSanta = mock[ConfigSecretSanta]
  val forbidden = new mutable.HashMap[String,ArrayBuffer[String]]


  "condition on giver receiver" should "return true if giver and receiver are not already in the map" in {
    assert(Utils.conditionOngiverreceiver(giver, receiver,mapping))
  }

  it should "return false if receiver is already giving to giver" in {
    mapping.+=(receiver->giver)
    assert(!Utils.conditionOngiverreceiver(giver,receiver,mapping))
  }

  // TODO : Fix mockito pbs, because,it's not mocking anything

  /*
  "condition on couples" should "return true if association giver -> receiver is not forbidden" in {
    when(mockConfigSecretSanta.getMapForbidden()).thenReturn(forbidden)
    assert(Utils.conditionOnCouples(receiver,giver))
  }
  */


  it should "return false if association giver -> receiver is forbidden" in {
    val giverPeopleForbidden = new ArrayBuffer[String]()

    giverPeopleForbidden.+=:(receiver)
    forbidden.+=((giver,giverPeopleForbidden))
    when(mockConfigSecretSanta.getMapForbidden()).thenReturn(forbidden)

    assert(!Utils.conditionOnCouples(receiver,giver))
  }


}
