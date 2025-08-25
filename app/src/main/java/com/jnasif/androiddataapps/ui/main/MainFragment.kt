package com.jnasif.androiddataapps.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jnasif.androiddataapps.LOG_TAG
import com.jnasif.androiddataapps.R
import com.jnasif.androiddataapps.data.Monster
import com.jnasif.androiddataapps.databinding.FragmentMainBinding
import com.jnasif.androiddataapps.ui.shared.SharedViewModel

class MainFragment : Fragment(), MainRecyclerAdapter.MonsterItemListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: SharedViewModel
    private lateinit var binding : FragmentMainBinding
    private lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
        navController = findNavController()
        binding.swipeLayout.setOnRefreshListener {
            viewModel.refreshData()
        }
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.monsterData.observe(viewLifecycleOwner, Observer {
            val adapter = MainRecyclerAdapter(requireActivity(), it, this)
            binding.recyclerView.adapter = adapter
            binding.swipeLayout.isRefreshing = false
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onMonsterItemClick(monster: Monster) {
        Log.i(LOG_TAG, "Selected Monster: ${monster.name}")
        viewModel.selectedMonster.value = monster
        navController.navigate(R.id.action_nav_detail)
    }

}