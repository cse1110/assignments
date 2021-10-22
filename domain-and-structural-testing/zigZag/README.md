<!--NO_HARDWRAPS-->

The `zigzag` method receives a string `s` and a number of rows `numRows`, writes it down in a zigzag pattern. See the example below.

Suppose a string `"PAYPALISHIRING"`, and number of rows = 4. The zigzag would look like the following:

```
P  I  N
A LS IG
YA HR
P  I
```

Note the "PAYP" in the first vertical row (zig), then "A" and "L" going up (zag), then, another vertical row "ISHI" (zig), then "R" and "I" going up, and finally "NG" (zig).

![zigzag.jpg](resource-files/zigzag.jpg)


You have to:

* Use domain testing techniques to derive tests for this method. 
* Then, augment your test suite with structural testing. Note that Pitest identifies 27 mutants, but 2 are impossible to kill; therefore, we consider 25 killed mutants as 100%.

(Inspired by LeetCode's ZigZag conversion problem: https://leetcode.com/problems/zigzag-conversion/)
