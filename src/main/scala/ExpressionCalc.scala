import ExpressionCalc.ExpOperation
import ExpressionCalc.ExpOperation.Add

object ExpressionCalc:
  type EnvironmentTable = Map[String, Int]
  given envTable: EnvironmentTable = Map("evenNumber" -> 2)

  enum ExpOperation:
    case Value(i: Int)
    case Variable(s: String)
    case Add(p1: ExpOperation, p2: ExpOperation)
    case Mult(p1: ExpOperation, p2: ExpOperation)
    case Sub(p1: ExpOperation, p2: ExpOperation)

  def eval(exp: ExpOperation): Int = exp match
    case ExpOperation.Value(i) => i
    case ExpOperation.Variable(s) => summon[EnvironmentTable].getOrElse(s, throw new Exception("not found"))
    case ExpOperation.Add(p1, p2) => eval(p1) + eval(p2)
    case ExpOperation.Mult(p1, p2) => eval(p1) * eval(p2)
    case ExpOperation.Sub(p1, p2) => eval(p1) - eval(p2)

  def main(args: Array[String]): Unit = {
    import ExpOperation.*

    println(
      eval(Add(Value(2),Value(7)))
    )
    println(
      Add(Mult(Value(2),Add(Value(3),Value(5))),Add(Value(5),Value(7)))
    )
    println(
      eval(Add(Mult(Variable("evenNumber"), Add(Value(3), Value(5))), Sub(Value(5), Value(7))))
    )
  }