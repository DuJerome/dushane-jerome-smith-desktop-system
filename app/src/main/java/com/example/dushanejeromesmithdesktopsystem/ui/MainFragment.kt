package com.example.dushanejeromesmithdesktopsystem.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dushanejeromesmithdesktopsystem.R

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var countTextView: TextView
    private lateinit var incrementButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        countTextView = view.findViewById(R.id.count_textview)
        incrementButton = view.findViewById(R.id.increment_button)

        viewModel.count.observe(viewLifecycleOwner) { count ->
            countTextView.text = count.toString()
        }

        incrementButton.setOnClickListener {
            viewModel.increment()
        }
    }
}
