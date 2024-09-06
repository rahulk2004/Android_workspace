package module2

//WAP to find max value using function as express

fun findmax(num1:Int,num2:Int){

    if(num1>num2){

        println(" $num1 is max value ")
    }
    else{

        println("$num2 is max value ")
    }
}


fun main(){

    println("enter first value :")
    var num1= readLine()!!.toInt()

    println("enter second value :")
    var num2= readLine()!!.toInt()

    var max = findmax(num1,num2)




}