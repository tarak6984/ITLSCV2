package com.example.itlscv2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.itlscv2.R;

public class MainActivity extends AppCompatActivity {

    // creating variables for our text view and button
    private TextView tvsec;
    private TextView tvMain;
    private Button bac, bc, bbrac1, bbrac2, bsin, bcos, btan, blog, bln, bfact, bsquare, bsqrt, binv;
    private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bpi, bmul, bminus, bplus, bequal, bdot, bdiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing all our variables.
        tvsec = findViewById(R.id.idTVSecondary);
        tvMain = findViewById(R.id.idTVprimary);
        bac = findViewById(R.id.bac);
        bc = findViewById(R.id.bc);
        bbrac1 = findViewById(R.id.bbrac1);
        bbrac2 = findViewById(R.id.bbrac2);
        bsin = findViewById(R.id.bsin);
        bcos = findViewById(R.id.bcos);
        btan = findViewById(R.id.btan);
        blog = findViewById(R.id.blog);
        bln = findViewById(R.id.bln);
        bfact = findViewById(R.id.bfact);
        bsquare = findViewById(R.id.bsquare);
        bsqrt = findViewById(R.id.bsqrt);
        binv = findViewById(R.id.binv);
        b0 = findViewById(R.id.b0);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        bpi = findViewById(R.id.bpi);
        bmul = findViewById(R.id.bmul);
        bminus = findViewById(R.id.bminus);
        bplus = findViewById(R.id.bplus);
        bequal = findViewById(R.id.bequal);
        bdot = findViewById(R.id.bdot);
        bdiv = findViewById(R.id.bdiv);

        // adding on click listener to our all buttons.
        b1.setOnClickListener(v -> appendToTextView("1"));
        b2.setOnClickListener(v -> appendToTextView("2"));
        b3.setOnClickListener(v -> appendToTextView("3"));
        b4.setOnClickListener(v -> appendToTextView("4"));
        b5.setOnClickListener(v -> appendToTextView("5"));
        b6.setOnClickListener(v -> appendToTextView("6"));
        b7.setOnClickListener(v -> appendToTextView("7"));
        b8.setOnClickListener(v -> appendToTextView("8"));
        b9.setOnClickListener(v -> appendToTextView("9"));
        b0.setOnClickListener(v -> appendToTextView("0"));
        bdot.setOnClickListener(v -> appendToTextView("."));
        bplus.setOnClickListener(v -> appendToTextView("+"));
        bdiv.setOnClickListener(v -> appendToTextView("/"));
        bbrac1.setOnClickListener(v -> appendToTextView("("));
        bbrac2.setOnClickListener(v -> appendToTextView(")"));
        bpi.setOnClickListener(v -> {
            appendToTextView("3.142");
            tvsec.setText(bpi.getText().toString());
        });
        bsin.setOnClickListener(v -> appendToTextView("sin"));
        bcos.setOnClickListener(v -> appendToTextView("cos"));
        btan.setOnClickListener(v -> appendToTextView("tan"));
        binv.setOnClickListener(v -> appendToTextView("^(-1)"));
        bln.setOnClickListener(v -> appendToTextView("ln"));
        blog.setOnClickListener(v -> appendToTextView("log"));

        bminus.setOnClickListener(v -> {
            String str = tvMain.getText().toString();
            if (str.length() > 0 && str.charAt(str.length() - 1) != '-') {
                appendToTextView("-");
            }
        });

        bmul.setOnClickListener(v -> {
            String str = tvMain.getText().toString();
            if (str.length() > 0 && str.charAt(str.length() - 1) != '*') {
                appendToTextView("*");
            }
        });

        bsqrt.setOnClickListener(v -> {
            if (tvMain.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter a valid number..", Toast.LENGTH_SHORT).show();
            } else {
                double r = Math.sqrt(Double.parseDouble(tvMain.getText().toString()));
                tvMain.setText(String.valueOf(r));
            }
        });

        bequal.setOnClickListener(v -> {
            String str = tvMain.getText().toString();
            double result = evaluate(str);
            tvMain.setText(String.valueOf(result));
            tvsec.setText(str);
        });

        bac.setOnClickListener(v -> {
            tvMain.setText("");
            tvsec.setText("");
        });

        bc.setOnClickListener(v -> {
            String str = tvMain.getText().toString();
            if (!str.isEmpty()) {
                str = str.substring(0, str.length() - 1);
                tvMain.setText(str);
            }
        });

        bsquare.setOnClickListener(v -> {
            if (tvMain.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter a valid number..", Toast.LENGTH_SHORT).show();
            } else {
                double d = Double.parseDouble(tvMain.getText().toString());
                double square = d * d;
                tvMain.setText(String.valueOf(square));
                tvsec.setText(d + "²");
            }
        });

        bfact.setOnClickListener(v -> {
            if (tvMain.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter a valid number..", Toast.LENGTH_SHORT).show();
            } else {
                int value = Integer.parseInt(tvMain.getText().toString());
                int fact = factorial(value);
                tvMain.setText(String.valueOf(fact));
                tvsec.setText(value + "!");
            }
        });
    }

    private void appendToTextView(String value) {
        tvMain.setText(tvMain.getText().toString() + value);
    }

    private int factorial(int n) {
        // this method is used to find factorial
        return (n == 1 || n == 0) ? 1 : n * factorial(n - 1);
    }

    private double evaluate(String str) {
        return new Object() {
            int pos = -1;
            int ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                while (true) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                while (true) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();
                double x;
                int startPos = pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if (ch >= '0' && ch <= '9' || ch == '.') {
                    while (ch >= '0' && ch <= '9' || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, pos);
                    x = parseFactor();
                    if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else if (func.equals("log")) x = Math.log10(x);
                    else if (func.equals("ln")) x = Math.log(x);
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                if (eat('^')) x = Math.pow(x, parseFactor());
                return x;
            }
        }.parse();
    }
}
