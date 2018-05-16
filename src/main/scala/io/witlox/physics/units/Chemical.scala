package io.witlox.physics.units

import io.witlox.physics.Units.{AmountOfSubstance, CatalyticActivity, kat, mol}
import io.witlox.physics.units.Prefix._

object Chemical extends Spoon {

  val mol  = new mol()
  val Tmol= mol(tera)
  val Gmol = mol(giga)
  val Mmol = mol(mega)
  val kmol = mol(kilo)
  val mmol = mol(milli)
  val umol = mol(micro)
  val nmol = mol(nano)
  val pmol = mol(pico)

  val kat = new kat()
  val Tkat = kat(tera)
  val Gkat = kat(giga)
  val Mkat = kat(mega)
  val kkat = kat(kilo)
  val mkat = kat(milli)
  val ukat = kat(micro)
  val nkat = kat(nano)
  val pkat= kat(pico)

  override def spoon(text: String): Option[Any] = {
    val bs = extract(text)
    if (bs.isDefined) {
      bs.get._1 match {
        case a: AmountOfSubstance => Some(a * bs.get._2)
        case k: CatalyticActivity => Some(k * bs.get._2)
        case _ => None
      }
    } else {
      None
    }
  }

}
