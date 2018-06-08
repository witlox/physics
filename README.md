# io.witlox.physics [![Build Status](https://travis-ci.org/witlox/physics.svg?branch=master)](https://travis-ci.org/witlox/physics) [![Coverage Status](https://coveralls.io/repos/github/witlox/physics/badge.svg?branch=master)](https://coveralls.io/github/witlox/physics?branch=master) [![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fwitlox%2Fphysics.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2Fwitlox%2Fphysics?ref=badge_shield)


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

"Inferring a unit from a string" can be expressed as follows:
 
```scala
Chemical.spoon("1000 mmol").get should be (mol)
```


## License
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fwitlox%2Fphysics.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2Fwitlox%2Fphysics?ref=badge_large)