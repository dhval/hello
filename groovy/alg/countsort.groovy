array = [15,5,10,5,10,10,15,5,15,10,5]

def swapBucket(int a, int b) {
    if (a == b) return; 
    array[a] = array[a] + array[b]
    array[b] = array[a] - array[b]
    array[a] = array[a] - array[b]
}

def getBucketValue(int a) {
    return array[a];
}

def start = 0, end = array.size() - 1;

// Pass 1, move lowest order element (5) to their final position 
while (start < end) {
    // first element from left which is not 5
    for ( ; getBucketValue(start) == 5 && start < end ; start++);
    // first element from right which IS 5
    for ( ; getBucketValue(end) != 5 && start < end ; end--);
    
    if (start < end) swapBucket(start, end);
}     

// In second pass we can do 10,15

// We can extend this using recurion, for sorting domain = k, we need k-1 recurions 

for (key in array) { print " ${key} " }