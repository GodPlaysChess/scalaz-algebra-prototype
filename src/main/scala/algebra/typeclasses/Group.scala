package algebra.typeclasses

import algebra.data.Rational
import scalaz.Tags.Multiplication
import scalaz.{@@, Monoid, Tag}
import scalaz.Equal
import scalaz.syntax.equal._

/**
  * Monoid with inverse
* */
trait Group[F] extends Monoid[F] {
  def inverse(a: F): F

  def divide(a: F, b: F): F = append(a, inverse(b))

  trait GroupLaw {

    def inverseCommutativity(a: F)(implicit E: Equal[F]): Boolean = append(a, inverse(a)) === append(inverse(a), a)
    
    def inverseIdentity(a: F)(implicit E: Equal[F]): Boolean = append(a, inverse(a)) === zero
  }
}

object Group {
  @inline def apply[F](implicit F: Group[F]): Group[F] = F
}

object GroupInstances {
  implicit val intGroup: Group[Int] = new Group[Int] {
    override def inverse(a: Int): Int = -a

    override def zero: Int = scalaz.std.anyVal.intInstance.zero

    override def append(f1: Int, f2: ⇒ Int): Int = scalaz.std.anyVal.intInstance.append(f1, f2)
  }

  implicit val rationalGroup: Group[Rational @@ Multiplication] = new Group[Rational @@ Multiplication] {
    override def inverse(a: Rational @@ Multiplication): Rational @@ Multiplication = Multiplication(Rational(Tag.unwrap(a).nom, Tag.unwrap(a).denom))

    override def zero: Rational @@ Multiplication = Multiplication(Rational(0, 0))

    override def append(f1: Rational @@ Multiplication, f2: ⇒ Rational @@ Multiplication): Rational @@ Multiplication =
      Multiplication(Tag.unwrap(f1) * Tag.unwrap(f2))
  }

}

