package algebra.typeclasses

import scalaz.Equal

/**
  *
  * https://en.wikipedia.org/wiki/Semilattice
  *
  */
trait BoundedJoinSemilattice[F] extends JoinSemilattice[F] {

  def id: F

  trait BoundedJoinSemilatticeLaws extends JoinSemilatticeLaws {
    def joinId(f: F)(implicit F: Equal[F]): Boolean =
      F.equal(join(id, f), f)
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
  