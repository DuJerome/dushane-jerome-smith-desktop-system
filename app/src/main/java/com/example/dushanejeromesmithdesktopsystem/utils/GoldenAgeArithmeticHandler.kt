package com.example.dushanejeromesmithdesktopsystem.utils

import kotlin.math.abs

/**
 * A handler for Golden Age arithmetic, including a standard calculator
 * and a bit calculator for 2 and 3-qubit systems.
 */
object GoldenAgeArithmeticHandler {

    /**
     * A standard calculator for basic arithmetic operations using counting methods.
     */
    object Calculator {
        fun add(a: Int, b: Int): Int {
            var result = a
            if (b > 0) {
                for (i in 1..b) {
                    result++
                }
            } else if (b < 0) {
                for (i in 1..abs(b)) {
                    result--
                }
            }
            return result
        }

        fun subtract(a: Int, b: Int): Int {
            return add(a, -b)
        }

        fun multiply(a: Int, b: Int): Int {
            if (a == 0 || b == 0) return 0
            val result = (1..abs(b)).fold(0) { acc, _ -> add(acc, abs(a)) }
            return if ((a > 0 && b > 0) || (a < 0 && b < 0)) result else -result
        }

        fun power(base: Int, exponent: Int): Int {
            if (exponent < 0) return 0 // Or throw exception
            if (exponent == 0) return 1
            var result = 1
            for (i in 1..exponent) {
                result = multiply(result, base)
            }
            return result
        }

        fun divide(a: Int, b: Int): Int {
            if (b == 0) throw IllegalArgumentException("Division by zero is not allowed.")
            if (a == 0) return 0
            var tempA = abs(a)
            val tempB = abs(b)
            var quotient = 0
            while (tempA >= tempB) {
                tempA = subtract(tempA, tempB)
                quotient = add(quotient, 1)
            }
            return if ((a > 0 && b > 0) || (a < 0 && b < 0)) quotient else -quotient
        }
    }

    /**
     * A calculator for bitwise operations on 2 and 3-qubit systems.
     * The state of a qubit system is represented by an integer.
     */
    object QubitCalculator {

        // --- 2-Qubit Operations (4 states, 0 to 3) ---

        fun and2(stateA: Int, stateB: Int): Int {
            require(stateA in 0..3 && stateB in 0..3) { "2-qubit states must be between 0 and 3." }
            return stateA and stateB
        }

        fun or2(stateA: Int, stateB: Int): Int {
            require(stateA in 0..3 && stateB in 0..3) { "2-qubit states must be between 0 and 3." }
            return stateA or stateB
        }

        fun xor2(stateA: Int, stateB: Int): Int {
            require(stateA in 0..3 && stateB in 0..3) { "2-qubit states must be between 0 and 3." }
            return stateA xor stateB
        }

        fun not2(state: Int): Int {
            require(state in 0..3) { "2-qubit state must be between 0 and 3." }
            return state.inv() and 3
        }

        fun multiply2(stateA: Int, stateB: Int): Int {
            require(stateA in 0..3 && stateB in 0..3) { "2-qubit states must be between 0 and 3." }
            return (stateA * stateB) % 4
        }

        // --- 3-Qubit Operations (8 states, 0 to 7) ---

        fun and3(stateA: Int, stateB: Int): Int {
            require(stateA in 0..7 && stateB in 0..7) { "3-qubit states must be between 0 and 7." }
            return stateA and stateB
        }

        fun or3(stateA: Int, stateB: Int): Int {
            require(stateA in 0..7 && stateB in 0..7) { "3-qubit states must be between 0 and 7." }
            return stateA or stateB
        }

        fun xor3(stateA: Int, stateB: Int): Int {
            require(stateA in 0..7 && stateB in 0..7) { "3-qubit states must be between 0 and 7." }
            return stateA xor stateB
        }

        fun not3(state: Int): Int {
            require(state in 0..7) { "3-qubit state must be between 0 and 7." }
            return state.inv() and 7
        }

        fun multiply3(stateA: Int, stateB: Int): Int {
            require(stateA in 0..7 && stateB in 0..7) { "3-qubit states must be between 0 and 7." }
            return (stateA * stateB) % 8
        }
    }

    /**
     * A converter for changing number bases.
     */
    object BaseConverter {
        fun toBinaryString(n: Int): String {
            if (n == 0) return "0"
            var num = abs(n)
            val binary = StringBuilder()
            while (num > 0) {
                binary.append(num % 2)
                num /= 2
            }
            val result = binary.reverse().toString()
            return if (n < 0) "-$result" else result
        }

        fun toOctalString(n: Int): String {
            if (n == 0) return "0"
            var num = abs(n)
            val octal = StringBuilder()
            while (num > 0) {
                octal.append(num % 8)
                num /= 8
            }
            val result = octal.reverse().toString()
            return if (n < 0) "-$result" else result
        }

        fun toHexString(n: Int): String {
            if (n == 0) return "0"
            var num = abs(n)
            val hex = StringBuilder()
            val hexChars = "0123456789ABCDEF".toCharArray()
            while (num > 0) {
                hex.append(hexChars[num % 16])
                num /= 16
            }
            val result = hex.reverse().toString()
            return if (n < 0) "-$result" else result
        }
    }

    /**
     * Manages permissions for abilities.
     */
    object AbilityPermissions {
        private val allowedAbilities = mutableSetOf<String>()

        fun grantPermission(ability: String) {
            allowedAbilities.add(ability)
        }

        fun revokePermission(ability: String) {
            allowedAbilities.remove(ability)
        }

        fun isAbilityAllowed(ability: String): Boolean {
            return allowedAbilities.contains(ability)
        }
    }

    /**
     * Represents a cursor for navigating sequences with defined boundaries.
     */
    object Cursor {
        private var position: Int = 0
        private var sequenceSize: Int = 0

        /**
         * Sets the size of the sequence the cursor operates on.
         * Resets the cursor position to 0.
         * @param size The number of elements in the sequence.
         */
        fun setSequenceSize(size: Int) {
            sequenceSize = if (size > 0) size else 0
            reset()
        }

        fun getPosition(): Int = position

        /**
         * Moves the cursor forward by a number of steps, staying within the sequence bounds.
         */
        fun moveForward(steps: Int = 1) {
            if (sequenceSize == 0) return
            val newPosition = position + steps
            position = newPosition.coerceIn(0, sequenceSize - 1)
        }

        /**
         * Moves the cursor backward by a number of steps, staying within the sequence bounds.
         */
        fun moveBackward(steps: Int = 1) {
            if (sequenceSize == 0) return
            val newPosition = position - steps
            position = newPosition.coerceIn(0, sequenceSize - 1)
        }

        /**
         * Resets the cursor position to the beginning of the sequence (index 0).
         */
        fun reset() {
            position = 0
        }
    }
}
