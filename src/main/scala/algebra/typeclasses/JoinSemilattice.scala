package algebra.typeclasses

import algebra.data.{NonEmptySubset, Subset}
import scalaz.Equal

/**
  *
  * https://en.wikipedia.org/wiki/Semilattice
  *
  */
trait JoinSemilattice[F] {

  def join(a: F, b: F): F

  trait JoinSemilatticeLaws {
    def associative(f1: F, f2: F, f3: F)(implicit F: Equal[F]): Boolean =
      F.equal(join(f1, join(f2, f3)), join(join(f1, f2), f3))

    def commutativity(f1: F, f2: F)(implicit F: Equal[F]): Boolean =
      F.equal(join(f1, f2), join(f2, f1))

    def idempotency(f: F)(implicit F: Equal[F]): Boolean = {
      F.equal(join(f, f), f)
    }
  }

}

// could those be better described as Semilattice with tag @@ Upper, or @@ Lower ?
trait MeetSemilattice[F] {
  // same as join
  def meet(a: F, b: F): F

}

/**
  * Just a bad idea of is it possible to define it this way:  
  * implicit val PO: PartialOrdering[F]
  * def join[H](subset: NonEmptySubset[H, F]): H
  *
  */
  