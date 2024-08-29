import ExpressionCalc.ExpOperation.Assign

import scala.collection.mutable

object ExpressionCalc:
  type EnvironmentTable = mutable.Map[String, Int]
  type EnvironmentTableContext = EnvironmentTable ?=> Int
  given envTable: EnvironmentTable = mutable.Map("evenNumber" -> 2)

  enum ExpOperation:
    case Value(i: Int)
    case Variable(s: String)
    case Add(p1: ExpOperation, p2: ExpOperation)
    case Mult(p1: ExpOperation, p2: ExpOperation)
    case Sub(p1: ExpOperation, p2: ExpOperation)
    case Assign(variable: Variable, v: Value)

  def eval(exp: ExpOperation): EnvironmentTableContext = exp match
    case ExpOperation.Value(i) => i
    case ExpOperation.Variable(s) => summon[EnvironmentTable].getOrElse(s, throw new Exception("not found"))
    case ExpOperation.Add(p1, p2) => eval(p1) + eval(p2)
    case ExpOperation.Mult(p1, p2) => eval(p1) * eval(p2)
    case ExpOperation.Sub(p1, p2) => eval(p1) - eval(p2)
    case Assign(v, i) =>
      summon[EnvironmentTable] ++= mutable.Map(v.s -> i.i)
      i.i

  def main(args: Array[String]): Unit = {
    import ExpOperation.*

    println(
      eval(Add(Value(2),Value(7)))
    )
    println(
      Add(Mult(Value(2),Add(Value(3),Value(5))),Add(Value(5),Value(7)))
    )
    println(
      eval(Assign(Variable("x"), Value(5)))
    )
    println(
      eval(Add(Mult(Variable("x"), Add(Value(3), Value(5))), Sub(Value(5), Value(7))))
    )
  }