# io.witlox.physics

Scala implementation for doing basic physics equations. Implicit and explicit conversion.

## Example

"Heat absorption in a litre of water when heating 30 degrees during 10 seconds" can be expressed as follows:


    val vol = litre
    val dens = g/cm3
    val mass = vol*dens
    val hc = 4.184 * J/g
    val Q = mass * s(10) * (hc * 30)
    Q should be (kcal(300) +- kcal(1))
