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

/**
 * A simple [Fragment] subclass.
 * Use the [m1_97109.newInstance] factory method to
 * create an instance of this fragment.
 */
class m1_97109 : Fragment() {

    private val viewModel: MissionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_m1_97109, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button5).setOnClickListener {
            viewModel.selectedStrategy = "SNIPER_SHOT"
            viewModel.trustLevel += 5
            val args = bundleOf("decisionKey" to "SNIPER_SHOT")
            findNavController().navigate(R.id.action_escalation_to_outcome, args)
        }

        view.findViewById<Button>(R.id.button4).setOnClickListener {
            viewModel.selectedStrategy = "REOPEN_COMMUNICATION"
            viewModel.trustLevel -= 15
            val args = bundleOf("decisionKey" to "REOPEN_COMMUNICATION")
            findNavController().navigate(R.id.action_escalation_to_outcome, args)
        }
    }
}