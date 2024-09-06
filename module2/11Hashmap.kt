package module2

//WAP to create map instance and store values with State, City pair. Also print all value using loop. (Hashmap)

fun main(){

    var map = HashMap<String,String>()

    map.put("Gujrat","Rajkot")
    map.put("Maharashtra","Mumbai")
    map.put("Rajasthan","Jaipur")
    map.put("Haryana","Gudgao")

    for(i in map.entries){

        println(i)
    }


}