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
 An array contains both positive and negative elements, find the subarray whose sum equals 0. *
 Create an array A[i], such that A[i] = sum of Array[0 ... i]
 */

class FindSum {

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
