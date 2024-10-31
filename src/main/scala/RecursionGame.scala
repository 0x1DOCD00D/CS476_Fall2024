object RecursionGame:
  val fact1:Int=>Int = (n:Int)=>if n == 0 then 1 else n*fact1(n-1)

  def hof1(g: Int=>Int, n: Int): Int = ((n:Int)=>if n == 0 then 1 else n*g(n-1))(n)
  def hack(g: Int=>Int)(n: Int): Int=>Int = ???
//  hack(Fix(f))
  
  
//  hof1((n:Int)=>if n == 0 then 1 else n*???(n-1), 5)


//  def Y(g: (Int=>Int)=>(Int=>Int)): Int =>Int = (i:Int) => g(Y(g))(i)
  def Y(g: (Int=>Int)=>(Int=>Int)): Int =>Int = (i:Int) => g(Y(g))(i)
//    (i:Int) => g(Y(g))(i) => if 3 == 0 then 1 else 3*Y(g)(3-1)(Y(g))(3) =>
//    3*Y(g)(2) =>3*g(Y(g))(2)
//    3*2*Y(g)(2-1)(Y(g))
//    3*2*1*Y(g)(1-1)(Y(g))
//    3*2*1*Y(g)(0)(Y(g))
//    if 0 == 0 then 1 else 3*2*1*Y(g)
//   3*2*1*1

  def YY[A, B](f: (A => B) => (A => B)): A => B = {
    case class Wrapper(wrapped: Wrapper => (A => B))

    val g: Wrapper => (A => B) = {
      w => f(a => w.wrapped(w)(a))
    }

    g(Wrapper(g))
  }


  def main(args: Array[String]): Unit = {
//    println(identity)
//    println(fact1(6))
//    println(hof1(fact1, 6))
//    Y(g)(3) =>
    println {
      YY(
        (h: Int => Int) => (n: Int) => if n == 0 then 1 else n * h(n - 1)
      )(5)
    }
    println{
      hof1(
        Y(
          (h: Int => Int) => (n: Int) => if n == 0 then 1 else n * h(n - 1)
        )
        , 6
      )
    }
  }