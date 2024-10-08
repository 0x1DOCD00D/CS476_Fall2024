object Genericity:

  def id[TV](i: TV): TV = i
  def idf[TI]: TI=>String = (i:TI)=>i.toString

//  def id(i: Int): Int = i

//  def id(i: Float): Float = i

//  def id(i: Double): Double = i

  def main(args: Array[String]): Unit = {
    println(id[Int](5))
    println(id("abc"))
    println(idf)
    println(idf(4.2f))
  }