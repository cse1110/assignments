The `isPalindrome` method returns whether a given word is a palindrome. A word is a palindrome if the result of reading it from left to right is the same as that of reading it from right to left. The function is case-insensitive, so the string `"Aa"` is considered a palindrome.

Your task is to write property-based tests (usinq JQWik) for this `isPalindrome` method.


Some tips:

* Use the JQWik manual: https://jqwik.net/docs/current/user-guide.html
* The `equalsIgnoreCase` method (which is used in the `isPalindrome` implementation) can behave unexpectedly for letters outside the English alphabet. Feel free to use the `@AlphaChars` annotation to limit the generated strings to only use alphabetic characters.
