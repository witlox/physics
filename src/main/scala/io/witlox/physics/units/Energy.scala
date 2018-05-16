package io.witlox.physics.units

import io.witlox.physics.Units.{A, C, Capacitance, Conductance, Current, ElectricCharge, ElectricResistance, Energy, F, Force, J, N, Ohm, Power, S, V, Voltage, W}
import io.witlox.physics.units.Prefix._

object Energy extends Spoon {

  val N = new N()
  val Newton = N
  val TN = N(tera)
  val GN = N(giga)
  val MN = N(mega)
  val kN = N(kilo)
  val mN = N(milli)
  val uN = N(micro)
  val nN = N(nano)
  val pN = N(pico)

  val A = new A()
  val Ampere = A
  val TA = A(tera)
  val GA = A(giga)
  val MA = A(mega)
  val kA = A(kilo)
  val mA = A(milli)
  val uA = A(micro)
  val nA = A(nano)
  val pA = A(pico)

  val V = new V()
  val Volt = V
  val TV = V(tera)
  val GV = V(giga)
  val MV = V(mega)
  val kV = V(kilo)
  val mV = V(milli)
  val uV = V(micro)
  val nV = V(nano)
  val pV = V(pico)

  val J = new J()
  val Joule = J
  val TJ = J(tera)
  val GJ = J(giga)
  val MJ = J(mega)
  val kJ = J(kilo)
  val mJ = J(milli)
  val uJ = J(micro)
  val nJ = J(nano)
  val pJ = J(pico)

  val W = new W()
  val Watt = W
  val TW = W(tera)
  val GW = W(giga)
  val MW = W(mega)
  val kW = W(kilo)
  val mW = W(milli)
  val uW = W(micro)
  val nW = W(nano)
  val pW = W(pico)

  val C = new C()
  val Coulomb = C
  val TC = C(tera)
  val GC = C(giga)
  val MC = C(mega)
  val kC = C(kilo)
  val mC = C(milli)
  val uC = C(micro)
  val nC = C(nano)
  val pC = C(pico)

  val S = new S()
  val Siemens = S
  val TS = S(tera)
  val GS = S(giga)
  val MS = S(mega)
  val kS = S(kilo)
  val mS = S(milli)
  val uS = S(micro)
  val nS = S(nano)
  val pS = S(pico)

  val F = new F()
  val Farad = F
  val TF = F(tera)
  val GF = F(giga)
  val MF = F(mega)
  val kF = F(kilo)
  val mF = F(milli)
  val uF = F(micro)
  val nF = F(nano)
  val pF = F(pico)

  val Ohm = new Ohm()
  val TOhm = Ohm(tera)
  val GOhm = Ohm(giga)
  val MOhm = Ohm(mega)
  val kOhm = Ohm(kilo)
  val mOhm = Ohm(milli)
  val uOhm = Ohm(micro)
  val nOhm = Ohm(nano)
  val pOhm = Ohm(pico)

  val cal = J(4.184)
  val Tcal = cal(tera)
  val Gcal = cal(giga)
  val Mcal = cal(mega)
  val kcal = cal(kilo)
  val mcal = cal(milli)
  val ucal = cal(micro)
  val ncal = cal(nano)
  val pcal= cal(pico)

  val ElectronVolt = J(1.60217733e-19)
  val eV = ElectronVolt

  override def spoon(text: String): Option[Any] = {
    val bs = extract(text)
    if (bs.isDefined) {
      bs.get._1 match {
        case f: Force => Some(f * bs.get._2)
        case c: Current => Some(c * bs.get._2)
        case v: Voltage => Some(v * bs.get._2)
        case e: Energy => Some(e * bs.get._2)
        case p: Power => Some(p * bs.get._2)
        case e: ElectricCharge => Some(e * bs.get._2)
        case c: Conductance => Some(c * bs.get._2)
        case c: Capacitance => Some(c * bs.get._2)
        case e: ElectricResistance => Some(e * bs.get._2)
        case _ => None
      }
    } else {
      None
    }
  }

}
