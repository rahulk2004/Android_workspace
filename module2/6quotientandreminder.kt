package module2

//Kotlin Program to Compute Quotient and Remainder

fun main(){

    println("enter value : ")
    var n1 = readLine()!!.toInt()

    println("enter second value : ")
    var n2 = readLine()!!.toInt()

    var q = n1 / n2
    var r = n1 % n2

    println("$n1 / $n2 = $q ")
    println("$n1 % $n2 = $r")

}