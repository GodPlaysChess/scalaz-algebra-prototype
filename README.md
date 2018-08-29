"# scalaz-algebra-prototype" 

# TODO
-

# Notes:

- Groupoid could be defined via Category ( category with inverse ) or via Group ( group with PF instead of total function)
    which one to pick in the implementation. Should not they be essentially the same in the end?

- Many typeclasses would make sense only for certain data structures. (Like Ring for Range or enum for instance).
    Where should we get those data structures from?

- Quasigroup. Define it through inverse (then what's the laws) or through right and left division?
    how do inverse relate to \ and / ?

-  How to encode Subgroup properly?