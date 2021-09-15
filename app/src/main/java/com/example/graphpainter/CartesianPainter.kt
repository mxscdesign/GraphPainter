package com.example.graphpainter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.lang.Math.sqrt
import kotlin.math.ln
import kotlin.math.roundToInt
import kotlin.math.sin

class CartesianPainter(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    constructor(context: Context) : this(context, null)

    var xMin = -10.0
    var xMax = 10.0
    var yMin = -10.0
    var yMax = 10.0
    var graph = 1

    var backgroundPaint = Paint()
    val axesPaint = Paint()
    val graphPaint = Paint()

    fun init(
        xMin: Double,
        xMax: Double,
        yMin: Double,
        yMax: Double,
        graph_color: Int,
        axes_color: Int,
        graph_size: Float,
        graph: Int
    ) {

        this.xMin = xMin
        this.xMax = xMax
        this.yMin = yMin
        this.yMax = yMax

        backgroundPaint.color = 0xffffffd8.toInt()
        axesPaint.color = axes_color
        axesPaint.strokeWidth = 3F
        graphPaint.color = graph_color
        graphPaint.strokeWidth = graph_size

        this.graph = graph
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {
            drawPaint(backgroundPaint)
            val plane = CoordinatesConverter(width, height, xMin, xMax, yMin, yMax)
            val widthLine = plane.xCartToScreen(0.0)
            val heightLine = plane.yCartToScreen(0.0)
            drawLine(widthLine.toFloat(), 0F, widthLine.toFloat(), height.toFloat(), axesPaint)
            drawLine(0F, heightLine.toFloat(), width.toFloat(), heightLine.toFloat(), axesPaint)

            if (graph == 1)
                graph1(xMin, xMax, plane, canvas)
            else graph2(xMin, xMax, plane, canvas)
        }
    }

    private fun graph1(xMin: Double, xMax: Double, plane: CoordinatesConverter, canvas: Canvas) {
        val points: MutableList<Pair<Double, Double>> = mutableListOf()
        var x = xMin
        val h = 0.1
        while (x <= xMax) {
            val y = ln(x) - 5 * sin(x) * sin(x)
            if (!y.isNaN())
                points.add(Pair(x, y))
            x = ((x + h) * 10.0).roundToInt() / 10.0
        }

        for (i in 0 until points.size - 1) {
            val p1X = plane.xCartToScreen(points[i].first)
            val p1Y = plane.yCartToScreen(points[i].second)
            val p2X = plane.xCartToScreen(points[i + 1].first)
            val p2Y = plane.yCartToScreen(points[i + 1].second)
            canvas.drawLine(p1X.toFloat(), p1Y.toFloat(), p2X.toFloat(), p2Y.toFloat(), graphPaint)
        }
    }

    private fun graph2(xMin: Double, xMax: Double, plane: CoordinatesConverter, canvas: Canvas) {
        val points1: MutableList<Pair<Double, Double>> = mutableListOf()
        val points2: MutableList<Pair<Double, Double>> = mutableListOf()

        var x = xMin
        val h = 0.1
        while (x <= xMax) {
            val y = sqrt(9 - 9 * x * x / 4)
            val y1 = -sqrt(9 - 9 * x * x / 4)
            if (!y.isNaN())
                points1.add(Pair(x, y))
            if (!y1.isNaN())
                points2.add(Pair(x, y1))
            x = ((x + h) * 10.0).roundToInt() / 10.0
        }

        for (i in 0 until points1.size - 1) {
            val p1X = plane.xCartToScreen(points1[i].first)
            val p1Y = plane.yCartToScreen(points1[i].second)
            val p2X = plane.xCartToScreen(points1[i + 1].first)
            val p2Y = plane.yCartToScreen(points1[i + 1].second)
            canvas?.drawLine(p1X.toFloat(), p1Y.toFloat(), p2X.toFloat(), p2Y.toFloat(), graphPaint)
        }

        for (i in 0 until points2.size - 1) {
            val p1X = plane.xCartToScreen(points2[i].first)
            val p1Y = plane.yCartToScreen(points2[i].second)
            val p2X = plane.xCartToScreen(points2[i + 1].first)
            val p2Y = plane.yCartToScreen(points2[i + 1].second)
            canvas?.drawLine(p1X.toFloat(), p1Y.toFloat(), p2X.toFloat(), p2Y.toFloat(), graphPaint)
        }
    }
}

