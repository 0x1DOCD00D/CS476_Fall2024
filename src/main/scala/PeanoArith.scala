object PeanoArith:

  trait Nat:
    def add(p1: Nat):Nat

  private case object Zero extends Nat:
    override def add(p1: Nat): Nat = p1

  private case class Succ(p: Nat) extends Nat:
    override def add(p1: Nat): Nat =
      if this == Zero then p1 else p.add(Succ(p1))


  def main(args: Array[String]): Unit = {
    println(Succ(Zero))//one
    println(Succ(Succ(Zero)))//two
    val three = Succ(Succ(Succ(Zero)))
    val two = Succ(Succ(Zero))
    println(
      three.add(two)
    )
    println(
      Zero.add(two)
    )

    println(
      three.add(Zero)
    )

  }