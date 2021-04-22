package com.example.todolist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.adapters.TaskListAdapter
import com.example.todolist.viewmodels.TaskListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    private val taskListViewModel: TaskListViewModel by viewModels()
    lateinit var rvTaskList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_task_list, container, false)

        rvTaskList = view.findViewById(R.id.rv_taskList)
        rvTaskList.layoutManager = LinearLayoutManager(view.context)

        taskListViewModel.tasks.observe(viewLifecycleOwner){
            rvTaskList.adapter = TaskListAdapter(it)
        }

        return view
    }


}