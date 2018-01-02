package br.com.k2sistemas.smartrecycleadapterlib

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.com.k2sistemas.library.adapter.SmartRecyclerViewAdapter
import br.com.k2sistemas.smartrecycleadapterlib.model.Account
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mAccounts: ArrayList<Account>
    var mAdapter: SmartRecyclerViewAdapter<Account>? = null
    var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createAccoounts()
        configureRecycleView()
        setAdapter()
    }

    protected fun createAccoounts() {
        mAccounts = ArrayList<Account>()
        mAccounts.add(Account("Account01", "SubAccount01"))
        mAccounts.add(Account("Account02", "SubAccount02"))
        mAccounts.add(Account("Account03", "SubAccount03"))
        mAccounts.add(Account("Account04", "SubAccount04"))
        mAccounts.add(Account("Account05", "SubAccount05"))
        mAccounts.add(Account("Account06", "SubAccount06"))
        mAccounts.add(Account("Account07", "SubAccount07"))
        mAccounts.add(Account("Account08", "SubAccount08"))
    }

    protected fun configureRecycleView() {
        rvNames.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
        rvNames.layoutManager = mLayoutManager
    }

    protected fun setAdapter() {
        mAdapter = SmartRecyclerViewAdapter<Account>(this, mAccounts )
        rvNames.adapter = mAdapter
    }

}
