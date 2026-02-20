package com.kancharla.abhilash.fc_003

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class m1_58241 : Fragment() {

    private val viewModel: MissionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_m1_58241, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trustTextView = view.findViewById<TextView>(R.id.trustTextView)
        trustTextView.text = "Trust Level: ${viewModel.trustLevel}"

        view.findViewById<Button>(R.id.button6).setOnClickListener {
            findNavController().navigate(R.id.action_missionBrief_to_rooftop)
        }
    }
}