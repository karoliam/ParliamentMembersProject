package com.karoliinamultas.parliamentmembersproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.karoliinamultas.parliamentmembersproject.data.PoliticianViewModel
import com.karoliinamultas.parliamentmembersproject.databinding.FragmentMembersOfPartyBinding


class MembersOfPartyFragment : Fragment() {
    private lateinit var mPoliticianViewModel: PoliticianViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMembersOfPartyBinding>(
            inflater,
            R.layout.fragment_members_of_party, container, false
        )
        mPoliticianViewModel = ViewModelProvider(this).get(PoliticianViewModel::class.java)
        val adapter = MemberRecyclerViewAdapter()

        binding.membersList.adapter = adapter
        binding.membersList.layoutManager = LinearLayoutManager(requireContext())
        mPoliticianViewModel.politicians.observe(viewLifecycleOwner) {
            adapter.data = it
        }

        binding.membersList.apply {
            adapter.data
        }

        return binding.root
    }
}