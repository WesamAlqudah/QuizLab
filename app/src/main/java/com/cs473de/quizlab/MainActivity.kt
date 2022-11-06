package com.cs473de.quizlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(),AdapterQuestions.ItemListener {
    val questionsList=ArrayList<Question>()
    var score=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv_qeuestions=findViewById<RecyclerView>(R.id.rv_questions)
        val adapter=AdapterQuestions()
        adapter.setItemListener(this)
        adapter.setQuestions(questionsList)
        rv_qeuestions.adapter=adapter
    }

    init {
        getQuestions()
    }

    private fun getQuestions() {
        questionsList.add(Question(1,"Who developed Kotlin?","JetBrains",50, arrayListOf("IBM","Google","JetBrains","Microsoft")))
        questionsList.add(Question(2,"Which extension is responsible to save Kotlin files?",".kt or .kts",50, arrayListOf(".kot",".android",".kt or .kts")))

    }

    override fun onItemChosen(score: Int) {
        this.score+=score


    }

    fun submit(view: View) {
        if (score>=50)showCongratsDialog()
    }

    private fun showCongratsDialog() {

        val dialog=AlertDialog.Builder(this)
        dialog.setTitle("Congratulations!")
        dialog.setMessage("Score: $score")
        dialog.setCancelable(true)
        dialog.setPositiveButton("Ok") { _, _ ->
            resetAnswer()
        }
        dialog.create().show()
    }

    private lateinit var resetListener: ResetListener
    private fun resetAnswer() {
        resetListener.reset()
    }

    fun setResetListener(resetListener: ResetListener){
        this.resetListener=resetListener
    }

    fun reset(view: View) {

    }

    interface ResetListener{
        fun reset()
    }


}