package io.witlox.physics.units

import io.witlox.physics.Units.{Time, s}
import io.witlox.physics.units.Prefix._

object Time extends Spoon {

  val s = new s()
  val second: s = s
  val Ts = s(tera)
  val Gs = s(giga)
  val Ms = s(mega)
  val ks = s(kilo)
  val ms = s(milli)
  val us = s(micro)
  val ns = s(nano)
  val ps = s(pico)

  val s2 = s*s
  val s3 = s*s*s

  val min = 60*s
  val h = min(60)
  val day = h(24)
  val year = day(365.25)

  override def spoon(text: String): Option[Any] = {
    val bs = extract(text)
    if (bs.isDefined) {
      bs.get._1 match {
        case t: Time => Some(t * bs.get._2)
        case _ => None
      }
    } else {
      None
    }
  }
}
