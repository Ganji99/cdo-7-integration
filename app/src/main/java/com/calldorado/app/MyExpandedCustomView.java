package com.calldorado.app;

import static android.view.View.inflate;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.calldorado.sdk.ui.ui.aftercall.cards.native_field.CalldoradoCustomView;
import com.calldoradosdk.integration.R;
import com.calldoradosdk.integration.SecondActivity;

//This class is to show the expanded native field on the AfterCall without any height restrictions
public class MyExpandedCustomView extends CalldoradoCustomView {



    private Context context;
    TextView clickMeExpanded, myExpandedCustomView;
    ConstraintLayout constraintLayout;

    public MyExpandedCustomView(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @NonNull
    @Override
    public View getRootView() {
        constraintLayout = (ConstraintLayout) inflate(context, R.layout.myexpandedcustomview, null);

        clickMeExpanded = constraintLayout.findViewById(R.id.expandedclick);
        myExpandedCustomView = constraintLayout.findViewById(R.id.expandedtextview);

        clickMeExpanded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        myExpandedCustomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"This is a Expanded Custom view",Toast.LENGTH_SHORT).show();
            }
        });

        return constraintLayout;
    }
}
