package algebra.typeclasses

import scalaz.{Category, Equal}
import scalaz.std.option._
import scalaz.syntax.equal._
import scalaz.std.anyVal.booleanInstance

trait Groupoid[F] {
  def zero: F // shall we keep it? What's the connection with inverse? Does it go straight to laws?

  def inverse(f: F): F

  def partialCombine(f1: F, f2: F): Option[F]

  trait GroupoidLaws {
    def associativityLaw(a: F, b: F, c: F)(implicit E: Equal[F]): Boolean = {
      partialCombine(a, b).flatMap(x ⇒ partialCombine(x, c)) === partialCombine(b, c).flatMap(x ⇒ partialCombine(a, x))
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


// this is Groupoid defined through category, and actually should be the same as above
trait GroupoidC[=>:[_, _]] extends Category[=>:] {
  def inverse[A, B](to: A =>: B): B =>: A

  trait GroupoidLaws extends CategoryLaw {
    def inverseLaw1[A, B](f: A =>: B)(implicit E: Equal[A =>: A]): Boolean = {
      compose(inverse(f), f) === id[A]
    }

    def inverseLaw2[A, B](f: A =>: B)(implicit E: Equal[B =>: B]): Boolean = {
      compose(f, inverse(f)) === id[B]
    }
  }
}

