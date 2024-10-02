# Homework 2
### Description: extend a language that you created in HW1 with classes, inheritance and dynamic dispatch for designers of the [fuzzy logic](https://en.wikipedia.org/wiki/Fuzzy_logic) with which they can create and evaluate simulated fuzzy logic gates using variables and scopes to implement fuzzy logic functions
### Grade: 15%

## Preliminaries
As part of this homework assignment you will gain experience with creating and managing your Git repository, implementing your first *domain-specific language (DSL)* using Scala for writing and evaluating set operation expressions for designers of the digital fuzzy logic to implement [logic gates](https://en.wikipedia.org/wiki/Logic_gate). Using your DSL users can describe and evaluate [boolean functions](https://en.wikipedia.org/wiki/Boolean_function) using variables and scopes. You will create [Scalatest](https://www.scalatest.org/) tests to test your implementation and you will create [SBT build and run scripts](https://www.scala-sbt.org/) for your DSL project. Feel free to come up with some cute name for your language. Doing this homework is essential for successful completion of the rest of this course, since all other homeworks will share the same features of this homework: branching, merging, committing, pushing your code into your Git repo, creating test cases and build scripts, reusing the DSL that you will design and implement and employing various tools like a debugger for diagnosing problems with your applications.

First things first, if you haven't done so you must create your account at [Github](https://github.com), a Git repo management system. Then invite me, your course instructor as your collaborator – my github ID is 0x1DOCD00D and your TA whose github ID is unprosaiclabyrinth. Since it is a large class, please avoid direct emails from other accounts like funnybunny2000@gmail.com and use the corresponding channels on Teams instead. You will always receive a response within 12 hours at most.

Next, if you haven't done so, you will install [IntelliJ](https://www.jetbrains.com/student/) with your academic license, the JDK, the Scala runtime and the IntelliJ Scala plugin and the [Simple Build Toolkit (SBT)](https://www.scala-sbt.org/1.x/docs/index.html) and make sure that you can create, compile, and run Java and Scala programs. Please make sure that you can run [various Java tools from your chosen JDK between versions 8 and 22](https://docs.oracle.com/en/java/javase/index.html).

I recommend using [the fifth edition of the book on Programming in Scala by Martin Odersky and Lex Spoon et al](https://www.artima.com/shop/programming_in_scala_5ed). There are many other books and resources available on the Internet to learn Scala. Those who know more about functional programming can use the book on Functional Programming in Scala published on Sep 14, 2014 by Paul Chiusano and Runar Bjarnason and it is available using your academic subscription on [SafariBooksOnline](https://learning.oreilly.com/home/).

## Overview
The main goal of this homework is to design a toy language that has important features of general-purpose languages: variables, scopes, functions, etc. In this course, students do not design a full-fledged external compiler by creating the full compilation chain, i.e., starting with the lexer/parser for a language grammar and going all the way to code generation/optimization for specific platforms. This is the type of homework that is assigned in a compiler course, but ours if different - it is about a language design and the choices you make as a language designer.

With that in mind the description of this homework does not dictate you the exact constructs and the exact semantics for these constructs. The examples that I provided are intentionally vague and riddled with inconsistencies, so that you execute the function application expression: yourbrain.apply(homework). The language constructs like Scope are given a range of possible choices, not a single direction meaning that you can interpret these constructs differently but within the general definition of what these constructs mean in the PL theory. That is, you cannot interpret the construct Variable as an iteration abstraction, however, it is up to you to decide how to represent a variable and how to map names to values.

This homework script is written using a retroscripting technique, in which the homework outlines are generally and loosely drawn, and the individual students improvise to create the implementation that fits their refined objectives. In doing so, students are expected to stay within the basic requirements of the homework and they are free to experiments. Asking questions is important, so please ask away on the corresponding Teams channels!

In your first homework assignment you created an evaluation language embedded in Scala for fuzzy logic designers that uses a dynamically updated environment to specify values for variables with computing and storing results of the evaluation of arithmetic expressions in variables and using them in different scopes. In this homework you will build on the language and its features created in your first homework. The requirements for this homework below is an outline - you can add more interesting features to your language for additional bonus points. Alternatively, if you implement only a subset of the required features please make sure to specify it in your documentation. 

## Functionality
We have learned that a class is a named scope that contains variable and method definitions and it may contain nested classes. Inheritance is used as a mechanism for software reuse, so that when a class extends some superclass it inherits its methods and new ones can be added to the subclass. In this homework, class methods can contain arithmetic expressions and the return value of a method is the value of the last expression that is contained in the method. It is up to you to decide how you can allow programmers to invoke the methods of the superclasses and what the semantics of the empty methods is.
```scala
//example definitions of a class
Class("Base")
Class("Derived", Extends(Class("Base")))
Extends(Class("Derived"), Class("Base"))

//example declaration of class variables
Class("Derived", List(ClassVar("v1", VarType("int")), ClassVar("v2", VarType("string")))

//example definition of class methods
Method(Class("Base"), MethodName("m1"), List(Parameter("p1", ParamType("int")), Parameter("p2", ParamType("string"))), List(Assign(Variable("somevar"), Add(Variable("var"), Macro("someName"))), Let(Assign(Variable("var2"), Add(Variable("var"), Macro("someName")))) In Add(Variable("var2"), Value(1)))) 

//example of the instantiation of a class
val instance = CreateNew(Class("Derived"))

//example of the invocation of a method
val result = instance.InvokeMethod("m1", List(("p1", 1), ("p2", "howdy!"))

//examples of defining nested classes
Class("Base", Class("Nested"))
Class("Derived", Class("Inner1st", Class("Inner2nd")))
```
In your language, programs are evaluated using the function **evaluate** that you created in your first homework. You can decide whether you want to evaluate expressions only to integer values or you may extend evaluations to other types, e.g., strings and floats. Handling nested classes is not much different from handling nested scopes - you will define your own rules for accessing variables and methods in nested classes and for accessing methods and variables of the superclasses from subclasses and of the outer classes from the nested classes. Correspondingly, you will maintain the environment for mapping variables to values and for implementing your scoping rules.

Your homework can be divided roughly into five steps. First, you will decide on the semantics of inheritance and dynamic dispatch for your language. Second, you will design your own object-oriented language extension using the example with case classes shown above. You will add the logic for evaluating method invocations on class instances. Next, you will create an implementation of nested classes with scoping rules for obscuring and shadowing that you define to resolve the values of class variables, which have the same names in nested classes. Fourth, you will create the dynamic dispatch mechanism to determine which methods are invoked on class instances. Finally, you will create scalatests to verify the correctness of your implementation. You will write a short report to explain your implementation and the semantics of your language -- if the your code is commented well, it could be used as the report for evaluating your homework.

## Baseline
Your absolute minimum gradeable baseline project should include the constructs Class, Extends, CreateNew, InvokeMethod, Parameter, Method, ClassVar and VarType or their equivalents and their evaluations in the corresponding function evaluate. Your project should be buildable using the SBT, and your documentation must specify how you create and evaluate expressions in your language with appropriate test cases. Extensively commenting your code and using descriptive names for program entities are considered good programming practices for code comprehension; if your code is easy to read with sufficient comments explaining the semantics of your language constructs, I will accept it as your official homework documentation.

## Teams collaboration
You can post questions and replies, statements, comments, discussion, etc. on Teams. For this homework, feel free to share your ideas, mistakes, code fragments, commands from scripts, and some of your technical solutions with the rest of the class, and you can ask and advise others using Teams on language design issues, resolving error messages and dependencies and configuration issues. When posting question and answers on Teams, please select the appropriate folder, i.e., HW1 to ensure that all discussion threads can be easily located. Active participants and problem solvers will receive bonuses from the big brother :-) who is watching your exchanges on Teams (i.e., your class instructor). However, *you must not post the source code of your program or specific details on how your implemented your design ideas!*

## Git logistics
**This is an individual homework.** Separate private repositories will be created for each of your homeworks and for the course project. Inviting other students to join your repo for an individual homework will result in losing your grade. **For grading, only the latest push timed before the deadline will be considered.** For more information about using the Git please use this [link as the starting point](https://confluence.atlassian.com/bitbucket/bitbucket-cloud-documentation-home-221448814.html). For those of you who struggle with the Git, I recommend a book by Ryan Hodson on Ry's Git Tutorial. The other book called Pro Git is written by Scott Chacon and Ben Straub and published by Apress and it is [freely available](https://git-scm.com/book/en/v2/). There are multiple videos on youtube that go into details of the Git organization and use.

I repeat, make sure that you will give the course instructor and your TA the read/write access to *your repository* so that we can leave the file feedback.txt with the explanation of the grade assigned to your homework.

## Discussions and submission
As it is mentioned above, you can post questions and replies, statements, comments, discussion, etc. on Teams. Remember that you cannot share your code and your solutions privately, but you can ask and advise others using Teams and StackOverflow or some other developer networks where resources and sample programs can be found on the Internet, how to resolve dependencies and configuration issues. Yet, your implementation should be your own and you cannot share it. Alternatively, you cannot copy and paste someone else's implementation and put your name on it. Your submissions will be checked for plagiarism. **Copying code from your classmates or from some sites on the Internet will result in severe academic penalties up to the termination of your enrollment in the University**. When posting question and answers on Teams, please select the appropriate folder, i.e., hw1 to ensure that all discussion threads can be easily located.


## Submission deadline and logistics
Sunday, November, 3, 2024 at 10PM CST you should submit a link to your Github repository using the corresponding Teams Assignment entry. Your submission will include the code, your documentation with instructions and detailed explanations on how to assemble and deploy your program along with the tests and a document that explains the semantics of your language, and what the limitations of your implementation are. Again, do not forget, please make sure that you will give your instructor/TA the write access to your repository. Your name should be shown in your README.md file and other documents. Your code should compile and run from the command line using the commands **sbt clean compile test** and **sbt clean compile run** or the corresponding commands for Gradle. Also, you project should be IntelliJ friendly, i.e., your graders should be able to import your code into IntelliJ and run from there. Use .gitignore to exlude files that should not be pushed into the repo.

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
