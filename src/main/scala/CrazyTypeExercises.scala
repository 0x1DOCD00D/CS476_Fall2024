object CrazyTypeExercises:
  val v: 1|2 = 2
  val ambig: String|Int = "s"

  def main(args: Array[String]): Unit = {
    def getRes(p: String| Int): String | Int =
      ambig match
        case a: String => a+"xxx"
        case a: Int => 7
        case a: Double => 7

    println(getRes(ambig))
    println(v)
  }
