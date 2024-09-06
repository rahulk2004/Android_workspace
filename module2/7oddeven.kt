package module2

//Kotlin Program to Check Whether a Number is Even or Odd

fun main(){

    print("enter any number :")
    var n = readLine()!!.toInt()

    if(n%2==0){

        println("this number is even")
    }
    else {

        println("this number is odd")
    }
}
