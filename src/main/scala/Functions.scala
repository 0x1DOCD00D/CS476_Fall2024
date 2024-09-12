object Functions:
  val f: String => Int => Int = (s: String) => (i: Int) => s.length+i

  val x = {
    val y = 2
    y
  }
  def g(p:Int) = println(p)

  def main(args: Array[String]): Unit = {
//    println(x)
    g(x)
//    println(f("mark"))
  }