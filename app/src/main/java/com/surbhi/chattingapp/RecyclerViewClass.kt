package com.surbhi.chattingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.surbhi.chattingapp.databinding.RightChatViewBinding

class RecyclerViewClass(var messageDataClass: ArrayList<MessageDataClass>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var viewType = 1
    class LeftViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        var tvLeftView = view.findViewById<TextView>(R.id.tvLeft)
    }
    class RightViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        var tvRightView = view.findViewById<TextView>(R.id.tvRight)
    }

    override fun getItemViewType(position: Int): Int {
        this.viewType = messageDataClass[position].type ?: 1
        return super.getItemViewType(position)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (this.viewType == 1)
        {
            return LeftViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.left_chat_view,parent,false))
        }
        else
        {
            return RightViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.right_chat_view,parent,false))
        }
    }

    override fun getItemCount(): Int {
        return messageDataClass.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder){
            is LeftViewHolder->{
                holder.tvLeftView.setText(messageDataClass[position].message)
            }
            is RightViewHolder->{
                holder.tvRightView.setText(messageDataClass[position].message)
            }
        }

    }
}