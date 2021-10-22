We have a method that calculates the tax income, based on the received income.
You can view the code in the Library tab.
The income is taxed as follows:

* `0 <= Income < 22100` → **Tax = `0.15 * Income`**
* `22100 <= Income < 53500` → **Tax = `3315 + 0.28 * (Income - 22100)`**
* `53500 <= Income < 115000` → **Tax = `12107 + 0.31 * (Income - 53500)`**
* `115000 <= Income < 250000` → **Tax = `31172 + 0.36 * (Income - 115000)`**
* `250000 <= Income` → **Tax = `79772 + 0.396 * (Income - 250000)`**

Write property-based tests for this method.

(Exercise based on Kaner, C., Padmanabhan, S., & Hoffman, D. (2013). The Domain Testing Workbook. Context Driven Press)
