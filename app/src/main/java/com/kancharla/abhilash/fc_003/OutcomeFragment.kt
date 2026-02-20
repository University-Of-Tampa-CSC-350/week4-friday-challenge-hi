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

class OutcomeFragment : Fragment() {

    private val viewModel: MissionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_outcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val strategy = viewModel.selectedStrategy
        val trust = viewModel.trustLevel
        val helicopterDismissed = viewModel.helicopterDismissed

        val trustTextView = view.findViewById<TextView>(R.id.trustTextView)
        trustTextView.text = "Trust Level: $trust"

        val titleView = view.findViewById<TextView>(R.id.textViewOutcomeTitle)
        val resultView = view.findViewById<TextView>(R.id.textViewOutcomeResult)

        val (title, result) = computeOutcome(strategy, trust, helicopterDismissed)
        titleView.text = title
        resultView.text = result

        view.findViewById<Button>(R.id.buttonReplay).setOnClickListener {
            findNavController().navigate(R.id.action_outcome_to_start)
        }
    }

    private fun computeOutcome(
        strategy: String?,
        trust: Int,
        helicopterDismissed: Boolean
    ): Pair<String, String> {
        return when {
            strategy == "APPEAL_TO_EMOTION" && trust > 15 -> Pair(
                "Emotional Breakthrough",
                "Connor appeals to Daniel's fear and confusion.\n\nDaniel begins to doubt his actions.\n\nAfter a tense pause, Daniel releases Emma and lowers his weapon.\nPolice move in and secure the scene.\n\nEmma is safe."
            )
            strategy == "LOGICAL_REASONING" -> Pair(
                "Miscalculated Approach",
                "Connor relies on logic and protocol.\n\nDaniel rejects the reasoning and becomes overwhelmed.\nIn a moment of panic, Daniel jumps from the rooftop, still holding Emma.\n\nEmma does not survive."
            )
            strategy == "CONTINUE_NEGOTIATION" && helicopterDismissed && trust > 0 -> Pair(
                "Negotiated Surrender",
                "With reduced pressure, Connor carefully continues the negotiation.\n\nDaniel slowly releases Emma and steps back from the edge.\nPolice secure Daniel without further violence.\n\nEmma is safe."
            )
            strategy == "MOVE_CLOSER" -> Pair(
                "Critical Mistake",
                "Connor moves closer, attempting to close the distance.\n\nDaniel panics at the sudden movement.\nIn the chaos, he loses his balance.\n\nBoth Daniel and Emma fall from the rooftop.\nEmma does not survive."
            )
            strategy == "SNIPER_SHOT" -> Pair(
                "Tactical Resolution",
                "Connor authorizes the sniper shot.\nDaniel is neutralized instantly.\nEmma is pulled to safety by responding officers.\nEmma survives, though shaken by the trauma."
            )
            strategy == "REOPEN_COMMUNICATION" -> Pair(
                "Irrecoverable Breakdown",
                "Connor attempts to reopen communication, but trust has collapsed.\nDaniel, overwhelmed and unstable, jumps from the rooftop while holding Emma.\nEmma does not survive."
            )
            trust > 15 -> Pair(
                "Mission Success",
                "Daniel surrenders. Emma survives."
            )
            else -> Pair(
                "Negotiation Collapses",
                "The negotiation collapses. The situation spirals out of control.\nEmma does not survive."
            )
        }
    }
}
