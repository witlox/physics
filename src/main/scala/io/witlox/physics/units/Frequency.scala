package io.witlox.physics.units

import io.witlox.physics.Units.{Frequency, Hz}
import io.witlox.physics.units.Prefix._

object Frequency extends Spoon {

  val Hz = new Hz()
  val Hertz = Hz
  val THz = Hz(tera)
  val GHz = Hz(giga)
  val MHz = Hz(mega)
  val kHz = Hz(kilo)
  val mHz = Hz(milli)
  val uHz = Hz(micro)
  val nHz = Hz(nano)
  val pHz = Hz(pico)

  override def spoon(text: String): Option[Any] = {
    val bs = extract(text)
    if (bs.isDefined) {
      bs.get._1 match {
        case a: Frequency => Some(a * bs.get._2)
        case _ => None
      }
    } else {
      None
    }
  }
}
