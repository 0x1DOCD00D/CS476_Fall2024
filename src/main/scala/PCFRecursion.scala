object PCFRecursion:
  val _f: Int => Int = (i:Int)=>i
  val f: Int => Int = (i:Int)=>_f(i+1) //(i:Int)=>((j:Int)=>j)3
  val ff: Int => Int = (i:Int)=> f(f(i))

  def applyFunctionNtimes(f: Int =>Int, times: Int): Int => Int = {
    require(times >= 0)
    if times == 0 then (i:Int) => f(i)
    else if true then (i:Int) =>applyFunctionNtimes(f, times - 1)(f(i))
    else (i:Int) => f(i)
  }

  val etaF = applyFunctionNtimes

  def main(args: Array[String]): Unit = {
    println(_f(2))
    println(ff(2))
    println{
      applyFunctionNtimes((i:Int)=>i+1, 10)(2)
    }
  }