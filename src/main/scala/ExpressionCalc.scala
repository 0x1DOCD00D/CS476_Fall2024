import ExpressionCalc.ExpOperation.:=

import scala.collection.mutable

object ExpressionCalc:
  type EnvironmentTable = mutable.Map[String, Int]
  type EnvironmentTableContext = EnvironmentTable ?=> Int
  given envTable: EnvironmentTable = mutable.Map("evenNumber" -> 2)
//  given envTable1: EnvironmentTable = mutable.Map("evenNumber" -> 2)

  def f(v: Int) = v+2
  def ff(v: Int) = ???//g+2

  def outerScope(param: String) = {
    var vasu = 476
    println(s"outer = $param")

    def innerScope(param:String) = {
      def vasuScope(param:String) = {
        val vasu = Map("c"->5)
        println(vasu)
      }
      vasu = 1
//      val vasu = "441"
      println(s"inner = $param")
      println(vasu)
      vasuScope("fff")
    }
    innerScope(param + "6")

  }

  enum ExpOperation:
    case Value(i: Int)
    case Variable(s: String)
    case Add(p1: ExpOperation, p2: ExpOperation)
    case Mult(p1: ExpOperation, p2: ExpOperation)
    case Sub(p1: ExpOperation, p2: ExpOperation)
    case :=(variable: Variable, v: Value)

  def eval(exp: ExpOperation): EnvironmentTableContext = exp match
    case ExpOperation.Value(i) => i
    case ExpOperation.Variable(s) => summon[EnvironmentTable].getOrElse(s, throw new Exception("not found"))
    case ExpOperation.Add(p1, p2) =>
      while(true) do {}
      eval(p1) + eval(p2)
    case ExpOperation.Mult(p1, p2) => eval(p1) * eval(p2)
    case ExpOperation.Sub(p1, p2) => eval(p1) - eval(p2)
    case :=(v, i) =>
      summon[EnvironmentTable] ++= mutable.Map(v.s -> i.i)
      i.i

  def main(args: Array[String]): Unit = {
    import ExpOperation.*
    outerScope("5")

    println(
      eval(Add(Value(2),Value(7)))
    )
    println {
      val x = Add(Value(3),Value(5))
      Add(Mult(Value(2),x),Add(Value(5),Value(7)))
    }
    println(
      eval(:=(Variable("x"), Value(5)))
    )
    println(
      eval(Add(Mult(Variable("x"), Add(Value(3), Value(5))), Sub(Value(5), Value(7))))
    )
  }