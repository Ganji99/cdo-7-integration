package com.calldorado.app

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.calldorado.sdk.ui.ui.aftercall.cards.native_field.CalldoradoCustomView
import com.calldoradosdk.integration.R
import com.calldoradosdk.integration.SecondActivity

//This class is to show the expanded native field on the AfterCall without any height restrictions
class MyExpandedCustomView(context: Context) : CalldoradoCustomView(
    context
) {

    override fun getRootView(): View {
        val constraintLayout =
            View.inflate(context, R.layout.myexpandedcustomview, null) as ConstraintLayout

        val clickMeExpanded = constraintLayout.findViewById<TextView>(R.id.expandedclick)
        val myExpandedCustomView = constraintLayout.findViewById<TextView>(R.id.expandedtextview)

        clickMeExpanded.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, SecondActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        })

        myExpandedCustomView.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                context,
                "This is a Expanded Custom view",
                Toast.LENGTH_SHORT
            ).show()
        })

        return constraintLayout
    }
}