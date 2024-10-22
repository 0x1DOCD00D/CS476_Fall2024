object PartialFunks:
  val pf2: PartialFunction[Int, Unit] = {
    case 2 => println("two")
  }

  val pf3: PartialFunction[Int, Unit] = {
    case 3 => println("three")
  }

  val pf4: PartialFunction[Int, Unit] = {
    case 4 => println("four")
  }

  val pf5: PartialFunction[Int, Unit] = {
    case 4 => println("four")
  }

  def accumulate[A, B](pfs: PartialFunction[A, B]*): PartialFunction[A, B] = {
    pfs.reduce(_ orElse _)
  }

  def filter[A](list: List[A])(pf: PartialFunction[A, _]): List[A] = {
    list.collect {
      case x if pf.isDefinedAt(x) => x
    }
  }
//    val result = filter(list)(accumulatedPF)


  def main(args: Array[String]): Unit = {
    new ExceptionMaker().f(-1)//.lift
    pf2(2)
    if pf2.isDefinedAt(3) then pf2(3) else println("ouch!!!")
    println(pf2.lift(3))

    (pf2 orElse pf3 orElse pf4 orElse pf5)(4)
    val pf = accumulate(pf2, pf3, pf5, pf4)
    pf(4)

  }