package io.witlox.physics.units

import io.witlox.physics.Units.{Pa, Pressure}
import io.witlox.physics.units.Prefix._

object Pressure extends Spoon {

  val Pa = new Pa()
  val Pascal = Pa
  val TPa = Pa(tera)
  val GPa = Pa(giga)
  val MPa = Pa(mega)
  val kPa = Pa(kilo)
  val hPa = Pa(hecto)
  val mPa = Pa(milli)
  val uPa = Pa(micro)
  val nPa = Pa(nano)
  val pPa = Pa(pico)

  val bar = Pa(1e5)
  val mbar = hPa
  val atm = mbar(1013.25)

  override def spoon(text: String): Option[Any] = {
    val bs = extract(text)
    if (bs.isDefined) {
      bs.get._1 match {
        case p: Pressure => Some(p * bs.get._2)
        case _ => None
      }
    } else {
      None
    }
  }
}
