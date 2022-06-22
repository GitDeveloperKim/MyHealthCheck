package com.example.myapplication01.dao

class User(name: String) {
    var name:String=""
    var weight = 0
    var height = 1.0
    var bmi:Double = 1.0

    init {
        this.name = name
    }

    constructor(name: String, weight: Int, height: Double) :this(name){
        this.weight = weight
        this.height = height
    }

    fun calculateBMI() {
        this.bmi = this.weight/(this.height * this.height)
    }
}