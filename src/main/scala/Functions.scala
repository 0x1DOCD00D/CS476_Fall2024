object Functions:
  val f: String => Int => Int = (s: String) => (i: Int) => s.length+i

  val x = {
    val y = 2
    y
  }
  var z = 3
  def g(p: => Int) =
    if z >= 3 then println(p)
    else println("nothing")

  def MarkWhile(cond: => Boolean)(code: => Int): Int = {

    // cond is evaluated, but code is not evaluated in the recursive call
    // Infinite recursion occurs because code is never evaluated
    // if cond then MarkWhile(cond)(code)

    if cond then
      println(s"Within Mark's if $z")
      code  // evaluate code to increment z
      MarkWhile(cond)(code)
    else 0
  }
  def main(args: Array[String]): Unit = {
    //    println(x)
    g {
      println("The block is evaluated")
      val y = 2
      y
    }

    MarkWhile(z == 3) {
      println("The block is evaluated")
      val y = 2
      z = z+1
      y
    }
//    println(f("mark"))
  }