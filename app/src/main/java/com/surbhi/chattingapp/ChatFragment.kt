package com.surbhi.chattingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.surbhi.chattingapp.databinding.FragmentChatBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var binding : FragmentChatBinding ?= null
    var recyclerView : RecyclerViewClass ?= null
    var mainActivity : MainActivity ?= null
    var layoutManager : LinearLayoutManager ?= null
    var messageList = ArrayList<MessageDataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentChatBinding.inflate(layoutInflater)
        recyclerView = RecyclerViewClass(messageList)
        binding?.btnSend?.setOnClickListener {
            AlertDialog.Builder(mainActivity?:requireContext()).setTitle("Message")
                .setMessage("Message")
                .setCancelable(false)
                .setPositiveButton("Left"){_,_->
                    messageList.add(MessageDataClass(message = binding?.etMessage?.text?.toString(),type =1))
                    binding?.etMessage?.text?.clear()
                    recyclerView?.notifyDataSetChanged()
                }
                .setNegativeButton("Right"){_,_->
                    messageList.add(MessageDataClass(message = binding?.etMessage?.text?.toString(),type =2))
                    binding?.etMessage?.text?.clear()
                    recyclerView?.notifyDataSetChanged()
                }
                .show()
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(mainActivity)
        binding?.rvChat?.layoutManager = layoutManager
        binding?.rvChat?.adapter = recyclerView

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChatFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}