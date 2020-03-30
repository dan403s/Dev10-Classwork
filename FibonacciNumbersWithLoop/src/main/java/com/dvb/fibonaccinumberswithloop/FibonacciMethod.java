/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.fibonaccinumberswithloop;

/**
 *
 * @author Daniel Bart
 */
public class FibonacciMethod {

    public void fib(int n) {
        int n1 = 0;
        int n2 = 1;

        System.out.printf("Fibonacci Sequence of %d:", n);

        for (int i = 0; i <= n; i++) {
            System.out.printf(" %d ", n1);
            
            int sum = n1 + n2;
            n1 = n2;
            n2 = sum;
        }
        
        System.out.println("");
        
    }

}
