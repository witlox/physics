package io.witlox.physics

import io.witlox.physics.Exponents._
import io.witlox.physics.Units._
import io.witlox.physics.units.{Angle, Chemical}
import io.witlox.physics.units.Angle._
import io.witlox.physics.units.Area._
import io.witlox.physics.units.Chemical._
import io.witlox.physics.units.Pressure._
import io.witlox.physics.units.Length._
import io.witlox.physics.units.Temperature._
import io.witlox.physics.units.Time._
import io.witlox.physics.units.Volume._
import io.witlox.physics.units.Mass._
import io.witlox.physics.units.Energy._
import org.scalatest._


class UnitsSpec extends FlatSpec with Matchers {

  "An Exponent" should "support numerals" in {
    exponentValue[P0] should be (0)
    exponentValue[P1] should be (1)
    exponentValue[P4] should be (4)
    exponentValue[N3] should be (-3)
  }

  "An Exponent" should "support next and previous" in {
    exponentValue[P0#Next] should be (1)
    exponentValue[P1#Next] should be (2)
    exponentValue[P1#Prev] should be (0)
    exponentValue[P0#Next#Prev] should be (0)
    exponentValue[P2#Next#Prev#Next#Next] should be (4)
  }

  "An Exponent" should "support negation" in {
    exponentValue[P0#Neg] should be (0)
    exponentValue[P3#Neg] should be (-3)
    exponentValue[P3#Neg#Neg] should be (3)
  }

  "An Exponent" should "support addition and subtraction" in {
    exponentValue[P4#Sub[P3]] should be (1)
    exponentValue[P5#Add[P2]] should be (7)
  }

  "A Quantity" should "have exponents that can be converted to numbers" in {
    exponentValue[Speed#M] should be (1)
    exponentValue[Speed#S] should be (-1)
    exponentValue[Speed#KG] should be (0)
  }

  "A Quantity" should "check units on compile time" in {
    val speed = 120*m / (1*min)
    speed should be (2.0*m/s)

    val pressure = 10*MN / (10*m2)
    pressure should be (10*bar)

    val power = 9000 * GW
    val tw = power / TW
    tw should be (9.0)
  }

  "Temperature Units" should "be convertable" in {
    toCelsius(K(273.15)) should be (0)
  }

  "A quantity" should "be comparable" in {
    K(0) should be < K(1)
    K(14.01) should be > K(8.01)
    K(100) should be (K(100))
  }

  "Distance" should "be computable from velocity in time" in {
    val v = new Velocity(10)
    val t = min(3)
    (v*t) should be (m(1800))
  }

  "Heat absorption in a litre of water when heating 30 degrees during 10 seconds" should "be expressible as the second law of thermodynamics" in {
    val vol = litre
    val dens = g/cm3
    val mass = vol*dens
    val hc = 4.184 * J/g
    val Q = mass * s(10) * (hc * 30)
    Q should be (kcal(300) +- kcal(1))
  }

  "Bob is stranded in a desert with one apple and" should "be able to walk distance in time over energy from mass" in {
    val appleRadius = 3*cm
    val appleEnergyDensity = 52 * cal / (100*g)
    val appleDensity =  1150 * kg/m3
    val bobWeigh = 80 * kg
    val bobWalkSpeed = 3 * km/h
    val walkEnergy = 60  * cal/km

    val appleVolume = 4.0 / 3.0 * Math.PI * appleRadius * appleRadius * appleRadius
    val appleMass = appleVolume * appleDensity
    val appleEnergy = appleMass * appleEnergyDensity
    val bobWalkDistance = appleEnergy / walkEnergy
    val bobWalkTime = bobWalkDistance / bobWalkSpeed

    bobWalkDistance should be (km(1.127) +- m(100))
    bobWalkTime should be (min(22) +- min(1))
    appleEnergy should be (280.0*J +- 5*J)
    appleMass should be (kg(0.1) +- 100*g)
  }

  "A velociraptor is chasing a human with is a starting distance" should "have the velociraptor catching the human" in {
    val velociraptor = 25 * m/s
    val human = 10 * km/h
    val distance = 30 * m
    val humanRemainingLifeTime = distance / (velociraptor - human)
    humanRemainingLifeTime should be (s(1.35) +- s(0.01))
  }

  "Inferring a unit from a string" should "be a thing we can do" in {
    Angle.spoon("1 krad").get should be (krad)
    Chemical.spoon("1000 mmol").get should be (mol)
  }

}
