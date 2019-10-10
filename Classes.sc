object FirstWorkSheet {
  case class Student(var rollno: Int=1,var name: String="Christian",var marks:Int=90){
  def >(s2:Student):Boolean = marks>s2.marks

}

var s1= new Student()                             //> s1  : FirstWorkSheet.Student = Student(1,Christian,90)
var s2=new Student(2,"Mery",70)                   //> s2  : FirstWorkSheet.Student = Student(2,Mery,70)
s1.>(s2)                                          //> res0: Boolean = true
 
}
