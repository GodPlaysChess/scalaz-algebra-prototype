package algebra.typeclasses

import scalaz.{@@, Equal, Monoid, Tag}
import scalaz.syntax.equal._


/**
  * Abelian group F
  * Monoid under F @@ T
  * Multiplication is distributive
  */
trait Ring[F, T] extends AbelianGroup[F] {
  
  def multMonoid: Monoid[F @@ T]

  def multiply(a: F, b: F): F = from(multMonoid.append(to(a), to(b)))

  private def from(a: F @@ T): F = Tag.unwrap(a)
  private def to(a: F): F @@ T = Tag.apply(a)

  trait RingLaws extends AbelianGroupLaws {
    def multiplicationIsNotAddition(els: List[F])(implicit eq: Equal[F]): Boolean = {
      (for (a ← els; b ← els) yield append(a, b) === multiply(a, b))
        .contains(false)
    }

    def distributiveRight(a: F, b: F, c: F)(implicit eq: Equal[F]): Boolean = {
      multiply(a, append(b, c)) === append(multiply(a, b), multiply(a, c))
    }

    def distributiveLeft(a: F, b: F, c: F)(implicit eq: Equal[F]): Boolean = {
      multiply(append(b, c), a) === append(multiply(b, a), multiply(c, a))
    }
  }

}

object Ring {
  // the most often used ring, is the one which is created from NatRange
  // may be here scalaz-refined would fit
  //  type Range1[N, M]
  //  type Range = Range1[N, M]
  //  def fromRange(from: Int, to: Int): Ring[Range]
}
