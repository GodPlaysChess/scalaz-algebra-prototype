package algebra.data

final case class Rational(nom: BigInt, denom: BigInt) {

  def +(other: Rational): Rational = {
    ???
  }

  def *(other: Rational): Rational = ???

  def -(other: Rational): Rational = ???

  def /(other: Rational): Rational = ???

  private def simplify: Rational = ???
}

object Rational {
  def apply(nom: BigInt, denom: BigInt): Rational = new Rational(nom, denom).simplify
}
