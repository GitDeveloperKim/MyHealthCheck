package com.example.myapplication01.vo

enum class Sex {
    MALE, FEMALE
}

class User(name: String) {
    var name:String=""
    var weight = 0
    var height = 1.0
    var bmi:Double = 1.0
    var sex:Sex = Sex.MALE

    init {
        this.name = name
    }

    constructor(name: String, weight: Int, height: Double, sex: Sex) :this(name){
        this.weight = weight
        this.height = height
        this.sex = sex
    }

    fun calculateBMI() {
        this.bmi = this.weight/(this.height * this.height)
    }
}