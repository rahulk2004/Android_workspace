package com.example.indianpincodes.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.indianpincodes.MyAdapter
import com.example.indianpincodes.Model
import com.example.indianpincodes.R
import com.example.indianpincodes.databinding.FragmentQuickSearchBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class QuickSearchFragment : Fragment() {

    private lateinit var binding: FragmentQuickSearchBinding
    private var adapter: MyAdapter? = null
    private var searchField: String = "pincode"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentQuickSearchBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        setupAdapter()
        setupRadioButtons()

        binding.searchBox.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterData(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        return binding.root
    }

    private fun setupRadioButtons() {
        binding.searchTypeGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioPincode -> searchField = "pincode"
                R.id.radioDistrict -> searchField = "district"
                R.id.radioOffice -> searchField = "officename"
            }
            filterData(binding.searchBox.text.toString())
        }
    }

    private fun setupAdapter() {
        val options = FirebaseRecyclerOptions.Builder<Model>()
            .setQuery(FirebaseDatabase.getInstance().reference.child("pincodes"), Model::class.java)
            .build()

        adapter = MyAdapter(options)
        binding.recyclerView.adapter = adapter
        adapter?.startListening()
    }

    private fun filterData(query: String) {
        if (adapter != null) {
            adapter?.stopListening()
        }

        val searchQuery = FirebaseDatabase.getInstance().reference.child("pincodes")
            .orderByChild(searchField)
            .startAt(query)
            .endAt(query + "\uf8ff")

        val options = FirebaseRecyclerOptions.Builder<Model>()
            .setQuery(searchQuery, Model::class.java)
            .build()

        adapter = MyAdapter(options)
        binding.recyclerView.adapter = adapter
        adapter?.startListening()
    }

    override fun onStart() {
        super.onStart()
        adapter?.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter?.stopListening()
    }
}
