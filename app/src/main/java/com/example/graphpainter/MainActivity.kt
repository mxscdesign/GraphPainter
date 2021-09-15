package com.example.graphpainter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var graph_color = 0xff000000.toInt()
        var axes_color = 0xff000000.toInt()
        var graph_size = 10F
        var graph = 1

        val xMin = intent.getDoubleExtra("xMin", -10.0)
        val xMax = intent.getDoubleExtra("xMax", 10.0)
        val yMin = intent.getDoubleExtra("yMin", -10.0)
        val yMax = intent.getDoubleExtra("yMax", 10.0)

        when (intent.getIntExtra("graph_color", 0xff000000.toInt())) {
            R.id.rb_graph_black -> graph_color = 0xff000000.toInt()
            R.id.rb_graph_blue -> graph_color = 0xff0000ff.toInt()
            R.id.rb_graph_red -> graph_color = 0xffff0000.toInt()
        }

        when (intent.getIntExtra("axes_color", 0xff000000.toInt())) {
            R.id.rb_axis_red -> axes_color = 0xffff0000.toInt()
            R.id.rb_axis_blue -> axes_color = 0xff0000ff.toInt()
            R.id.rb_axis_black -> axes_color = 0xff000000.toInt()
        }

        when (intent.getIntExtra("graph_size", 10)) {
            R.id.rb_graph_thin -> graph_size = 5F
            R.id.rb_graph_medium -> graph_size = 10F
            R.id.rb_graph_wide -> graph_size = 15F
        }

        when (intent.getIntExtra("graph", 1)) {
            R.id.rb_graph2 -> graph = 2
        }

        cartesian_painter.init(
            xMin,
            xMax,
            yMin,
            yMax,
            graph_color,
            axes_color,
            graph_size,
            graph
        )
    }
}