object Continuations:
  type Continuation[A] = (A => Unit) => Unit

  // A computation that passes its result to the continuation (CPS style)
  def computation1(x: Int): Continuation[Int] = { cont =>
    println(s"Computation 1: Input = $x")
    println(s"Computation 1: Cont = $cont")
    val result = x + 1
    val ir = cont(result)  // Pass result to the continuation
    println(ir)
    ir
  }

  def computation2(y: Int): Continuation[Int] = { cont =>
    println(s"Computation 2: Input = $y")
    val result = y * 2
    cont(result)  // Pass result to the next continuation
  }

  def computation3(z: Int): Continuation[Int] = { cont =>
    println(s"Computation 3: Input = $z")
    val result = z - 3
    cont(result)  // Final result passed to the continuation
  }

  // A function to run the continuation chain
  def runContinuations[A](initial: A)(continuation: Continuation[A]): Unit = {
    continuation(result => println(s"Final result: $result"))
  }

  // Compose computations manually using CPS
  val composedContinuations: Continuation[Int] = { cont =>
    computation1(10) { result1 =>
      println(s"after comp 1: $result1")
      computation2(result1) { result2 =>
        println(s"after comp 2: $result2")
        computation3(result2)(cont) // Continue to the final continuation
      }
    }
  }

  def main(args: Array[String]): Unit = {
    runContinuations(10)(composedContinuations)
  }
