package com.example.satya.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void quantityIncrement(View v) {
        if (quantity < 100)
            quantity += 1;
        else
            Toast.makeText(this, "Maximum Quantity reached !!", Toast.LENGTH_SHORT).show();
        displayQuantity();
    }

    public void quantityDecrement(View v) {
        if (quantity > 0)
            quantity -= 1;
        else
            Toast.makeText(this, "Minimum Quantity reached !!", Toast.LENGTH_SHORT).show();
        displayQuantity();
    }

    public void displayQuantity() {
        TextView quantity_display = (TextView) findViewById(R.id.quntity_textview);
        quantity_display.setText(String.valueOf(quantity));
    }

    public void displayOrderSummary(String orderSummaryText) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for Just Java");
        intent.putExtra(Intent.EXTRA_TEXT, orderSummaryText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void orderSummary(View v) {
        int basePrice = 0;

        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();

        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();

        if (hasWhippedCream) {
            basePrice += 1;
        }
        if (hasChocolate) {
            basePrice += 2;
        }
        
        basePrice = (basePrice + 5) * quantity;

        EditText entered_name = (EditText) findViewById(R.id.name);
        String name = entered_name.getText().toString();

        String orderSummaryText = "Name: " + name;
        orderSummaryText += "\nWhipped Cream Added ?: " + hasWhippedCream;
        orderSummaryText += "\nChocolate Added ?: " + hasChocolate;
        orderSummaryText += "\nQuantity: " + quantity;
        orderSummaryText += "\nPrice: ₹" + basePrice;
        orderSummaryText += "\nThank You !!";

        displayOrderSummary(orderSummaryText);

    }
}
