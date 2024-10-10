import scala.collection.mutable.ListBuffer

object Genericity:

  def id[TV](i: TV): TV = i
  def idf[TI]: TI=>String = (i:TI)=>i.toString

//  def id(i: Int): Int = i

//  def id(i: Float): Float = i

//  def id(i: Double): Double = i

  def mymap[From, To](lst: List[From])(f: From => To): List[To] =
    val len = lst.length
    var index = 0
    val dest = ListBuffer[To]()
    while (index < len) {
      dest += f(lst(index))
      index = index + 1
    }
    dest.toList

  def main(args: Array[String]): Unit = {
    println(id[Int](5))
    println(id("abc"))
    println(idf)
    println(idf(4.2f))
    println{
      mymap[String, Double](List[String]("aa", "aaa", "aabb"))((s:String)=>s.length.toDouble)
    }
  }