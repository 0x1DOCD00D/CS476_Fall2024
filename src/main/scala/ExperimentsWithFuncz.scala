object ExperimentsWithFuncz:
  val f: String =>Int = (s:String)=> s.length
//  val g : String =>Int =>Int = (x:String =>Int) =>

  def mf(someFunc: String=>Int, e: String): Int =
    someFunc(e)

  def methodName1(p1: Int, p2:Int): Int = p1

  def methodName2(p: Int): Int=>Int =
    println(p+1)
    methodName2(p)
  def methodName(p: Int): Int = p*2

  def main(args: Array[String]): Unit = {
    val x = methodName
    println(x(3))
    println(f("Mark"))
    val y = methodName2(100)
    println(y(200))
//    println(mf((s:String)=> s.length, "CS476" + " TBH18F"))
  }
