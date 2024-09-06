package module2

//Kotlin Program to Find the Frequency of Character in a String

fun main(){

    println("enter string : ")
    var str= readLine()!!.toString()

    println("enter charachter : ")
    var ch = readLine()!!.single()
    var freq = 0

    for(i in 0..str.length-1){

        if(ch == str[i]){
            ++freq
        }
    }

    println("frequency of $ch = $freq")

}