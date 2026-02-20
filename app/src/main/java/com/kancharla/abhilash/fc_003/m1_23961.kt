package com.kancharla.abhilash.fc_003

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class m1_23961 : Fragment() {

    private val viewModel: MissionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_m1_23961, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trustTextView = view.findViewById<TextView>(R.id.trustTextView)
        trustTextView.text = "Trust Level: ${viewModel.trustLevel}"

        view.findViewById<Button>(R.id.button5).setOnClickListener {
            viewModel.selectedStrategy = "APPEAL_TO_EMOTION"
            viewModel.trustLevel += 15
            val args = bundleOf("decisionKey" to "APPEAL_TO_EMOTION")
            findNavController().navigate(R.id.action_openingDialogue_to_outcome, args)
        }

        view.findViewById<Button>(R.id.button3).setOnClickListener {
            viewModel.selectedStrategy = "LOGICAL_REASONING"
            viewModel.trustLevel -= 5
            val args = bundleOf("decisionKey" to "LOGICAL_REASONING")
            findNavController().navigate(R.id.action_openingDialogue_to_outcome, args)
        }
    }
}