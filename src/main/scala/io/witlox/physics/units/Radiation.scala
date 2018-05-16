package io.witlox.physics.units

import io.witlox.physics.Units.{AbsorbedDose, Bq, DoseEquivalent, Gy, IonizingRadiationActivity, Sv}
import io.witlox.physics.units.Prefix._

object Radiation extends Spoon {

  val Gy = new Gy()
  val Gray = Gy
  val TGy = Gy(tera)
  val GGy = Gy(giga)
  val MGy = Gy(mega)
  val kGy = Gy(kilo)
  val mGy = Gy(milli)
  val uGy = Gy(micro)
  val nGy = Gy(nano)
  val pGy = Gy(pico)

  val Sv = new Sv()
  val Sievert = Sv
  val TSv = Sv(tera)
  val GSv = Sv(giga)
  val MSv = Sv(mega)
  val kSv = Sv(kilo)
  val mSv = Sv(milli)
  val uSv = Sv(micro)
  val nSv = Sv(nano)
  val pSv = Sv(pico)

  val Bq = new Bq()
  val Bequerel = Bq
  val TBq = Bq(tera)
  val GBq = Bq(giga)
  val MBq = Bq(mega)
  val kBq = Bq(kilo)
  val mBq = Bq(milli)
  val uBq = Bq(micro)
  val nBq = Bq(nano)
  val pBq = Bq(pico)

  override def spoon(text: String): Option[Any] = {
    val bs = extract(text)
    if (bs.isDefined) {
      bs.get._1 match {
        case a: AbsorbedDose => Some(a * bs.get._2)
        case d: DoseEquivalent => Some(d * bs.get._2)
        case i: IonizingRadiationActivity => Some(i * bs.get._2)
        case _ => None
      }
    } else {
      None
    }
  }
}
