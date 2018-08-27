package algebra.typeclasses

import scalaz.Equal
import scalaz.std.option._
import scalaz.syntax.equal._
import scalaz.std.anyVal.booleanInstance

trait Groupoid[F] {
  def inverse(f: F): F

  def partialCombine(f1: F, f2: F): Option[F]

  trait GroupoidLaws {
    def associativityLaw(a: F, b: F, c: F)(implicit eq: Equal[F]): Boolean = {
      partialCombine(a, b).flatMap(x ⇒partialCombine(x, c)) === partialCombine(b, c).flatMap(x ⇒ partialCombine(a, x))
    }

    def inverseLaw(a: F)(implicit eq: Equal[F]): Boolean = {
      partialCombine(a, inverse(a)).isDefined
      partialCombine(inverse(a), a).isDefined
    }

    def identityLaw(a: F, b: F, c: F)(implicit eq: Equal[F]): Boolean = {
      partialCombine(a, b) map { x ⇒
        partialCombine(x, inverse(b)).contains(a)
        partialCombine(inverse(a), x).contains(b)
      } forall (_ === true)
    }
  }
}
