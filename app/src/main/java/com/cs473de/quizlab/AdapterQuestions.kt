package com.cs473de.quizlab


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterQuestions : RecyclerView.Adapter<AdapterQuestions.QuestionItemVM>() {
    private var questions_List: List<Question> = ArrayList<Question>()
    private lateinit var itemListener: ItemListener


    fun setItemListener(itemListener: ItemListener) {
        this.itemListener = itemListener
    }

    interface ItemListener {
        fun onItemChosen(score: Int)
    }


    inner class QuestionItemVM(private val view: View) : RecyclerView.ViewHolder(view),MainActivity.ResetListener {

        fun bindQuestion(question: Question) {
            val tv_question = view.findViewById<TextView>(R.id.tv_question)
            tv_question.text = question.ques
            val radio = view.findViewById<RadioGroup>(R.id.radio_group)
            question.choices?.forEach {
                val rb = RadioButton(view.context)
                rb.id = question.choices!!.indexOf(it)
                rb.text = it
                radio.addView(rb)
            }
            radio.setOnCheckedChangeListener { radioGroup, i ->
                question.chosenAnswer=question.choices!![i]
                val s=if(question.chosenAnswer==question.choices!![i])question.quesScore else 0
                itemListener.onItemChosen(s!!)

            }


        }

        override fun reset() {
            val radio = view.findViewById<RadioGroup>(R.id.radio_group)
            radio.clearCheck()
        }

    }

    fun setQuestions(list: List<Question>) {
        this.questions_List = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionItemVM {
        val inflater = LayoutInflater.from(parent.context)
        val questionItem = inflater.inflate(R.layout.item_question, parent, false)
        return QuestionItemVM(questionItem)
    }

    override fun onBindViewHolder(holder: QuestionItemVM, position: Int) {
        holder.bindQuestion(questions_List[position])

    }

    override fun getItemCount(): Int {
        return questions_List.size
    }
}