package io.witlox.physics.units

import io.witlox.physics.Units.{Length, m}
import io.witlox.physics.units.Prefix._

object Length extends Spoon {

  val m = new m()
  val meter: m = m
  val Tm = m(tera)
  val Gm = m(giga)
  val Mm = m(mega)
  val km = m(kilo)
  val dm = m(deci)
  val cm = m(centi)
  val mm = m(milli)
  val um = m(micro)
  val nm = m(nano)
  val pm = m(pico)

  override def spoon(text: String): Option[Any] = {
    val bs = extract(text)
    if (bs.isDefined) {
      bs.get._1 match {
        case l: Length => Some(l * bs.get._2)
        case _ => None
      }
    } else {
      None
    }
  }
}
