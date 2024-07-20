package com.babylonml

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

class MemorySegmentUsageTest : AbstractTvmTest() {
    @Test
    fun memorySegmentUsageTest() {
        val memorySegment = MemorySegment.ofArray(floatArrayOf(1.0f, 2.0f, 3.0f, 4.0f, 5.0f))
        memorySegment.setAtIndex(ValueLayout.JAVA_FLOAT, 0, 10.0f)
        memorySegment.getAtIndex(ValueLayout.JAVA_FLOAT, 0)

        val arrayToCopy = TvmFloatArray.fromArray(
            floatArrayOf(1.0f, 2.0f, 3.0f, 4.0f, 5.0f)
        )
        val taskGraph = taskGraph(arrayToCopy)
        val resultArray = TvmFloatArray(5)

        Kernels.addCopyVectorTask(
            taskGraph, "copyVector", arrayToCopy, 0,
            resultArray, 0, 5
        )

        assertExecution(taskGraph, resultArray) {
            Assertions.assertArrayEquals(
                floatArrayOf(1.0f, 2.0f, 3.0f, 4.0f, 5.0f),
                resultArray.toHeapArray(), 0.001f
            )
        }
    }
}