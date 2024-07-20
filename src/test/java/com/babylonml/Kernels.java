package com.babylonml;

import uk.ac.manchester.tornado.api.TaskGraph;
import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.types.arrays.FloatArray;

public class Kernels {

  static void copyVectorKernel(FloatArray source, int sourceOffset,
      FloatArray destination, int destinationOffset, int length) {
    for (@Parallel int i = 0; i < length; i++) {
      destination.set(destinationOffset + i, source.get(sourceOffset + i));
    }
  }

  public static void addCopyVectorTask(TaskGraph graph, String name,
      FloatArray source, int sourceOffset,
      FloatArray destination, int destinationOffset, int length) {
    graph.task(name, Kernels::copyVectorKernel, source, sourceOffset,
        destination, destinationOffset, length);
  }
}
