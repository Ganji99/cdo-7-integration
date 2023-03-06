package com.calldorado.app;

import static android.view.View.inflate;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.calldorado.sdk.ui.ui.aftercall.cards.native_field.CalldoradoCustomView;
import com.calldoradosdk.integration.R;
import com.calldoradosdk.integration.SecondActivity;

//This class is to show the light native field on the WIC with a 55.dp height
public class MyCustomView extends CalldoradoCustomView {



    private Context context;
    TextView clickMe;
    ConstraintLayout constraintLayout;

    public MyCustomView(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @NonNull
    @Override
    public View getRootView() {
        constraintLayout = (ConstraintLayout) inflate(context, R.layout.mycustomview, null);
        clickMe = constraintLayout.findViewById(R.id.click);

        clickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return constraintLayout;
    }
}
