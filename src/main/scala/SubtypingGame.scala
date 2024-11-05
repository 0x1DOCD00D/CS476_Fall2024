object SubtypingGame:
  def f(p:String, i:Int): String =
    if p == null then i.toString else (i+i).toString
  val s:String = f(s, 5)

//  T >: T1 >: T3
// T >: T2 >: T4
  trait T
  class T1 extends T
//    val m1 = 3
  class T3 extends T1
  class T2 extends T
  class T4 extends T2

  trait F
  class F1 extends F
  class F3 extends F1
  class F2 extends F
  class F4 extends F2

  val x:T = new T {}
  val x1:T = new T1
//  val x2:T1 = new T{}

  def m(fp: T3 => F4): Unit = println("func game")

  val f12: T1=>F2 = (i:T1)=>new F2
  val f32: T3=>F2 = (i:T3)=>new F2
  val f02: T=>F2 = (i:T)=>new F2

  def main(args: Array[String]): Unit = {
    println(s)
//    m(f02)
  }