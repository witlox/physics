package io.witlox.physics.units

import io.witlox.physics.Units.{One, PlaneAngle, rad}
import io.witlox.physics.units.Prefix._

import scala.math.Pi

object Angle extends Spoon {

  val rad = new rad()
  val radian: rad = rad
  val Trad = rad(tera)
  val Grad = rad(giga)
  val Mrad = rad(mega)
  val krad = rad(kilo)
  val mrad = rad(milli)
  val urad = rad(micro)
  val nrad = rad(nano)
  val prad = rad(pico)

  val unitlessOne = new One()

  val Tau: Double = 2*Pi
  val turns = new rad(Tau)
  val deg = new rad(Tau/360.0)

  override def spoon(text: String): Option[Any] = {
    val bs = extract(text)
    if (bs.isDefined) {
      bs.get._1 match {
        case a: PlaneAngle => Some(a * bs.get._2)
        case _ => None
      }
    } else {
      None
    }
  }
}
