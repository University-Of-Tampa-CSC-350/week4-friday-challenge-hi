package com.kancharla.abhilash.fc_003

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class m1_76542 : Fragment() {

    private val viewModel: MissionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_m1_76542, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button5).setOnClickListener {
            viewModel.selectedStrategy = "ESTABLISH_CONTACT"
            viewModel.trustLevel += 10
            val args = bundleOf("strategy" to "ESTABLISH_CONTACT")
            findNavController().navigate(R.id.action_rooftop_to_openingDialogue, args)
        }

        view.findViewById<Button>(R.id.button3).setOnClickListener {
            viewModel.selectedStrategy = "HELICOPTER_WITHDRAWAL"
            viewModel.helicopterDismissed = true
            viewModel.trustLevel += 5
            val args = bundleOf("strategy" to "HELICOPTER_WITHDRAWAL")
            findNavController().navigate(R.id.action_rooftop_to_tensionReduced, args)
        }

        view.findViewById<Button>(R.id.button4).setOnClickListener {
            viewModel.selectedStrategy = "REFUSE_NEGOTIATION"
            viewModel.trustLevel -= 10
            val args = bundleOf("strategy" to "REFUSE_NEGOTIATION")
            findNavController().navigate(R.id.action_rooftop_to_escalation, args)
        }
    }
}