package com.example.xml2screen

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Настройка отступов
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Находим все необходимые элементы
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val ageEditText = findViewById<EditText>(R.id.ageEditText)
        val continueButton = findViewById<Button>(R.id.continueButton)
        val backButton = findViewById<Button>(R.id.backButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        // Обработчик кнопки "Продолжить"
        continueButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val age = ageEditText.text.toString().trim()

            if (name.isNotEmpty() && age.isNotEmpty()) {
                // Скрываем элементы ввода
                nameEditText.visibility = EditText.GONE
                ageEditText.visibility = EditText.GONE
                continueButton.visibility = Button.GONE

                // Показываем результат и кнопку "Назад"
                resultTextView.text = "Добро пожаловать, $name. Ваш возраст: $age"
                resultTextView.visibility = TextView.VISIBLE
                backButton.visibility = Button.VISIBLE
            } else {
                Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        // Обработчик кнопки "Назад"
        backButton.setOnClickListener {
            // Возвращаем элементы ввода
            nameEditText.visibility = EditText.VISIBLE
            ageEditText.visibility = EditText.VISIBLE
            continueButton.visibility = Button.VISIBLE

            // Скрываем результат и кнопку "Назад"
            resultTextView.visibility = TextView.GONE
            backButton.visibility = Button.GONE

            // Очищаем поля ввода
            nameEditText.text.clear()
            ageEditText.text.clear()
        }
    }
}
