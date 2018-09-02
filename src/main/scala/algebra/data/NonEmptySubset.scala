package algebra.data

import scalaz.OneAnd
// extends OneAnd[Subset[H, ?], H]
final case class NonEmptySubset[H, F](oneAnd: OneAnd[Subset[?, F], H])

