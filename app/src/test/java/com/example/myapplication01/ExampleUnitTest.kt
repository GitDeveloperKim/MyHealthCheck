package com.example.myapplication01

import com.example.myapplication01.vo.Sex
import com.example.myapplication01.vo.User
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun user_name_is_corrent() {
        var kimyoungseok = User("kimyoungseok")
        assertEquals("kimyoungseok", kimyoungseok.name)
    }

    @Test
    fun user_height_is_correct() {
        var kimyoungseok = User("kimyoungseok", 80, 1.8, Sex.MALE)
        assertEquals(80, kimyoungseok.weight)
    }

    @Test
    fun user_weight_is_correct() {
        var kimyoungseok = User("kimyoungseok", 80, 1.8, Sex.MALE)
        assertEquals(1.8, kimyoungseok.height,0.1)
    }

    @Test
    fun user_bmi_is_correct() {
        // given
        var kimyoungseok = User("kimyoungseok", 80, 1.8, Sex.MALE)
        // when
        kimyoungseok.calculateBMI()
        // then
        assertEquals(80/(1.8*1.8), kimyoungseok.bmi, 0.1)
    }
}