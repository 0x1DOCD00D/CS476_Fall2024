object PeanoArith:

  trait Nat:
    def add(p1: Nat):Nat
    def subtract(p1:Nat): Nat
    def div(p1:Nat): Nat
    def mult(p1:Nat): Nat
    def toArabic():Int

  private case object Zero extends Nat:
    override def add(p1: Nat): Nat = p1
    override def subtract(p1: Nat): Nat = Zero
    override def div(p1: Nat): Nat =
      p1 match
        case Zero => throw new Exception("Division by zero")
        case _ => Zero
    override def mult(p1: Nat): Nat = Zero
    override def toArabic(): Int = 0


  private case class Succ(p: Nat) extends Nat:
    override def add(p1: Nat): Nat =
      if this == Zero then p1 else p.add(Succ(p1))
    override def subtract(p1: Nat): Nat =
      p1 match
        case Zero => this
        case Succ(x) => p.subtract(x)
    override def div(p1: Nat): Nat =
      p1 match
        case Zero => throw new Exception("Division by zero")
        case this => Succ(Zero)
        case Succ(Zero) => this
        case _ => if (this == Zero) Zero else if (this.subtract(p1)!=Zero) this.subtract(p1).div(p1).add(Succ(Zero)) else this.subtract(p1).div(p1)

    override def mult(p1: Nat): Nat =
      p1 match
        case Zero => Zero
        case Succ(x) => this.mult(x).add(this)

    override def toArabic(): Int = 1 + p.toArabic()


  def main(args: Array[String]): Unit = {
    println(Succ(Zero))//one
    println(Succ(Succ(Zero)))//two
    val three = Succ(Succ(Succ(Zero)))
    val four = Succ(Succ(Succ(Succ(Zero))))
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

    println(
      three.subtract(two)
    )

    println(
      three.subtract(Zero)
    )

    println(
      two.subtract(three)
    )

    println(
      two.toArabic()
    )


    println(three.mult(two).toArabic())

    println(three.div(two).toArabic())

    println(four.div(two).toArabic())

    println(four.mult(two).div(three).toArabic())

  }