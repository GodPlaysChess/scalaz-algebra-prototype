package algebra.typeclasses

import algebra.data.Subset

/**
  * Group which is defined for a subset of F ( H <: F ), such as H is a group itself.
  * Subgroup[H, F] have 3 conditions here
  * - it is a Group[H]
  * - it is a Group[F] (under same binary op as H)
  * - H is a subset of F.
  * https://en.wikipedia.org/wiki/Subgroup
  */
trait Subgroup[H, F] extends Group[F] with Subset[H, F] { self ⇒

  // how to ensure that result is belonging to a subset. Shall I put it in laws instead?
  def G: Group[H] = new Group[H] {
    override def inverse(a: H): H = ??? //from(self.inverse(to(a)))

    override def zero: H = ??? //from(self.zero)

    override def append(f1: H, f2: ⇒ H): H = ??? //self.append(to(f1), to(f2))
  }

  trait SubgroupLaws extends MonoidLaw {
    //
  }
}

/*
  * trait Subgroup[H, F] extends Group[H] with Subset[H, F] { self ⇒
  *
  * // do not know any other way to ensure that F is also a group.
  * // it's possible also to extends group
  * def G: Group[F] = new Group[F] {
  * override def inverse(a: F): F = from(a).map(to)
  *
  * override def zero: F = to(self.zero)
  *
  * override def append(f1: F, f2: ⇒ F): F = to(self.append(from(f1), from(f2)))
  * }
  *
  * trait SubgroupLaws extends MonoidLaw {
  * //
  * }
  *
  *
  */
