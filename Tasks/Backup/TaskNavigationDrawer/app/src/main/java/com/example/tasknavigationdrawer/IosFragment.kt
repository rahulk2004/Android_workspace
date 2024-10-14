package com.example.tasknavigationdrawer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView


class IosFragment : Fragment() {

    lateinit var listView: ListView
    lateinit var list:MutableList<Model>

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_ios, container, false)

        listView = view.findViewById(R.id.list)
        list = ArrayList()

        list.add(Model("1 : overview ","What is Java? Java is a widely used object-oriented programming language and software platform that runs on billions of devices, including notebook computers, mobile devices, gaming consoles, medical devices and many others. The rules and syntax of Java are based on the C and C++ languages.",))
        list.add(Model("2 : Method Overloading  ","Method overloading in Java means having two or more methods (or functions) in a class with the same name and different arguments (or parameters). It can be with a different number of arguments or different data types of arguments."))
        list.add(Model("3 : Method Overriding","In Java, method overriding occurs when a subclass (child class) has the same method as the parent class. In other words, method overriding occurs when a subclass provides a particular implementation of a method declared by one of its parent classes."))
        list.add(Model("3 : Inheritance","Inheritance in Java is a mechanism where one class is allowed to inherit the fields and methods of another class. The class that inherits is called the subclass or child class, and the class from which it inherits is called the superclass or parent class."))
        list.add(Model("3 : Constructors","A constructor in Java Programming is a block of code that initializes (constructs) the state and value during object creation. It is called every time an object with the help of a new () keyword is created. Even if you haven't specified any constructor in the code, the Java compiler calls a default constructor."))


//        var adapter = MyAdapter(requireActivity(),list)
//        listView.adapter =adapter

        return view

    }


}