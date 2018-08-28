package algebra.typeclasses

/**
  *
  * https://en.wikipedia.org/wiki/Magma_(algebra)
  * set with binary operation
  * Semigroup is actually a Magma with associativity
  */
trait Magma[F] {
  def combine(a: F, b: F): F
}
