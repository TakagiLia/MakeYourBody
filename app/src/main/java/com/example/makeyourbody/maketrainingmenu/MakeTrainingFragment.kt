package com.example.makeyourbody.maketrainingmenu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.makeyourbody.databinding.FragmentMakeTrainingBinding

class MakeTrainingFragment : Fragment() {

    private var _binding: FragmentMakeTrainingBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("[TopPageFragment]", "onCreateView()")
        _binding = FragmentMakeTrainingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        Log.d("[TopPageFragment]", "onDestroyView()")
        super.onDestroyView()
        _binding = null
    }

}