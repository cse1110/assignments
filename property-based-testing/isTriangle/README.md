The `isTriangle` method returns whether a triangle is valid or not. Triangles are valid when no side is bigger than the sum of the other two sides. The implementation is quite straightforward. 

Your task is to write property-based tests (usinq JQWik) for this `isTriangle` method.

You need to add explanatory comments at the top of every test method, explaining what that test is all about.

Some tips:

* You can skip tests with negative numbers.
* Your solution should kill 10 mutants (one of them can't be killed)
* A good property-based test for this problem ensures 100% branch + condition coverage.
* You will have to generate sets of `(a,b,c)`, the sizes of the three sides of the triangle. We give you an inner class `ABC`, which is just a class that holds the three sides. You should make JQWik provide different `ABC`s.
* Have the JQWik manual at hand: https://jqwik.net/docs/current/user-guide.html (right-click and open in a new tab)

