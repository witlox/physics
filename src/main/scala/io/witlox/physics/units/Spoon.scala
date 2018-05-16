package io.witlox.physics.units

import scala.reflect.runtime.universe

trait Spoon {

  val regexN = """(-?[0-9]+(?:[,.][0-9]+)?)"""

  /** *
    * Extract the numeric value and the type from a text
    * "1000 kN" should be ("kN", 1000)
    * @param text to extract value and type
    * @return tuple of type (string) and value (double)
    */
  def getValue(text: String): (String, Double) = {
    val n = regexN.r findFirstIn text
    val t = text.replaceAll(regexN, "").trim
    if (n.isDefined) {
      (t, n.get.toDouble)
    } else {
      (t, 1.0)
    }
  }

  /**
    * Execute string to val conversion
    * @param a reference to the object
    * @param f quantity as string
    * @tparam A implicit object converter
    * @return Some(quantity value) or None if the quantity does not exist
    */
  private def convert[A: universe.TypeTag](a: A, f: String): Option[Any] = {
    val runtimeMirror = universe.runtimeMirror(getClass.getClassLoader)
    val moduleSymbol = runtimeMirror.moduleSymbol(a.getClass)
    val targetMethods = moduleSymbol.typeSignature.members.filter(x => x.isMethod && x.name.toString == f)
    if (targetMethods.nonEmpty) {
      val targetMethod = targetMethods.head.asMethod
      val rtval = runtimeMirror
        .reflect(runtimeMirror.reflectModule(moduleSymbol).instance)
        .reflectMethod(targetMethod)
        .apply()
      Some(rtval)
    } else {
      None
    }
  }

  /**
    * try to extract the value of the explicit type from a text
    * "1000 kN" should end up becoming Quantity of kN(1000)
    * @param text text to analyse, should only have a single value quantity pair
    * @return Some(Tuple(quantity, value)) or None if the quantity does not exist
    */
  protected def extract(text: String): Option[(Any, Double)] = {
    val (t,v) = getValue(text)
    val av = convert(this, t)
    if (av.isDefined) {
      Some(av.get, v)
    } else {
      None
    }
  }

  /**
    * try to extract the value of the explicit type from a text
    * "1000 kN" should end up becoming Quantity of kN(1000)
    * @param text text to analyse, should only have a single value quantity pair
    * @return Some(Quantity) or None if the quantity does not exist
    */
  def spoon(text: String): Option[Any]

}