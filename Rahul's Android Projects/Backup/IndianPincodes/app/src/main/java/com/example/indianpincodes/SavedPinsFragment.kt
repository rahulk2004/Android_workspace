package com.example.indianpincodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.indianpincodes.databinding.FragmentSavedPinsBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class SavedPinsFragment : Fragment() {
    private lateinit var binding: FragmentSavedPinsBinding
    private lateinit var adapter: MyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSavedPinsBinding.inflate(inflater, container, false)


        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val databaseRef = FirebaseDatabase.getInstance().reference.child("favorites")
        val options = FirebaseRecyclerOptions.Builder<Model>()
            .setQuery(databaseRef, Model::class.java)
            .build()


        adapter = MyAdapter(options)
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()


//        if (adapter.itemCount == 0) {
//            Toast.makeText(requireContext(), "No saved pins found!", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}
