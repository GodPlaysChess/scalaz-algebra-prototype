package algebra.typeclasses

import scalaz.Equal
import scalaz.syntax.equal._

/**
  *  Magma with Invertibility
  *  https://en.wikipedia.org/wiki/Quasigroup
  */
trait Quasigroup[F] extends Magma[F] {
  // how do inverse relate to \ and / ?
  def inverse(a: F): F

  def /(a: F, b: F): F

  def \(a: F, b: F): F

  trait QuasigroupLaws {

    def rightDivisiond(x: F, y: F)(implicit E: Equal[F]): Boolean = {
      y === combine(x, \(x, y))
      y === \(x, combine(x, y))
      y === combine(/(y, x), x)
      y === /(combine(y, x), x)
    }
  }
}
