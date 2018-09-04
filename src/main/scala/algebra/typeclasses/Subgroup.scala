package algebra.typeclasses

import algebra.data.{Subset, Subset1}

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
    override def inverse(a: H): H = narrow(self.inverse(widen(a)))

    override def zero: H = narrow(self.zero)

    override def append(f1: H, f2: ⇒ H): H = self.append(widen(f1), widen(f2))
  }

  trait SubgroupLaws extends MonoidLaw {
    //
  }
}

/**
  * Just exploring how to define a subgroup through a SubSet1
  */
trait Subgroup1[F] extends Subset1[F] with Group[F] {
  // this group should be defined on a subset
  def subGroup: Group[F] = new Group[F] {
    override def inverse(a: F): F = ???

    override def zero: F = ???

    override def append(f1: F, f2: ⇒ F): F = ???
  }
}