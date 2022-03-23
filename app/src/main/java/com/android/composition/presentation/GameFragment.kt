package com.android.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.composition.R
import com.android.composition.databinding.FragmentGameBinding
import com.android.composition.domain.entities.GameResult
import com.android.composition.domain.entities.GameSettings
import com.android.composition.domain.entities.Level

class GameFragment : Fragment() {
    private lateinit var level: Level
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvQuestion.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.main_container,
                    GameFinishedFragment.newInstance(
                        GameResult(
                            true,
                            20,
                            25,
                            GameSettings(
                                20,
                                20,
                                50,
                                30
                            )
                        )
                    )
                )
                .addToBackStack(null)
                .commit()
        }
    }

    private fun parseArguments() {
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val KEY_LEVEL = "level"
        const val NAME = "GameFragment"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments =
                    Bundle().apply {
                        putParcelable(KEY_LEVEL, level)
                    }
            }
        }
    }
}
