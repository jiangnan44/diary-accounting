package com.v.roomtest.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.v.roomtest.R
import com.v.roomtest.data.CunningDbManager
import com.v.roomtest.data.User

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    val TAG = "room"
    private lateinit var adapter: CunningAdapter

    private lateinit var etName: EditText
    private lateinit var etAge: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        etName = view.findViewById(R.id.et_name)
        etAge = view.findViewById(R.id.et_age)

        val rv: RecyclerView = view.findViewById(R.id.rv_users)
        rv.let {
            it.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            adapter = CunningAdapter(null)
        }


        initAdd(view)
        initDelete(view)
        initQuery(view)
        initQueryAll(view)
        initUpdate(view)

    }


    private fun initAdd(view: View) {
        //add user
        view.findViewById<Button>(R.id.btn_add_user).setOnClickListener {
            Log.d(TAG,"add user")
            val name = etName.text.toString().trim()
            if (name.isEmpty()) {
                Snackbar.make(it, "plz input name,at least", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val etAge = etAge.text.toString().trim()
            val age = if (etAge.isDigitsOnly()) {
                etAge.toInt()
            } else {
                (Math.random() * 100).toInt()
            }

            val user = User(name, age, 0, name.plus(age))
            CunningDbManager.addUser(user, it.context)
        }
    }


    private fun initUpdate(view: View) {
        //add user
        view.findViewById<Button>(R.id.btn_update_user).setOnClickListener {
            if (adapter.currentSelectedUser == null) {
                Snackbar.make(it, "plz select a user to update", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val name = etName.text.toString().trim()
            if (name.isEmpty()) {
                Snackbar.make(it, "plz input name,at least", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val etAge = etAge.text.toString().trim()
            val age = if (etAge.isDigitsOnly()) {
                etAge.toInt()
            } else {
                (Math.random() * 100).toInt()
            }

            val user = User(name, age, 0, name.plus(age))
            CunningDbManager.updateUser(it.context, user)
        }
    }

    private fun initQuery(view: View) {
        //add user
        view.findViewById<Button>(R.id.btn_query_user).setOnClickListener {

            val name = etName.text.toString().trim()
            if (name.isEmpty()) {
                Snackbar.make(it, "plz input query name", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = CunningDbManager.getUser(name, it.context)
            if (user == null) {
                Snackbar.make(it, "no such user with name:$name", Snackbar.LENGTH_SHORT).show()
            } else {
                adapter.updateData(listOf(user))
            }
        }
    }


    private fun initQueryAll(view: View) {
        //add user
        view.findViewById<Button>(R.id.btn_query_all).setOnClickListener {


            val users = CunningDbManager.getAllUser(it.context)
            if (users.isNullOrEmpty()) {
                Snackbar.make(it, "no users now!! @see Database Inspector", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                adapter.updateData(users)
            }
        }
    }

    private fun initDelete(view: View) {
        //add user
        view.findViewById<Button>(R.id.btn_del_user).setOnClickListener {

            if (adapter.currentSelectedUser == null) {
                Snackbar.make(it, "plz select a user to delete", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            CunningDbManager.deleteUser(adapter.currentSelectedUser!!.id, it.context)
            Snackbar.make(it, "deleted! click Query All", Snackbar.LENGTH_SHORT).show()
        }
    }
}

class CunningAdapter : RecyclerView.Adapter<CunningViewHolder> {
    private val users = ArrayList<User>()
    private var lastSelect: Int = 0
    var currentSelectedUser: User? = null

    constructor(users: List<User?>?) : super() {
        if (users.isNullOrEmpty()) return

        for (u in users) {
            u?.let {
                this.users.add(it)
            }
        }
    }

    fun updateData(users: List<User?>?) {
        if (users.isNullOrEmpty()) return
        this.users.clear()
        for (u in users) {
            u?.let {
                this.users.add(it)
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CunningViewHolder {
        return CunningViewHolder(getItemView(parent.context))
    }

    private fun getItemView(context: Context): View {
        return TextView(context).apply {
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            setTextColor(Color.parseColor("#666666"))
            setPadding(50)
        }
    }

    override fun onBindViewHolder(holder: CunningViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            currentSelectedUser = users[position]
            Snackbar.make(it, "select ${currentSelectedUser?.name}", Snackbar.LENGTH_SHORT).show()
            it.setBackgroundColor(Color.parseColor("#55555555"))
            lastSelect = position
        }

        if (position == lastSelect) {
            holder.itemView.background = null
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

}

class CunningViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)