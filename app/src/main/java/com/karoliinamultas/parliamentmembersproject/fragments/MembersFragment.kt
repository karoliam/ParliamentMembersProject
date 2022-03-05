package com.karoliinamultas.parliamentmembersproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.karoliinamultas.parliamentmembersproject.adapters.MemberRecyclerViewAdapter
import com.karoliinamultas.parliamentmembersproject.databinding.FragmentMembersOfPartyBinding
import com.karoliinamultas.parliamentmembersproject.viewModels.MembersViewModel


class MembersOfPartyFragment : Fragment() {
    private lateinit var memberViewModel: MembersViewModel
    private lateinit var adapter: MemberRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMembersOfPartyBinding>(
            inflater,
            R.layout.fragment_members_of_party, container, false
        )
        //Safe args
        val args = MembersOfPartyFragmentArgs.fromBundle(requireArguments())
        val partyName = args.partyName

        //ViewModel
        memberViewModel = ViewModelProvider(this).get(MembersViewModel::class.java)

        //Adding MP names to recyclerview
        memberViewModel.members.observe(viewLifecycleOwner, Observer {
        adapter = MemberRecyclerViewAdapter(requireContext(), memberViewModel.extractParties(it, partyName))
            binding.membersList.adapter = adapter
        })
        binding.membersList.layoutManager = LinearLayoutManager(activity)



        return binding.root
    }

}