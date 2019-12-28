/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.section04functionalunittests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Daniel Bart
 */
public class RotateLeft {

    public int[] rotateLeft(int[] numbers) {

        int[] output = new int[numbers.length];

        for (int i = 0; i < numbers.length - 1; i++) {
            output[i] = numbers[i + 1];
        }
        
        output[numbers.length - 1] = numbers[0];
        
        return output;

    }

}
