package com.tt.arvision

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode

class MainActivity : AppCompatActivity() {

    private lateinit var arModel: ArSceneView
    private lateinit var modelNode: ArModelNode

    private lateinit var fixObject: ExtendedFloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arModel = findViewById(R.id.arModel)
        fixObject = findViewById(R.id.fix_object)

        fixObject.setOnClickListener {
            fixObject()
        }

        modelNode = ArModelNode().apply {
            loadModelGlbAsync(
                glbFileLocation = "model/killua_zoldik.glb",
                onLoaded = {
                    arModel.planeRenderer.isVisible = true
                }
            )
            onAnchorChanged = {
                fixObject.isVisible = false
            }
        }
        arModel.addChild(modelNode)

    }

    private fun fixObject(){
        modelNode.anchor()
        arModel.planeRenderer.isVisible = false
    }
}