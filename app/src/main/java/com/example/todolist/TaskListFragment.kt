package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.adapters.TaskListAdapter
import com.example.todolist.viewmodels.TaskListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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

        taskListViewModel.getTasks()

        taskListViewModel.tasks?.observe(viewLifecycleOwner) {
            val adapter = TaskListAdapter(it)
            rvTaskList.adapter = adapter


            val touchHelperCallback: ItemTouchHelper.SimpleCallback =
                object : ItemTouchHelper.SimpleCallback(
                    0,
                    ItemTouchHelper.RIGHT
                ) {

                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        when (direction) {
                            ItemTouchHelper.RIGHT -> {
                                taskListViewModel.toggleTaskStatus(adapter.tasks[viewHolder.adapterPosition])
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }

            val itemTouchHelper = ItemTouchHelper(touchHelperCallback)
            itemTouchHelper.attachToRecyclerView(rvTaskList)
        }

        return view
    }


}