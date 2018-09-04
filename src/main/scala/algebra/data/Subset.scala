package algebra.data

import scalaz.Equal
import scalaz.syntax.equal._
import scalaz.std.option._

/**
  * H is a subset of F, is there exists morphism from H to F.
  * I think we could define it this way, skipping  H <: F subtyping part, since
  * one can always argue that H is isomorphic to H1 <: F, where H1 is a subset of F.
  *
  * for example F is a set with a values A, B
  * H is a subset, with only value A.
  */
trait Subset[H, F] {
  // this is not strictly necessary but we somehow should signal that H is isomorphic to subset of F, in other words that F is a subtype of H
  implicit def E: F <:< H

  def widen(h: H): F // = E(h)
  def narrow(f: F): H = E.apply(f)

  trait SubsetLaws {

    // there have to be isomorhism from H to subset of F
    def isomorphism(h: H, f: F)(implicit E: Equal[H]): Boolean = {
      narrow(widen(h)) === h
    }
  }
}

object Subset {
  // short example to be able to understand what's going on
  // here , Htype is a Subset of Ftype.
  sealed abstract class FType
  final case object A extends FType
  final case object B extends FType

  sealed abstract class HType
  final case object Aiso extends HType

//  val trivialSubset: Subset[HType, FType] = new Subset[HType, FType] {
//    override def widen(h: HType): FType = h match {
//      case Aiso ⇒ A
//    }
//
//    override def narrow(f: FType): Option[HType] = f match {
//      case A ⇒ Some(Aiso)
//      case B ⇒ None
//    }
//  }

  // here F can be easily expressed as a Subtype of H how does this work then
  // that would mean, that whenever we need to use a subset, we can always widen it's type to a superset F
  val E: FType <:< HType = ???

}

/**
  * We can define subset also on the value level. Then actually any finite set of [F] is a subset of all values of F.
  * @tparam F
  */
trait Subset1[F] {
  def values: Set[F]
}


