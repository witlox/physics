package io.witlox.physics.units

import io.witlox.physics.Units.{K, Temperature}
import io.witlox.physics.units.Prefix._

object Temperature extends Spoon {

  val K = new K()
  val Kelvin = K
  val TK = K(tera)
  val GK = K(giga)
  val MK = K(mega)
  val kK = K(kilo)
  val mK = K(milli)
  val uK = K(micro)
  val nK = K(nano)
  val pK = K(pico)

  private val zeroCelsiusInKelvin = 273.15

  val zeroCelsius = new Temperature(zeroCelsiusInKelvin)

  def fromCelsius(value: Double): Temperature = new Temperature(value + zeroCelsiusInKelvin)
  def toCelsius(temperature: Temperature): Double = temperature.value - zeroCelsiusInKelvin

  override def spoon(text: String): Option[Any] = {
    val bs = extract(text)
    if (bs.isDefined) {
      bs.get._1 match {
        case t: Temperature => Some(t * bs.get._2)
        case _ => None
      }
    } else {
      None
    }
  }
}
