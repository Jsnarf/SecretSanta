package com.github.Jsnarf.secretSanta


class TreatmentTest extends Testing {

  val giver = "titi"
  val receiver = "toto"
  val mapping = collection.mutable.Map[String,String]()

  "treatment" should "send back false with giver who cannot give to the receiver" in  {
    val listReceiver = new java.util.ArrayList[String]()
    listReceiver.add(receiver)
    assert(!Treatment.treatment(listReceiver,giver,mapping))
  }

  it should "send back true with giver that can give to the receiver" in {
    val listReceiver = new java.util.ArrayList[String]()
    listReceiver.add(giver)
    assert(Treatment.treatment(listReceiver,receiver,mapping))
  }

  it should "send back false if giver and receiver are the same" in {
    val listReceiver = new java.util.ArrayList[String]()
    listReceiver.add(receiver)
    assert(!Treatment.treatment(listReceiver,giver,mapping))
  }

}
