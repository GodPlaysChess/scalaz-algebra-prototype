package algebra.typeclasses

import scalaz.Equal
import scalaz.syntax.equal._

/**
  * Quasigroup with identity
  * https://en.wikipedia.org/wiki/Quasigroup
  */
trait Loop[F] extends Quasigroup[F] {
  def zero: F


  trait LoopLaws extends QuasigroupLaws {

    def leftIdentity(a: F)(implicit E: Equal[F]): Boolean = {
      combine(a, zero) === a
    }

    def rightIdentity(a: F)(implicit E: Equal[F]): Boolean = {
      combine(zero, a) === a
    }

  }

}
