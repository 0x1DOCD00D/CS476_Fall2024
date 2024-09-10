# Homework 1
### Description: create a language for designers of the [fuzzy logic](https://en.wikipedia.org/wiki/Fuzzy_logic) with which they can create and evaluate simulated fuzzy logic gates using variables and scopes to implement fuzzy logic functions.
### Grade: 15%

## Preliminaries
As part of this homework assignment you will gain experience with creating and managing your Git repository, implementing your first *domain-specific language (DSL)* using Scala for writing and evaluating set operation expressions for designers of the digital fuzzy logic to implement [logic gates](https://en.wikipedia.org/wiki/Logic_gate). Using your DSL users can describe and evaluate [boolean functions](https://en.wikipedia.org/wiki/Boolean_function) using variables and scopes. You will create [Scalatest](https://www.scalatest.org/) tests to test your implementation and you will create [SBT build and run scripts](https://www.scala-sbt.org/) for your DSL project. Feel free to come up with some cute name for your language. Doing this homework is essential for successful completion of the rest of this course, since all other homeworks will share the same features of this homework: branching, merging, committing, pushing your code into your Git repo, creating test cases and build scripts, reusing the DSL that you will design and implement and employing various tools like a debugger for diagnosing problems with your applications.

First things first, you must create your account at [Github](https://github.com), a Git repo management system. Then invite me, your course instructor as your collaborator – my github ID is 0x1DOCD00D and your TA whose github ID is unprosaiclabyrinth. Since it is a large class, please avoid direct emails from other accounts like funnybunny2000@gmail.com and use the corresponding channels on Teams instead. You will always receive a response within 12 hours at most.

Next, if you haven't done so, you will install [IntelliJ](https://www.jetbrains.com/student/) with your academic license, the JDK, the Scala runtime and the IntelliJ Scala plugin and the [Simple Build Toolkit (SBT)](https://www.scala-sbt.org/1.x/docs/index.html) and make sure that you can create, compile, and run Java and Scala programs. Please make sure that you can run [various Java tools from your chosen JDK between versions 8 and 18](https://docs.oracle.com/en/java/javase/index.html).

I recommend using [the fifth edition of the book on Programming in Scala by Martin Odersky and Lex Spoon et al](https://www.artima.com/shop/programming_in_scala_5ed). There are many other books and resources available on the Internet to learn Scala. Those who know more about functional programming can use the book on Functional Programming in Scala published on Sep 14, 2014 by Paul Chiusano and Runar Bjarnason and it is available using your academic subscription on [SafariBooksOnline](https://learning.oreilly.com/home/).

## Overview
In class I created and shared with you a simple arithmetic expression evaluation language embedded in Scala for multiplication and addition that uses a statically defined environment to specify values for variables. In this homework, you will create a similar language for fuzzy logic algebra and you will add expressions for storing results of some computations in variables and using them in different scopes.

[Fuzzy algebra](https://en.wikipedia.org/wiki/Fuzzy_mathematics) is a branch of mathematics that extends classical algebraic structures, such as Boolean algebra, to handle partial truth values. Unlike Boolean algebra, which operates with binary truth values (0 for false and 1 for true), fuzzy algebra allows for truth values in the range [0,1], where 0 is completely false, 1 is completely true, and values in between represent degrees of truth. Fuzzy algebra plays a significant role in fields like fuzzy logic, decision-making, artificial intelligence, and control systems, where uncertainties or imprecise information must be handled.

A fuzzy set A in a universe of discourse X is characterized by a membership function μ(x), where x∈X. The membership function assigns each element x a value in [0,1], representing the degree of membership of x in the fuzzy set A. Consider the following example: let X={x1,x2,x3,x4}. A fuzzy set A could be A={(x1,0.2),(x2,0.8),(x3,0.5),(x4,1.0)}. Here, x2 belongs to A with a degree of 0.8, and x4 with a degree of 1.0 (completely in A).

***You will implement the following basic functions for this homework.***

The membership function μ(x) of a fuzzy set A maps elements of the set X to values in [0,1]. The value μ(x) indicates how strongly x belongs to the fuzzy set. The fuzzy union A∪B of two fuzzy sets A and B is defined using the maximum of their membership values μ(A∪B)(x)=max(μA(x),μB(x)). For example: let A={(x1,0.2),(x2,0.8)} and B={(x1,0.5),(x2,0.6)}. The union A∪B is: A∪B={(x1,0.5),(x2,0.8)} (using the maximum of each membership).

The fuzzy intersection A∩B of two fuzzy sets A and B is defined using the minimum of their membership values: μA∩B(x)=min(μA(x),μB(x)). With the same sets as before A∩B={(x1,0.2),(x2,0.6)} (using the minimum of each membership).

The complement of a fuzzy set A, denoted as Ac, is defined as μAc(x)=1−μA(x). If A={(x1,0.2),(x2,0.8)} then the complement Ac is Ac={(x1,0.8),(x2,0.2)}.
 
Just like in classical algebra, fuzzy algebra allows us to perform various operations, but instead of binary logic, these operations handle fuzzy logic with degrees of truth. 
* Fuzzy addition is a way to combine membership values that reflects how much two fuzzy sets are "together." μA+B(x)=min(1,μA(x)+μB(x)). The result is capped at 1, as the membership values cannot exceed 1.
* Fuzzy multiplication reflects how two fuzzy sets "intersect" in strength: μA⋅B(x)=μA(x)⋅μB(x). Let A={(x1,0.4),(x2,0.7)} and B={(x1,0.5),(x2,0.6)}. The fuzzy multiplication A⋅BA⋅B is A⋅B={(x1,0.2),(x2,0.42)}
* The α-cut of a fuzzy set A is a crisp (ordinary) set that contains all elements of AA whose membership values are greater than or equal to αα, where α is a threshold in [0,1]. Aα={x∈X∣μA(x)≥α}. For a fuzzy set A={(x1,0.3),(x2,0.7),(x3,0.8)}, the 0.6-cut would be A0.6={x2,x3} as both x2 and x3 have membership values ≥ 0.6.

In classical logic, propositions are either true (1) or false (0). Operations are binary (AND, OR, NOT, XOR, NAND) whereas in fuzzy logic, truth values can be any value between 0 and 1. Operations like fuzzy union (OR), fuzzy intersection (AND), and fuzzy complement (NOT) operate on these degrees of truth rather than just binary values. Let’s say we have two fuzzy sets A and B with membership values defined over a common universe X={x1,x2,x3}.
* Set A={(x1,0.2),(x2,0.7),(x3,0.5)}
* Set B={(x1,0.6),(x2,0.3),(x3,0.5)}
Now, let's compute the fuzzy XOR (⊕) for each element of the sets using the formula: μA⊕B(x)=max(μA(x),μB(x))−min(μA(x),μB(x)). For x1: max(μA(x1),μB(x1))=max(0.2,0.6)=0.6 and min(μA(x1),μB(x1))=min(0.2,0.6)=0.2. So, μA⊕B(x1)=0.6−0.2=0.4. For x2 max(μA(x2),μB(x2))=max(0.7,0.3)=0.7 and min(μA(x2),μB(x2))=min(0.7,0.3)=0.3, so, μA⊕B(x2)=0.7−0.3=0.4. For x3, max(μA(x3),μB(x3))=max(0.5,0.5)=0.5 and min(μA(x3),μB(x3))=min(0.5,0.5)=0.5, so, μA⊕B(x3)=0.5−0.5=0. Thus, the fuzzy XOR operation A⊕B yields the following fuzzy set: A⊕B={(x1,0.4),(x2,0.4),(x3,0.0)}. This fuzzy set represents the XOR result, showing the degree of exclusivity between A and B for each element x.

Consider the following example of using your language - your implementation may differ with respect to your chosen syntax but the semantics should be aligned with this example.
```scala
//The operator Assign takes the specification of the logic gate and assigns it to a variable named logicGate1. 
Assign(FuzzyGate("logicGate1"), ADD(MULT(Input(("A", 0.2)), Input(("B", 0.8)))))
//test the created logic gate by specifying inputs for this logic gate
//in this case the scope of the input variables is defined by the logic gate module/variable
Scope(LogicGate("logicGate1"), Assign(Input("A"), 0.5))
Scope(LogicGate("logicGate1"), Assign(Input("B"), 0.7))
//The operation TestGate evaluates the logic gate and compares the result of the
//evaluation with the expected value. If the evaluation succeeds then the return
//value of the operation TestGate is true, false otherwise.
TestGate("logicGate1", "A") //it should return the value 0.5
//we create a new composite logic gate that combines the previously defined logic
//gate logicGate1 with an input named C using the boolean operation XOR. 
Assign(FuzzyGate("compositeGate"), XOR(LogicGate("logicGate1"), Input("C")))
TestGate("compositeGate", ("A", 0.2)) //it should return error since the input C is not defined.
```

This homework script is written using a retroscripting technique, in which the homework outlines are generally and loosely drawn, and the individual students improvise to create the implementation that fits their refined objectives. In doing so, students are expected to stay within the basic requirements of the homework and they are free to experiments. Asking questions is important, so please ask away on the corresponding Teams channels!

## Functionality
In your language, scopes can be created dynamically as part of the expressions in addition to being predefined in the environment. Your homework can be divided roughly into five steps. First, you learn how to configure and run your Scala project using the SBT with the code that I wrote in class. Second, you design your own language for logic gate operations and their evaluation and you can create your homework by building on the class example. You will add the logic for binding logic gate objects to variables, which will later be used in the extensions of this language in your next homeworks. Next, you will create an implementation of scopes, named and anonymous with scoping rules for obscuring and shadowing that you define to resolve the values of variable inputs for logic gate expressions that have the same names in expressions. Fourth, you will construct fuzzy logic tests for your implementation. Finally, you will create Scalatest tests to verify the correctness of your implementation for at least five constructed logic gates. You will write a report to explain your implementation and the semantics of your language that describes types and their evaluations. Add the corresponding entries to your build.sbt to enable Scalatest using the latest available version.
```scala
"org.scalatest" %% "scalatest" % scalacticVersion % Test,
"org.scalatest" %% "scalatest-featurespec" % scalacticVersion % Test,
```

An example of the Scalatest is shown below.
```scala
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class YourSetTheoryLanguageTest extends AnyFlatSpec with Matchers {
  behavior of "my first language for set theory operations"

  it should "create a gate to test the De Morgan's law" in {
    XOR(FuzzySet("A", List((Element("x1"),0.2),(Element("x2"),0.7),(Element("x3"),0.5))), 
      FuzzySet("B", List((Element("x1",0.6),(Element("x2"),0.3),(Element("x3"),0.5)))).eval() shouldBe List(Element("x1"),0.4),(Element("x2"),0.4),(Element("x3"),0.0))
  }
```

## Baseline
To be considered for grading, your project should include the constructs Assign, Scope, TestGate, and all required boolean operations and your project should be buildable using the SBT, and your documentation must specify how you create and evaluate expressions in your language.

## Teams collaboration
You can post questions and replies, statements, comments, discussion, etc. on Teams. For this homework, feel free to share your ideas, mistakes, code fragments, commands from scripts, and some of your technical solutions with the rest of the class, and you can ask and advise others using Teams on language design issues, resolving error messages and dependencies and configuration issues. When posting question and answers on Teams, please select the appropriate folder, i.e., HW1 to ensure that all discussion threads can be easily located. Active participants and problem solvers will receive bonuses from the big brother :-) who is watching your exchanges on Teams (i.e., your class instructor). However, *you must not post the source code of your program or specific details on how your implemented your design ideas!*

## Git logistics
**This is an individual homework.** Separate private repositories will be created for each of your homeworks and for the course project. Inviting other students to join your repo for an individual homework will result in losing your grade. **For grading, only the latest push timed before the deadline will be considered.** For more information about using the Git please use this [link as the starting point](https://confluence.atlassian.com/bitbucket/bitbucket-cloud-documentation-home-221448814.html). For those of you who struggle with the Git, I recommend a book by Ryan Hodson on Ry's Git Tutorial. The other book called Pro Git is written by Scott Chacon and Ben Straub and published by Apress and it is [freely available](https://git-scm.com/book/en/v2/). There are multiple videos on youtube that go into details of the Git organization and use.

I repeat, make sure that you will give the course instructor and your TA the read/write access to *your repository* so that we can leave the file feedback.txt with the explanation of the grade assigned to your homework.

## Discussions and submission
As it is mentioned above, you can post questions and replies, statements, comments, discussion, etc. on Teams. Remember that you cannot share your code and your solutions privately, but you can ask and advise others using Teams and StackOverflow or some other developer networks where resources and sample programs can be found on the Internet, how to resolve dependencies and configuration issues. Yet, your implementation should be your own and you cannot share it. Alternatively, you cannot copy and paste someone else's implementation and put your name on it. Your submissions will be checked for plagiarism. **Copying code from your classmates or from some sites on the Internet will result in severe academic penalties up to the termination of your enrollment in the University**. When posting question and answers on Teams, please select the appropriate folder, i.e., hw1 to ensure that all discussion threads can be easily located.


## Submission deadline and logistics
Sunday, September 29, 2024 at 10PM CST you should submit a link to your Github repository using the corresponding Teams Assignment entry. Your submission will include the code, your documentation with instructions and detailed explanations on how to assemble and deploy your program along with the tests and a document that explains the semantics of your language, and what the limitations of your implementation are. Again, do not forget, please make sure that you will give your instructor/TA the write access to your repository. Your name should be shown in your README.md file and other documents. Your code should compile and run from the command line using the commands **sbt clean compile test** and **sbt clean compile run** or the corresponding commands for Gradle. Also, you project should be IntelliJ friendly, i.e., your graders should be able to import your code into IntelliJ and run from there. Use .gitignore to exlude files that should not be pushed into the repo.

## Evaluation criteria
- the maximum grade for this homework is 15%. Points are subtracted from this maximum grade: for example, saying that 2% is lost if some requirement is not completed means that the resulting grade will be 15%-2% => 13%; if the core homework functionality does not work, no bonus points will be given;
- only some basic expression language is implemented without scopes and assignments and TestGate and nothing else is done: up to 10% lost;
- for each use of **var** instead of **val** 0.3% will be substracted from the maximum grade;
- for each non-spelling-related problem reported by the IntelliJ code analysis and inspection tool 0.2% will be substracted from the maximum grade;
- having less than five unit and/or integration tests that show how your implemented features work: up to 5% lost;
- missing comments and explanations from the program or using incomprehensible names for program variables and types, e.g., v29IxvT: up to 5% lost;
- no instructions in your README.md on how to install and to run your program: up to 5% lost;
- the program crashes without completing the core functionality or it is incorrect: up to 10% lost;
- the documentation exists but it is insufficient to understand how you assembled and deployed all language components: up to 10% lost;
- the minimum grade for this homework cannot be less than zero.

That's it, folks!
