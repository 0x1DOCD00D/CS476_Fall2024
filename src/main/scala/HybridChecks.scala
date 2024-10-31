object HybridChecks:
  case class Student(name: String)
  def inst(p: Any): Unit = {
    if p.isInstanceOf[Int] then println("Int")
    else println("Something else")
  }
  def f(p: Any): Unit = {
    p match
      case v: Int => 
        println(v+1)
      case v: String =>
        println(v.length)
      case v: Student  => println(v.name)
      case _ => println(s"No action defined on $p")
  }
  
  def main(args: Array[String]): Unit = {
    type MyType = Int | String | Student
    val v: List[MyType] = List(2, "str", Student("Connor"))
    
    inst(3)
    inst("string")
    f(2)
    f("CS476")
    f(Student("Yogesh"))
    f(2.3f)
  }
