package com.example.scanner

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            val launchIntent =
                packageManager.getLaunchIntentForPackage("com.DefaultCompany.capstone2")
            startActivity(launchIntent)
        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.ikea.com/kr/ko/assembly_instructions/nordkisa-bedside-table-bamboo__AA-2161835-1-2.pdf")
            )
            startActivity(intent)


        }
    }
        fun startBarcodeReader(view: View) {
            IntentIntegrator(this).initiateScan()

        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                if (result.contents != null) {
                    Toast.makeText(
                        this,
                        "scanned: ${result.contents} format: ${result.formatName}",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                }


            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
