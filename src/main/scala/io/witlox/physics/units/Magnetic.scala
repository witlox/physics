package io.witlox.physics.units

import io.witlox.physics.Units.{MagneticFlux, MagneticFluxDensity, T, Wb}
import io.witlox.physics.units.Prefix._

object Magnetic extends Spoon {

  val Wb = new Wb()
  val Weber = Wb
  val TWb = Wb(tera)
  val GWb = Wb(giga)
  val MWb = Wb(mega)
  val kWb = Wb(kilo)
  val mWb = Wb(milli)
  val uWb = Wb(micro)
  val nWb = Wb(nano)
  val pWb = Wb(pico)

  val T = new T()
  val Tesla = T
  val TT = T(tera)
  val GT = T(giga)
  val MT = T(mega)
  val kT = T(kilo)
  val mT = T(milli)
  val uT = T(micro)
  val nT = T(nano)
  val pT = T(pico)

  override def spoon(text: String): Option[Any] = {
    val bs = extract(text)
    if (bs.isDefined) {
      bs.get._1 match {
        case m: MagneticFlux => Some(m * bs.get._2)
        case m: MagneticFluxDensity => Some(m * bs.get._2)
        case _ => None
      }
    } else {
      None
    }
  }
}
