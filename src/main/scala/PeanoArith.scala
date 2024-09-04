object PeanoArith:

  trait Nat:
    def add(p1: Nat):Nat

  case object Zero extends Nat:
    override def add(p1: Nat): Nat = p1

  case class Succ(p: Nat) extends Nat:
    override def add(p1: Nat): Nat =
//    matching with p removes the outermost Succ so we
//    match with this (the object itself instead of the one it wraps)
//    which is 'succ(p)' or 'this' instead of p
      this match
        case Succ(x) => x.add(Succ(p1))
//        not needed since Zero already has add method
//        case Zero => p1
//        Sbt warning: Unreachable case except for null (if this is intentional, consider writing case null => instead).
        case null => throw new RuntimeException("ouch!!!")


  def main(args: Array[String]): Unit = {
    println(Succ(Zero))//one
    println(Succ(Succ(Zero)))//two
    val three = Succ(Succ(Succ(Zero)))
    val two = Succ(Succ(Zero))
    println(
      three.add(two)
    )
  }