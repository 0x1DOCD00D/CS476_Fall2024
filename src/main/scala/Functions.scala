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
    if cond then
      println(s"Within Mark's if $z")
      code // Executes the block which increments 'z'
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