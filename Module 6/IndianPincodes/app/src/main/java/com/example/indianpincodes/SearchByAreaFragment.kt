package com.example.indianpincodes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.indianpincodes.MyAdapter
import com.example.indianpincodes.Model
import com.example.indianpincodes.databinding.FragmentSearchByAreaBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*

class SearchByAreaFragment : Fragment() {
    private lateinit var binding: FragmentSearchByAreaBinding
    private lateinit var adapter: MyAdapter
    private lateinit var databaseReference: DatabaseReference

    private var stateList = mutableListOf<String>()
    private var districtList = mutableListOf<String>()
    private var talukaList = mutableListOf<String>()

    private var selectedState: String = ""
    private var selectedDistrict: String = ""
    private var selectedTaluka: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchByAreaBinding.inflate(inflater, container, false)

        databaseReference = FirebaseDatabase.getInstance().reference.child("pincodes")

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadStates()

        binding.searchButton.setOnClickListener { filterData() }

        return binding.root
    }

    private fun loadStates() {
        databaseReference.orderByChild("state").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                stateList.clear()
                stateList.add("Select State")

                for (data in snapshot.children) {
                    val state = data.child("state").value.toString()
                    if (state.isNotEmpty() && !stateList.contains(state)) {
                        stateList.add(state)
                    }
                }

                val stateAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, stateList)
                binding.stateSpinner.adapter = stateAdapter
            }

            override fun onCancelled(error: DatabaseError) {}
        })

        binding.stateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedState = stateList[position]
                if (position > 0) loadDistricts(selectedState)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun loadDistricts(state: String) {
        databaseReference.orderByChild("state").equalTo(state).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                districtList.clear()
                districtList.add("Select District")

                for (data in snapshot.children) {
                    val district = data.child("district").value.toString()
                    if (district.isNotEmpty() && !districtList.contains(district)) {
                        districtList.add(district)
                    }
                }

                val districtAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, districtList)
                binding.districtSpinner.adapter = districtAdapter
            }

            override fun onCancelled(error: DatabaseError) {}
        })

        binding.districtSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedDistrict = districtList[position]
                if (position > 0) loadTalukas(selectedDistrict)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun loadTalukas(district: String) {
        databaseReference.orderByChild("district").equalTo(district).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                talukaList.clear()
                talukaList.add("Select Taluka")

                for (data in snapshot.children) {
                    val taluka = data.child("taluka").value.toString()
                    if (taluka.isNotEmpty() && !talukaList.contains(taluka)) {
                        talukaList.add(taluka)
                    }
                }

                val talukaAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, talukaList)
                binding.talukaSpinner.adapter = talukaAdapter
            }

            override fun onCancelled(error: DatabaseError) {}
        })

        binding.talukaSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedTaluka = talukaList[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun filterData() {
        if (selectedState == "Select State" || selectedDistrict == "Select District" || selectedTaluka == "Select Taluka") {
            return
        }

        val query = databaseReference.orderByChild("taluka").equalTo(selectedTaluka)

        val options = FirebaseRecyclerOptions.Builder<Model>()
            .setQuery(query, Model::class.java)
            .build()

        adapter = MyAdapter(options)
        binding.recyclerView.adapter = adapter
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}
