object test{
val myNums= 1 to 3                                //> myNums  : scala.collection.immutable.Range.Inclusive = Range 1 to 3
myNums.map(i=> (1 to i).map(j=> i*j))             //> res0: scala.collection.immutable.IndexedSeq[scala.collection.immutable.Indexe
                                                  //| dSeq[Int]] = Vector(Vector(1), Vector(2, 4), Vector(3, 6, 9))
val rout=myNums.flatMap(i=>(1 to i).map(j=>i*j))  //> rout  : scala.collection.immutable.IndexedSeq[Int] = Vector(1, 2, 4, 3, 6, 9
                                                  //| )
println(rout)                                     //> Vector(1, 2, 4, 3, 6, 9)

//using for expressions
val rout1=for {
	i<- myNums
	j<- 1 to i
} yield i*j                                       //> rout1  : scala.collection.immutable.IndexedSeq[Int] = Vector(1, 2, 4, 3, 6, 
                                                  //| 9)
println(rout1)                                    //> Vector(1, 2, 4, 3, 6, 9)

//filtering
val rout2=for {
	i<- myNums if i%2==1
	j<- 1 to i
} yield i*j                                       //> rout2  : scala.collection.immutable.IndexedSeq[Int] = Vector(1, 3, 6, 9)
println(rout2)                                    //> Vector(1, 3, 6, 9)

//definitions

for (n<- 1 to 3) println(n)                       //> 1
                                                  //| 2
                                                  //| 3
(1 to 3).foreach(n=>println(n))                   //> 1
                                                  //| 2
                                                  //| 3
//pattern matching
case class Customer(first: String ="",
										last:String="")
							
def isCustomer(someValue: Any): Boolean={
	someValue match{
		case cust: Customer => true
		case _ => false
		}
	}                                         //> isCustomer: (someValue: Any)Boolean

val cust1=Customer("Martin","Odersky")            //> cust1  : test.Customer = Customer(Martin,Odersky)
println(isCustomer(cust1))                        //> true
println(isCustomer("Martin Odersky"))             //> false

//Pattern Matching Tuple Values
val tuple=(1,"a",2,"b")                           //> tuple  : (Int, String, Int, String) = (1,a,2,b)
println(tuple._3)                                 //> 2
val (first,second,third,fourth)=tuple             //> first  : Int = 1
                                                  //| second  : String = a
                                                  //| third  : Int = 2
                                                  //| fourth  : String = b
println(first)                                    //> 1
println(second)                                   //> a
println(third)                                    //> 2
println(fourth)                                   //> b
 //Pattern  Matching HOF Arguments
 val range1= 1 to 5                               //> range1  : scala.collection.immutable.Range.Inclusive = Range 1 to 5
 println(range1.reduce((acc,cur)=>acc+cur))       //> 15
 println(range1.foldLeft(0){ case (acc,cur)=> acc+cur})
                                                  //> 15

//Handling Options
case class Customer1(first:String="",
											middle: Option[String]=None,
											last: String="")
											
def getMiddleName(value: Option[String]): String={
		value match{
			case Some(middleName) => middleName
			case None=> "No middle name"
			}
	}                                         //> getMiddleName: (value: Option[String])String

val martin= Customer1("Martin",last="Odersky")    //> martin  : test.Customer1 = Customer1(Martin,None,Odersky)
println(getMiddleName(martin.middle))             //> No middle name

val jane=Customer1("Jane",Option("D."),"Doe")     //> jane  : test.Customer1 = Customer1(Jane,Some(D.),Doe)
println(getMiddleName(jane.middle))               //> D.

//HOF and Option
val rout4=Option("Martin")                        //> rout4  : Option[String] = Some(Martin)
println(rout4.map(name=>println("Yay, "+name)))   //> Yay, Martin
                                                  //| Some(())
//For Expression and Option
val rout5=Option("Jane")                          //> rout5  : Option[String] = Some(Jane)
val rout6=for{
		m <- rout4
		j <- rout5
	} yield (m + " is friend with " + j)      //> rout6  : Option[String] = Some(Martin is friend with Jane)
println(rout6)                                    //> Some(Martin is friend with Jane)

//Handling Failures
import scala.util.{Try,Success,Failure}
println("100".toInt)                              //> 100
//println("Martin".toInt)

def makeInt(s:String): Int= Try(s.toInt) match{
		case Success(n)=>n
		case Failure(_)=>0
	}                                         //> makeInt: (s: String)Int
	
println(makeInt("35"))                            //> 35
println(makeInt("James"))                         //> 0

//Higher Order Functions and Try


def getScala1: Try[String] = Success("Scala")     //> getScala1: => scala.util.Try[String]
val scala1=getScala1                              //> scala1  : scala.util.Try[String] = Success(Scala)
println(scala1)                                   //> Success(Scala)

/*
//Handling Futures
import scala.concurrente.Future
import scala.concurrent.ExecutionContext
import java.util.concurrent.ForkJoinPool
//import scala.util.Failure
//import scala.util.Success
import scala.concurrente.duration._

implicit val ec: ExecutionContext=
	ExecutionContext.fromExcutor(new ForkJoinPool())

implicit val timeout= 1 second

//Pattern Matchin on Future
val f: Future[Int] =Future {1+2+3}

f.onComplete{
		case Success(i)=>println("onComplete Success: "+i)
		case Failure(f)=>println("OnComplete Failure: "+f)
	}

	
*/
}

