object SumProductTypes:
//  S1={a,b,c}
// S2 = {1,2}
//S1xS2 = {(a,1),(a,2),...}
  val productValue: (String, Int) = ("Yogesh", 1)//|String| x |Int|
  val zeroProduct: Unit = ()
//  val zeroSum = void
  val sumTypeValue: "Dhruv" | "Fatima" = "Fatima"
  val sumTypeValue1: Either[String, Float] = Left("Dhruv")//Right(3.2f)

  def f(p: String): Either[String, Int] =
    if p.length == 0 then Left("Cannot pass an empty string")
    else Right(p.length)

//  trait SumType[T,S]
//  case class Left[T](v: T) extends SumType[T,_]
//  case class Right[S](v: S) extends SumType[_,S]

  trait Maybe[+T]
  case class Some1[+T](v:T) extends Maybe[T]
  case object None1 extends Maybe[Nothing]

  case class X(p: Maybe[Y])
  case class Y(p: Maybe[X])
  case class Recursive(p: Recursive)

  def main(args: Array[String]): Unit = {
    val xAndy = X(Some1(Y(Some1(X(None1)))))
    println(sumTypeValue)
    println {
      f("Fatima") match
        case Left(value) => value
        case Right(value) => value +10
    }
    println {
      f("") match
        case Left(value) => value
        case Right(value) => value + 10
    }
    var x = 0
//    val d = if (x = 3) == 3 then "a" else "b"
//    println(productValue(1))
  }