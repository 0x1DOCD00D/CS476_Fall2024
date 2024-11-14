object DynamicDispatch:
  class P:
    def m1() = ()
    def m2() = ()
    def m3() = ()

  class C extends P:
    override def m1() = ()
    def m4() = ()

  def dd(o:Any): Any =
    o.isInstanceOf[P]

  def main(args: Array[String]): Unit = {
    val o:P = C()
    val l = List(P(), C(), P())

    l(1).m2()
//    o.m4()
  }
