package com.babylonml

import org.junit.jupiter.api.Test

class EmptyTaskGraph : AbstractTvmTest() {
    @Test
    fun emptyTaskGraph() {
        val inputArray = TvmFloatArray.fromArray(
            floatArrayOf(1.0f, 2.0f, 3.0f, 4.0f, 5.0f)
        )
        val taskGraph = taskGraph(inputArray)
        val resultArray = TvmFloatArray(5)

        assertExecution(taskGraph, resultArray) {
        }
    }
}