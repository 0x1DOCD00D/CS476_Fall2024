object PeanoArith:

  trait Nat:
    def add(p1: Nat):Nat

  case object Zero extends Nat:
    override def add(p1: Nat): Nat = p1

  case class Succ(p: Nat) extends Nat:
    override def add(p1: Nat): Nat =
      p match
        case Succ(x) => x.add(Succ(p1))
        case Zero => p1
        case _ => throw new RuntimeException("ouch!!!")


  def main(args: Array[String]): Unit = {
    println(Succ(Zero))//one
    println(Succ(Succ(Zero)))//two
    val three = Succ(Succ(Succ(Zero)))
    val two = Succ(Succ(Zero))
    println(
      three.add(two)
    )
  }