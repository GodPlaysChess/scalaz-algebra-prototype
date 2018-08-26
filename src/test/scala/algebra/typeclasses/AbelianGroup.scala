package algebra.typeclasses

import scalaz.Equal
import scalaz.syntax.equal._


/**
  * Commutative group
  */
trait AbelianGroup[F] extends Group[F] {

  def addition(a: F, b: F) = append(a, b)
  trait AbelianGroupLaws extends GroupLaw {
    def commutativity(a: F, b: F)(implicit eq: Equal[F]): Boolean = append(a, b) === append(b, a)
  }
}
