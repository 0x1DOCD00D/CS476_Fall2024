object LambdaExperiments:
  val y1 = 3
  // lam x,y.x*y
// (lam x,y.x*y)(3)(5)
// (lam y.3*y)(5)
//
  val x = 10
  def hof1(i: Int): Int=>Int = ((x:Int)=>(y:Int)=>x*y)(i)
  
  {
    val x = hof1(3)
    println(x)
  }
  {
    {
      {
        val x = 3
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val y2 = 6
    println{
      val y3 = 7
      ((x:Int)=>x*y3)(5)
//      ((x:Int, y:Int)=>x*y)(5,3)
    }
    println(hof1(10)(20))
//    println{
//      new Function1[Int, Int]:
//        override def apply(v1: Int): Int = v1+1
//    }
  }