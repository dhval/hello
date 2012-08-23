package alg

class Fibonacci {

    def static fibonacci1(Integer n) {
	int f1 = 1, f2 = 2, count =2;

	while (count++ < n) {
	    f2 = f1 + f2; f1 = f2 - f1;
	    println "${count}- ${f2} "
	}
    }

    static main(args) {
	fibonacci1(10);
    }
}
