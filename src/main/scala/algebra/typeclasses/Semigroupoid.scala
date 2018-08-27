package algebra.typeclasses

import scalaz.Compose


/**
  * Groupoid without identity and inverse
  * in scalaz it is called Compose
  * */
trait Semigroupoid[=>:[_, _]] extends Compose[=>:]