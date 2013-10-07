// =============================================================================
//  Lexmark Confidential
//
//  $Id$
//
//  Copyright(c) 2012 Lexmark International, Inc.
//  All Rights Reserved.
// =============================================================================

package alg



/**
 *
 * http://stackoverflow.com/questions/11224457/closest-pair-sum-in-two-sorted-arrays
 *
 Given two sorted arrays of integers, a and b, and an integer c, I have to find i,j such that:
 a[i] + b[j] <= c
 and a[i] + b[j] is large as possible.
 The best solution I can think of is in O(nlogn) time, taking every integer from first array and finding the lower bound of "c-a[i]".
 Can anyone suggest me a better way to do this (maybe in O(n) time)?
 */

class ClosestPair {

    static Integer[] testArray = [
	1,
	2,
	4,
	6,
	-4,
	-8,
	3,
	1,
	-4
    ];
    static Integer[] sumArray =  [
	0,
	1,
	3,
	7,
	13,
	9,
	1,
	4,
	5,
	1
    ];

    static main(args) {



	int f1 = 1, f2 = 2, count =2;

	while (count++ < 10) {
	    f2 = f1 + f2; f1 = f2 - f1;
	    println "${count}- ${f2} "
	}

    }

}
