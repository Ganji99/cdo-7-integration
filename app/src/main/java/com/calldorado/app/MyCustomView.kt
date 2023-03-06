package com.calldorado.app

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.calldorado.sdk.ui.ui.aftercall.cards.native_field.CalldoradoCustomView
import com.calldoradosdk.integration.R
import com.calldoradosdk.integration.SecondActivity

//This class is to show the light native field on the WIC with a 55.dp height
class MyCustomView(context: Context) : CalldoradoCustomView(
    context
) {

    override fun getRootView(): View {
        val constraintLayout = View.inflate(context, R.layout.mycustomview, null) as ConstraintLayout
        val clickMe = constraintLayout.findViewById<Button>(R.id.click)
        clickMe.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, SecondActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        })
        return constraintLayout
    }
}