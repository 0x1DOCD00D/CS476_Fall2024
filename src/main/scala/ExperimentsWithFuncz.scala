object ExperimentsWithFuncz:
  val f: String =>Int = (s:String)=> s.length
  val g: Int => Double = (s:Int)=> s.toDouble * 1.5
//  val g : String =>Int =>Int = (x:String =>Int) =>

  def functionComposer[Type1, Type2, Type3](f: Type2=>Type1, g: Type3=>Type2): Type3=>Type1 = (i:Type3) =>f(g(i))

  def mf[Kaushal,Yogesh](function: Kaushal=>Yogesh, input: Kaushal): Yogesh =//e
    function(input)

  def mf1(function: Any=>Any, input: Any): Any =//e
    function(input)

  def methodName1(p1: Int, p2:Int): Int = p1

  def methodName2(p: Int): Int=>Int =
    println(p+1)
    methodName2(p)
  def methodName(p: Int): Int = p*2

  def main(args: Array[String]): Unit = {
    val x = methodName
    println(x(3))
    println(f("Mark"))
//    val y = methodName2(100)
//    println(y(200))
    println(mf((s:String)=> s.length, "CS476" + " TBH18F"))
    println{
//      functionComposer(f, g)("Kaushal")
    }
//    println(mf1((s:String)=> s.length, 78))
  }
