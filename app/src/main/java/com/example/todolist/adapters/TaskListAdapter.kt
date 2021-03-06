package com.example.todolist.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.Status
import com.example.todolist.data.Task
import com.example.todolist.viewmodels.TaskListViewModel

class TaskListAdapter(
    val tasks: MutableList<Task>
) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvItemName: TextView = itemView.findViewById(R.id.tv_task_item_name)
        private val ivCheckIcon: ImageView = itemView.findViewById(R.id.iv_check_icon)

        fun bind(task: Task) {
            tvItemName.text = task.name
            ivCheckIcon.visibility = if (task.status == Status.DONE) View.VISIBLE else View.INVISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.task_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}