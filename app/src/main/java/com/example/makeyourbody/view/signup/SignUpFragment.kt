package com.example.makeyourbody.view.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.makeyourbody.databinding.FragmentSignupBinding
import android.widget.ArrayAdapter
import com.example.makeyourbody.R

class SignUpFragment : Fragment(){

    private var _binding:  FragmentSignupBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Adapter作成
        val menuList = resources.getStringArray(R.array.signup_gender_spinner)
        val adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, menuList)

        // Adapter登録
        binding.autoCompleteTextview.setAdapter(adapter)
        binding.autoCompleteTextview.setText(menuList[0], false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}