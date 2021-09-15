package com.example.graphpainter


class CoordinatesConverter(
    var width: Int,
    var height: Int,
    var xMin: Double,
    var xMax: Double,
    var yMin: Double,
    var yMax: Double
) {

    fun xCartToScreen(x: Double): Int {
        return (width.toDouble() / (xMax - xMin) * (x - xMin)).toInt()
    }

    fun yCartToScreen(y: Double): Int {
        return (height.toDouble() / (yMax - yMin) * (yMax - y)).toInt()
    }

    fun xScreenToCart(x: Int): Double {
        return x / width.toDouble() / (xMax - xMin) + xMin
    }

    fun yScreenToCart(y: Int): Double {
        return yMax - y / height.toDouble() / (yMax - yMin)
    }
}