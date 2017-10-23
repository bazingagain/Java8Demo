package com.leon.learnjava.part1.chapter3.functionInface;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created on 23/10/2017.
 *
 * @author Xiaolei-Peng
 */

@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader br) throws IOException;
}
