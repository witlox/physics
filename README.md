# io.witlox.physics [![Build Status](https://travis-ci.org/witlox/physics.svg?branch=master)](https://travis-ci.org/witlox/physics) [![Coverage Status](https://coveralls.io/repos/github/witlox/physics/badge.svg?branch=master)](https://coveralls.io/github/witlox/physics?branch=master) [ ![Download](https://api.bintray.com/packages/witlox/sbt-libraries/io.witlox.physics/images/download.svg?version=0.1.0) ](https://bintray.com/witlox/sbt-libraries/io.witlox.physics/0.1.0/link)

Scala implementation for doing basic physics equations. Implicit and explicit conversion.

## Example

"Heat absorption in a litre of water when heating 30 degrees during 10 seconds" can be expressed as follows:

```scala
val vol = litre
val dens = g/cm3
val mass = vol*dens
val hc = 4.184 * J/g
val Q = mass * s(10) * (hc * 30)
Q should be (kcal(300) +- kcal(1))
```