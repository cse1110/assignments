<!--NO_HARDWRAPS-->

For a given array of doubles, the `unique` method (in the Library tab) returns a new array, 
without all the duplicated values and sorted in descending order.

Your task is to write **ONE** property-based test (usinq JQWik) for this method. This property-based test should focus on the main behavior of the class. Given a random array of doubles, it ensures that the returned array has no duplicates and it is in descending order.

Some tips:

* No need for tests that try out nulls.
* Use the JQWik manual: https://jqwik.net/docs/current/user-guide.html
* AssertJ has a nice assertion to ensure the order of the elements. `assertThat(...).isSortedAccordingTo(...)`. 
Read the manual to understand how it works.
* You may need to convert a list of doubles (which JQWik generates easily) to an array of doubles (which is the input of the method). We give you the `convertListToArray` utility method for that.
