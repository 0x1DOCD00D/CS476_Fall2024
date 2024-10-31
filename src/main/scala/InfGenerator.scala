object InfGenerator:
//  data list ::= Nil | hd :: list
// 1 :: (2 :: (3::Nil))
  def inf(bc: Int): LazyList[Int] = bc #:: inf(bc+1)

  def main(args: Array[String]): Unit = {
    inf(-1)
    println(inf(2))
    val ll = inf(1)
    val ll1 = ll.take(10).toList
    val ll2 = ll.drop(10).take(10).toList
    println{ ll1
    }
    println(ll2)
  }