Here's a clean, professional `README.md` for your **Simple Expression Calculator** project, followed by a list of technologies used.

---

## 📐 Simple Expression Calculator

This is a command-line Java program that evaluates simple mathematical expressions entered by the user. It supports basic arithmetic operations (`+`, `-`, `*`, `/`, `%`, `^`) and respects the order of precedence and parentheses.

### 🧠 Features

* Accepts user input through the terminal
* Handles:

  * Addition (`+`)
  * Subtraction (`-`)
  * Multiplication (`*`)
  * Division (`/`)
  * Modulo (`%`)
  * Exponentiation (`^`)
* Supports nested parentheses
* Converts infix expressions to postfix (Reverse Polish Notation)
* Evaluates postfix expressions
* Provides helpful error messages for:

  * Division/modulo by zero
  * Invalid characters or malformed expressions
  * Mismatched parentheses

### 💻 How to Run

1. **Clone the repository**:

   ```bash
   git clone https://github.com/yourusername/SimpleExpressionCalculator.git
   cd SimpleExpressionCalculator
   ```

2. **Compile the program**:

   ```bash
   javac SimpleExpressionCalculator.java
   ```

3. **Run the program**:

   ```bash
   java SimpleExpressionCalculator
   ```

4. **Sample Input**:

   ```
   Enter a mathematical expression: (2 + 3) * 4 ^ 2
   ```

5. **Sample Output**:

   ```
   Result: 80.0
   ```


### ⚠️ Error Handling

* `Division by zero` → Throws `ArithmeticException`
* `Mismatched parentheses` or `Invalid syntax` → Throws `IllegalArgumentException`
* Invalid characters → Caught and reported clearly to the user

### 🔧 Technologies Used

* **Java** (JDK 8+)
* **Java Collections Framework**

  * `List`, `Stack`, `Map`
* **Scanner** for user input
* **Math** class for exponentiation (`Math.pow`)
* No external libraries or frameworks required

---
