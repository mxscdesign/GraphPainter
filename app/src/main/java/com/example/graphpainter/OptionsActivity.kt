package com.example.graphpainter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_options.*

class OptionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        btn_build.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("xMin", et_xMin.text.toString().toDouble())
            intent.putExtra("xMax", et_xMax.text.toString().toDouble())
            intent.putExtra("yMin", et_yMin.text.toString().toDouble())
            intent.putExtra("yMax", et_yMax.text.toString().toDouble())
            intent.putExtra("axes_color", rg_axis_color.checkedRadioButtonId)
            intent.putExtra("graph_color", rg_graph_color.checkedRadioButtonId)
            intent.putExtra("graph_size", rg_graph_width.checkedRadioButtonId)
            intent.putExtra("graph", rg_graph.checkedRadioButtonId)

            startActivity(intent)
        }
    }
}