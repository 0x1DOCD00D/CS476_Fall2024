object ApplyFunction2Itself:
  def f[T](p: T => T): T => T =
    (i: T) => p(i)

  val computation = f(f(f(_: Int => Int)))
  
  def main(args: Array[String]): Unit = {
    println {
      computation((i: Int) => i + 1)(5)
    }
  }
 