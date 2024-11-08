# Homework 3
## Description: extend a language for designers of the [fuzzy logic](https://en.wikipedia.org/wiki/Fuzzy_logic) with which they can create and evaluate simulated fuzzy logic gates that you created in HW1/HW2 with partial evaluation.
### Grade: 20%

## Preliminaries
As part of the previous homework assignments you gained experience with creating and managing your Git repository, implementing your first *domain-specific language (DSL)* using Scala for writing and evaluating fuzzy logic expressions for designers of the digital fuzzy logic to implement [logic gates](https://en.wikipedia.org/wiki/Logic_gate). Using your DSL users can now describe and evaluate fuzzy logic expressions using variables and scopes and classes and inheritance composition. You created [Scalatest](https://www.scalatest.org/) tests to test your implementation and [SBT build and run scripts](https://www.scala-sbt.org/) for your DSL project. Doing this homework is essential for successful completion of the rest of this course, and the third homework will share the same features of this homework: branching, merging, committing, pushing your code into your Git repo, creating test cases and build scripts, reusing the DSL that you will design and implement and employing various tools like a debugger for diagnosing problems with your applications.

## Overview
In your homework assignments you created a simple digital fuzzy expression evaluation language embedded in Scala that uses a dynamically updated environment to specify values for variables with computing and storing results of the evaluation of fuzzy logic expressions in variables and using them in different scopes as well as dynamic dispatch algorithms for resolving method invocations at runtime. In this third homework you will work on adding an optimization for partial evaluation, a programming language technique that allow undefined variables to remain in expressions during evaluation while preserving the syntactical form of the expressions. Consider the following example of partial evaluation.
```scala
Multiply(Value(3), Multiply(Add(Value(5), Value(1)), Variable("var")))
```
The result of the partial evaluation of this expression would not be a value but the following new expression.
```scala
Multiply(Value(3), Multiply(Value(6), Variable("var")))
```

A more interesting example of partial evaluation includes the use of the operator associativity property.
```scala
Multiply(Value(3), Multiply(Value(5), Variable("var")))
```
The result of the partial evaluation of this expression is the following new expression.
```scala
Multiply(Value(15), Variable("var"))
```

In case of the assignment expression the variable **somevar** will be assigned the corresponding expression on the right-hand side instead of a value in the environment table.
```scala
//examples of a variable assignment with scope
Assign(Variable("somevar"), Add(Variable("var"), Value(3)))
```

This homework script is written using a retroscripting technique, in which the homework outlines are generally and loosely drawn, and the individual students improvise to create the implementation that fits their refined objectives. In doing so, students are expected to stay within the basic requirements of the homework and they are free to experiments. Asking questions is important, so please ask away at our Teams channel!

## Functionality
To make partial evaluation more interesting you will introduce a new conditional construct in your language that allows programmers to specify conditions and to evaluate the corresponding branches. Consider the following examples of this construct.

```scala
//example definitions of the conditional construct IFTRUE
Assign("Variable("lhs"), IFTRUE(Multiply(Value(15), Variable("var")) GREATEREQUAL Add(Value(2), Variable("var1"))) THENEXECUTE( 
  Assign(Variable("somevar"), Add(Variable("var"), Value(3))) )
ELSERUN (
  Class("Derived", Extends(Class("Base")))
  Extends(Class("Derived"), Class("Base"))
  Class("Derived", List(ClassVar("v1", VarType("int")), ClassVar("v2", VarType("string")))
  val instance = CreateNew(Class("Derived"))
  val result = instance.InvokeMethod("m1", List(("p1", 1), ("p2", "howdy!"))
) )
```

With conditional expressions both the **IFTRUE** and **ELSERUN** branches should be partially evaluated, since it is not known what branch will be taken when all values are supplied for the remaining undefined variables. As before, in your language, programs are evaluated using the function **evaluate** that you created in your homeworks. Also, your environment table should be updated to map variables not only to values but also to expressions as a result of partial evaluation.

Special care should be taken when evaluating method invocations. Partial evaluation will be applied to all input parameter expressions and to the body of the method. The result of this partial evaluation should be an object that represents a partially evaluated method. Your homework can be divided roughly into five steps. First, you will decide on the semantics of partial evaluation and its reduction rules (e.g., operation associativity) for your language. Second, you will design/reuse your own homework language implementation with the conditional construct. You will add the logic for partially evaluating method invocations on class instances. Next, you will implement reduction rules in the evaluation function and document how they work. Fourth, you will determine how to partially evaluate method calls in presence of the dynamic dispatch mechanism, since it may require partially evaluating more than one method in the subsumption hierarchy. Finally, you will create Junit or Flatspec tests to verify the correctness of your implementation. You will write a short report to explain your implementation and the semantics of your language -- if the your code is commented well, it could be used as the report for evaluating yourcourse project.

## Baseline
Your absolute minimum gradeable baseline project should include all the constructs from the previous homeworks and their full and partial evaluations in the corresponding function **evaluate**. Your project should be buildable using the SBT, and your documentation must specify how you create and evaluate expressions in your language with appropriate test cases. You should extensively comment your code and use descriptive names for program entities - it is considered to be a good programming practice for code comprehension; if your code is easy to read with sufficient comments explaining the semantics of your language constructs, I will accept it as your official project documentation.

## Teams collaboration
You can post questions and replies, statements, comments, discussion, etc. on Teams. For this homework, feel free to share your ideas, mistakes, code fragments, commands from scripts, and some of your technical solutions with the rest of the class, and you can ask and advise others using Teams on language design issues, resolving error messages and dependencies and configuration issues. When posting question and answers on Teams, please select the appropriate label, i.e., HW3 to ensure that all discussion threads can be easily located. Active participants and problem solvers will receive bonuses from the big brother :-) who is watching your exchanges on Teams (i.e., your class instructor). However, *you must not post the source code of your program or specific details on how your implemented your design ideas!*

## Git logistics
**This is an individual homework.** Separate private repositories will be created for each of your homeworks and for the course project. Inviting other students to join your repo for an individual homework will result in losing your grade. **For grading, only the latest push timed before the deadline will be considered.** For more information about using the Git please use this [link as the starting point](https://confluence.atlassian.com/bitbucket/bitbucket-cloud-documentation-home-221448814.html). For those of you who struggle with the Git, I recommend a book by Ryan Hodson on Ry's Git Tutorial. The other book called Pro Git is written by Scott Chacon and Ben Straub and published by Apress and it is [freely available](https://git-scm.com/book/en/v2/). There are multiple videos on youtube that go into details of the Git organization and use.

I repeat, make sure that you will give the course instructor and your TA the read/write access to *your repository* so that we can leave the file feedback.txt with the explanation of the grade assigned to your homework.

## Discussions and submission
As it is mentioned above, you can post questions and replies, statements, comments, discussion, etc. on Teams. Remember that you cannot share your code and your solutions privately, but you can ask and advise others using Teams and StackOverflow or some other developer networks where resources and sample programs can be found on the Internet, how to resolve dependencies and configuration issues. Yet, your implementation should be your own and you cannot share it. Alternatively, you cannot copy and paste someone else's implementation and put your name on it. Your submissions will be checked for plagiarism. **Copying code from your classmates or from some sites on the Internet will result in severe academic penalties up to the termination of your enrollment in the University**. When posting question and answers on Teams, please select the appropriate folder, i.e., hw1 to ensure that all discussion threads can be easily located.


## Submission deadline and logistics
Sunday, November, 24, 2024 at 10PM CST you should submit a link to your Github repository using the corresponding Teams Assignment entry. Your submission will include the code, your documentation with instructions and detailed explanations on how to assemble and deploy your program along with the tests and a document that explains the semantics of your language, and what the limitations of your implementation are. Again, do not forget, please make sure that you will give your instructor/TA the write access to your repository. Your name should be shown in your README.md file and other documents. Your code should compile and run from the command line using the commands **sbt clean compile test** and **sbt clean compile run** or the corresponding commands for Gradle. Also, you project should be IntelliJ friendly, i.e., your graders should be able to import your code into IntelliJ and run from there. Use .gitignore to exlude files that should not be pushed into the repo.

## Evaluation criteria
- the maximum grade for this homework is 20%. Points are subtracted from this maximum grade: for example, saying that 2% is lost if some requirement is not completed means that the resulting grade will be 20%-2% => 18%; if the core homework functionality does not work, no bonus points will be given;
- only some basic expression language is implemented without partial evaluation and all constructs from the previous homeworks and nothing else is done: up to 15% lost;
- for each use of **var** instead of **val** 0.3% will be substracted from the maximum grade;
- for each non-spelling-related problem reported by the IntelliJ code analysis and inspection tool 0.2% will be substracted from the maximum grade;
- having less than five unit and/or integration tests that show how your implemented features work: up to 5% lost;
- missing comments and explanations from the program or using incomprehensible names for program variables and types, e.g., v29IxvT: up to 5% lost;
- no instructions in your README.md on how to install and to run your program: up to 5% lost;
- the program crashes without completing the core functionality or it is incorrect: up to 15% lost;
- the documentation exists but it is insufficient to understand how you assembled and deployed all language components: up to 15% lost;
- the minimum grade for this homework cannot be less than zero.

That's it, folks!