package com.example.androidweek4assignment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidweek4assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val switchStart = findViewById<Switch>(R.id.switchStart)

        switchStart.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "시작됨", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "중지됨", Toast.LENGTH_SHORT).show()
            }
        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkId ->
            when (checkId) {
                R.id.radioOreo -> {
                    binding.imageView.setImageResource(R.drawable.oreo)
                    binding.imageView.visibility = View.VISIBLE
                }

                R.id.radioPie -> {
                    binding.imageView.setImageResource(R.drawable.pie)
                    binding.imageView.visibility = View.VISIBLE
                }

                R.id.radioQ -> {
                    binding.imageView.setImageResource(R.drawable.q10)
                    binding.imageView.visibility = View.VISIBLE
                }

                else -> {
                    binding.imageView.setImageDrawable(null)
                    binding.imageView.visibility = View.GONE
                }
            }

            val btnEnd = findViewById<Button>(R.id.btnEnd)
            val btnRestart = findViewById<Button>(R.id.btnRestart)

            btnEnd.setOnClickListener {
                finishAffinity()
                System.exit(0)
            }

            btnRestart.setOnClickListener {
                // 현재 액티비티에서 MainActivity를 실행하는 Intent를 만듦
                val intent = Intent(this, MainActivity::class.java)
                // FLAG_ACTIVITY_CLEAR_TOP : 액티비티 스택 위에 MainActivity가 이미 존재할 경우, 모든 액티비티 제거 후 기존 MainActivity 재사용
                // FLAG_ACTIVITY_NEW_TASK : 새로운 작업 시작 (새로운 루트로 시작함)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
    }
}