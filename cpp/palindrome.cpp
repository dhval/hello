//
//  main.cpp
//  Palindrome
//
//  Created by Admin on 5/15/13.
//  Copyright (c) 2013 Admin. All rights reserved.
//

#include <iostream>


bool isPalindrome(int x, int &y) {
    if (x < 0) return false;
    if (x == 0) return true;
    
    if (isPalindrome(x/10, y) && (x%10 == y%10)) {
        y = y /10;
        return true;
    } else {
        return false;
    }
    
}

int main(int argc, const char * argv[])
{

    int x = 92789;
    bool ans = isPalindrome(x, x);
    
    std::cout << "Hello, World!\n"<<ans;
    return 0;
}

