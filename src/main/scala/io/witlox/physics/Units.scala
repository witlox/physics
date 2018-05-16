package io.witlox.physics

import Exponents._

object Units {

  trait Unit {
    type M   <: Exp
    type KG  <: Exp
    type S   <: Exp
    type A   <: Exp
    type K   <: Exp
    type Mol <: Exp
    type CD  <: Exp

    type Div[O <: Unit] = Quantity[M-O#M, KG-O#KG, S-O#S, A-O#A, K-O#K, Mol-O#Mol, CD-O#CD]
    type Mul[O <: Unit] = Quantity[M+O#M, KG+O#KG, S+O#S, A+O#A, K+O#K, Mol+O#Mol, CD+O#CD]
  }

  type / [A <: Unit, B <: Unit] = A#Div[B]
  type ~ [A <: Unit, B <: Unit] = A#Mul[B] // We can not use * as a type, because it is reserved for variable length argument lists

  case class Quantity[M_ <: Exp, KG_ <: Exp, S_ <: Exp, A_ <: Exp, K_ <: Exp, Mol_ <: Exp, CD_ <: Exp](value: Double = 1.0) extends Unit with Ordered[Quantity[M_, KG_, S_, A_, K_, Mol_, CD_]] {
    type M   = M_
    type KG  = KG_
    type S   = S_
    type A   = A_
    type K   = K_
    type Mol = Mol_
    type CD  = CD_

    type This = Quantity[M, KG, S, A, K, Mol, CD]
    def +(m : This) = Quantity[M, KG, S, A, K, Mol, CD](value + m.value)
    def -(m : This) = Quantity[M, KG, S, A, K, Mol, CD](value - m.value)
    def *[M2 <: Exp, KG2 <: Exp, S2 <: Exp, A2 <: Exp, K2 <: Exp, Mol2 <: Exp, CD2 <: Exp](m : Quantity[M2, KG2, S2, A2, K2, Mol2, CD2]) = Quantity[M + M2, KG + KG2, S + S2, A + A2, K + K2, Mol + Mol2, CD + CD2](value * m.value)
    def /[M2 <: Exp, KG2 <: Exp, S2 <: Exp, A2 <: Exp, K2 <: Exp, Mol2 <: Exp, CD2 <: Exp](m : Quantity[M2, KG2, S2, A2, K2, Mol2, CD2]) = Quantity[M - M2, KG - KG2, S - S2, A - A2, K - K2, Mol - Mol2, CD - CD2](value / m.value)

    def apply(v : Double) = Quantity[M, KG, S, A, K, Mol, CD](v * value)

    override def toString: String = "" + value

    override def equals(that: Any): Boolean =  that match {
      case spread: QuantitySpread => spread.isWithin(this)
      case t: This => this.value == t.value
      case _ => this.value == that
    }

    def +-(tolerance: Quantity[M_, KG_, S_, A_, K_, Mol_, CD_]): QuantitySpread = {
      if (tolerance.value <= 0.0)
        throw new IllegalArgumentException(tolerance.toString + " passed to +- was zero or negative. Must be a positive non-zero number.")
      QuantitySpread(this, tolerance)
    }

    final case class QuantitySpread(pivot: Quantity[M_, KG_, S_, A_, K_, Mol_, CD_], tolerance: Quantity[M_, KG_, S_, A_, K_, Mol_, CD_]) {
      def isWithin(n: This): Boolean = {
        n.value > (pivot - tolerance).value && n.value <= (pivot + tolerance).value
      }
      override def toString: String = pivot + " +- " + tolerance
    }

    override def compare(that: Quantity[M_, KG_, S_, A_, K_, Mol_, CD_]): Int = this.value compare that.value
  }


  implicit def measure(v : Double) = Quantity[__, __, __, __, __, __, __](v)

  implicit def unitlessQuantityToDouble(q: Quantity[__, __, __, __, __, __, __]): Double = q.value

  // Unitless quantity
  type One                  = Quantity[__, __, __, __, __, __, __]

  // Base units
  type Length               = Quantity[P1, __, __, __, __, __, __]; type m   = Length;                   type metre         = m;      type meter = metre
  type Mass                 = Quantity[__, P1, __, __, __, __, __]; type kg  = Mass;                     type kilogram      = kg
  type Time                 = Quantity[__, __, P1, __, __, __, __]; type s   = Time;                     type second        = s
  type Current              = Quantity[__, __, __, P1, __, __, __]; type A   = Current;                  type Ampere        = A
  type Temperature          = Quantity[__, __, __, __, P1, __, __]; type K   = Temperature;              type Kelvin        = K
  type AmountOfSubstance    = Quantity[__, __, __, __, __, P1, __]; type mol = AmountOfSubstance;        type mole          = mol
  type LuminousIntensity    = Quantity[__, __, __, __, __, __, P1]; type cd  = LuminousIntensity;        type candela       = cd

  // Supplementary units
  type RelativeAtomicMass        = One;        type Ar  = RelativeAtomicMass;          type AtomicMass = Ar
  type PlaneAngle                = One;        type rad = PlaneAngle;                  type radian = rad
  type SolidAngle                = One;        type sr  = SolidAngle;                  type steradian = sr

  // Some derived units with symbols and alternative names
  type Frequency                 = One/s;      type Hz  = Frequency;                   type Hertz    = Hz

  type Force                     = m~kg/s2;    type N   = Force;                       type Newton   = N
  type Energy                    = N~m;        type J   = Energy;                      type Joule    = J
  type Power                     = J/s;        type W   = Power;                       type Watt     = W
  type Pressure                  = N/m2;       type Pa  = Pressure;                    type Pascal   = Pa

  type Voltage                   = W/A;        type V   = Voltage;                     type Volt     = V
  type ElectricCharge            = s~A;        type C   = ElectricCharge;              type Coulomb  = C
  type Capacitance               = C/V;        type F   = Capacitance;                 type Farad    = F
  type ElectricResistance        = V/A;        type Ohm = ElectricResistance
  type Conductance               = A/V;        type S   = Conductance;                 type Siemens  = S

  type MagneticFlux              = V~s;        type Wb  = MagneticFlux;                type Weber    = Wb
  type MagneticFluxDensity       = Wb/m2;      type T   = MagneticFluxDensity;         type Tesla    = T
  type Inductance                = Wb/A;       type H   = Inductance;                  type Henry    = H

  type LuminousFlux              = cd~sr;      type lm  = LuminousFlux;                type lumen    = lm
  type Illuminance               = lm/m2;      type lx  = Illuminance;                 type lux      = lx

  type CatalyticActivity         = mol/s;      type kat = CatalyticActivity;           type katal    = kat

  type AbsorbedDose              = J/kg;       type Gy  = AbsorbedDose;                type Gray     = Gy
  type DoseEquivalent            = J/kg;       type Sv  = DoseEquivalent;              type Sievert  = Sv
  type IonizingRadiationActivity = One/s;      type Bq  = IonizingRadiationActivity;   type Becquerel = Bq

  // Shorthands for some exponents
  type s2                             = s~s
  type s3                             = s~s~s

  // Derived units without own symbol
  type Area                           = m~m;        type m2  = Area
  type Volume                         = m~m~m;      type m3  = Volume
  type Velocity                       = m/s;        type Speed = Velocity
  type Acceleration                   = m/s2
  type AngularVelocity                = rad/s
  type AngularAcceleration            = rad/s2

  type Density                        = kg/m3
  type SurfaceDensity                 = kg/m2
  type Concentration                  = mol/m3
  type CatalyticActivityConcentration = kat/m3

  type DynamicViscosity               = Pa~s
  type SurfaceTension                 = N/m
  type MomentOfForce                  = N~m

  type HeatFluxDensity                = W/m2
  type Irradiance                     = W/m2
  type HeatCapacity                   = J/K
  type SpecificHeatCapacity           = J/kg~K
  type ThermalConductivity            = W/m~K
  type MolarHeatCapacity              = J/mol~K
  type ThermalExpansion               = m/m~K

  type SpecificEnergy                 = J/kg
  type EnergyDensity                  = J/m3
  type MolarEnergy                    = J/mol

  type ElectricResistivity            = Ohm~m
  type ElectricFieldStrength          = V/m
  type ElectricChargeDensity          = C/m3
  type ElectricFluxDensity            = C/m2
  type Permittivity                   = F/m
  type Permeability                   = H/m

  type IonizingRadiationExposure      = C/kg
  type AbsorbedDoseRate               = Gy/s
  type RadiantIntensity               = W/sr
  type Radiance                       = W/m2~sr
}
