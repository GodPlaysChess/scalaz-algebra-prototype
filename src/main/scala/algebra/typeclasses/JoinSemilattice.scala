package algebra.typeclasses

import algebra.data.{NonEmptySubset, Subset}
import scalaz.Equal

/**
  *
  * https://en.wikipedia.org/wiki/Semilattice
  *
  */
trait JoinSemilattice[F] {
  implicit val PO: PartialOrdering[F] // TODO find scalaz equivalence
  /**
    * Returns upper bound for any provided subset
    */
  def join[H](subset: NonEmptySubset[H, F]): H


  // TODO could we actually define it slightly smarter, including PO in the definition:
  def join2(a: F, b: F): F

  trait JoinSemilatticeLaws {
    def associativity(implicit E: Equal[F]) = {

    }
  }



}
// could those be better described as Semilattice with tag @@ Upper, or @@ Lower ?
trait MeetSemilattice[F] {
  implicit val PO: PartialOrdering[F]

  /**
    * Returns lower bound for any provided subset
    */
  def meet[H](subset: Subset[H, F]): H



}

