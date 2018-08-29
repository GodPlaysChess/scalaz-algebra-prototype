package algebra.data

import scalaz.Equal
import scalaz.syntax.equal._
import scalaz.std.option._

/**
  * H is a subset of F, is there exists morphism from H to F.
  * I think we could define it this way, skipping  H <: F subtyping part, since
  * one can always argue that H is isomorphic to H1 <: F, where H1 is a subset of F.
  */
trait Subset[H, F] {
  def to(h: H): F
  def from(f: F): Option[H]

  trait SubsetLaws {

    // there have to be isomorhism from H to subset of F
    def isomorphism(h: H, f: F)(implicit E: Equal[H]): Boolean = {
      from(to(h)) === Some(h)
    }
  }
}
