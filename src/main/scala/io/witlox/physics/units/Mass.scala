package io.witlox.physics.units

import io.witlox.physics.Units.{Mass, kg}
import io.witlox.physics.units.Prefix._

object Mass extends Spoon {

  val g = new kg(1e-3)
  val gram: kg = g
  val Tg = g(tera)
  val Gg = g(giga)
  val Mg = g(mega)
  val kg = g(kilo)
  val mg = g(milli)
  val ug = g(micro)
  val ng = g(nano)
  val pg = g(pico)

  val tonne = kg(1000)

  override def spoon(text: String): Option[Any] = {
    val bs = extract(text)
    if (bs.isDefined) {
      bs.get._1 match {
        case m: Mass => Some(m * bs.get._2)
        case _ => None
      }
    } else {
      None
    }
  }
}
