package app.pavel.calculator;

import java.util.*;
import java.math.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int situation;
    BigDecimal firstNumberBD = new BigDecimal("0.0");
    BigDecimal ResultBD = new BigDecimal("0.0");
    char arithmeticOperation = 0;
    boolean CommaMode = false;
    boolean arithmeticOperationMode = false;
    int NumberPostComma = 0;
    double PostCommaValue = 0.d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            firstNumberBD = new BigDecimal(savedInstanceState.getString("firstNumberBD"));
            ResultBD = new BigDecimal(savedInstanceState.getString("ResultBD"));
            char arithmeticOperation = savedInstanceState.getChar("arithmeticOperation");
            boolean arithmeticOperationMode = savedInstanceState.getBoolean("arithmeticOperationMode");
            transferMethod(firstNumberBD, ResultBD, arithmeticOperation, arithmeticOperationMode);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putChar("arithmeticOperation", arithmeticOperation);
        outState.putBoolean("arithmeticOperationMode", arithmeticOperationMode);
        outState.putString("firstNumberBD", firstNumberBD.toPlainString());
        outState.putString("ResultBD", ResultBD.toPlainString());
    }

    public void transferMethod(BigDecimal fNBD, BigDecimal RBD, char arithOperation, boolean aOM){

        firstNumberBD = new BigDecimal("0");
        ResultBD = new BigDecimal("0");

        TextView tv1 = (TextView) findViewById(R.id.textV1);
        if (fNBD.compareTo(BigDecimal.ZERO) == 0 &&
                RBD.compareTo(BigDecimal.ZERO) == 0) {
            situation = 0;
        } else if (fNBD.compareTo(BigDecimal.ZERO) != 0 &&
                RBD.compareTo(BigDecimal.ZERO) == 0) {
            situation = 1;
        } else if (fNBD.compareTo(BigDecimal.ZERO) == 0 &&
                RBD.compareTo(BigDecimal.ZERO) != 0 &&
                arithOperation != '0' ) {
            situation = 2;
        } else if (fNBD.compareTo(BigDecimal.ZERO) != 0 &&
                RBD.compareTo(BigDecimal.ZERO) != 0) {
            situation = 3;
        }  else situation = 4;

        switch (situation) {
            case 0:
                tv1.setText("");
                break;
            case 1:
                tv1.setText(fNBD.toPlainString());
                firstNumberBD = firstNumberBD.add(fNBD);
                break;
            case 2:
                switch (arithOperation) {
                    case '+' :
                        arithOperation = '+';
                        Button buttonSummarize = (Button) findViewById(R.id.buttonSummarize);
                        buttonSummarize.setBackgroundColor(ContextCompat.getColor(this,
                                R.color.colorAmberDark));
                        break;
                    case '-' :
                        arithOperation = '-';
                        Button buttonSubtract = (Button) findViewById(R.id.buttonSubtract);
                        buttonSubtract.setBackgroundColor(ContextCompat.getColor(this,
                                R.color.colorAmberDark));
                        break;
                    case '*' :
                        arithOperation = '*';
                        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
                        buttonMultiply.setBackgroundColor(ContextCompat.getColor(this,
                                R.color.colorAmberDark));
                        break;
                    case '/' :
                        arithOperation = '/';
                        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
                        buttonDivide.setBackgroundColor(ContextCompat.getColor(this,
                                R.color.colorAmberDark));
                        break;
                    default:
                        break;
                }
                arithmeticOperation = arithOperation;
                arithmeticOperationMode = aOM;
                ResultBD = ResultBD.add(RBD);
                tv1.setText(RBD.toPlainString());

                break;
            case 3:
                switch (arithOperation) {
                    case '+':
                        arithOperation = '+';
                        Button buttonSummarize = (Button) findViewById(R.id.buttonSummarize);
                        buttonSummarize.setBackgroundColor(ContextCompat.getColor(this,
                                R.color.colorAmberDark));
                        break;
                    case '-':
                        arithOperation = '-';
                        Button buttonSubtract = (Button) findViewById(R.id.buttonSubtract);
                        buttonSubtract.setBackgroundColor(ContextCompat.getColor(this,
                                R.color.colorAmberDark));
                        break;
                    case '*':
                        arithOperation = '*';
                        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
                        buttonMultiply.setBackgroundColor(ContextCompat.getColor(this,
                                R.color.colorAmberDark));
                        break;
                    case '/':
                        arithOperation = '/';
                        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
                        buttonDivide.setBackgroundColor(ContextCompat.getColor(this,
                                R.color.colorAmberDark));
                        break;
                    default:
                        break;
                }
                firstNumberBD = firstNumberBD.add(fNBD);
                ResultBD = ResultBD.add(RBD);
                arithmeticOperation = arithOperation;
                arithmeticOperationMode = aOM;
                tv1.setText(firstNumberBD.toPlainString());
                break;
            default:
                break;
        }
    }

    public void onClickOneDivideX(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        BigDecimal One = new BigDecimal("1");
        if (firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                ResultBD.compareTo(BigDecimal.ZERO) == 0) {
            One = One.divide(firstNumberBD, 50,  BigDecimal.ROUND_HALF_UP);
            int b = CountingMethod(One);
            One = One.setScale(b - 1, BigDecimal.ROUND_HALF_DOWN);
            firstNumberBD = new BigDecimal("0");
            firstNumberBD = firstNumberBD.add(One);
            if (firstNumberBD.compareTo(new BigDecimal("1")) == -1) {
                // if firstNumberBD less than 1
                String s1 = firstNumberBD.toPlainString();
                tv1.setText(s1);
            } if (firstNumberBD.compareTo(new BigDecimal("1")) == 1) {
                // if firstNumberBD greater than 1
                int c = CountingMethod(firstNumberBD);
                firstNumberBD = firstNumberBD.setScale(c - 1, BigDecimal.ROUND_HALF_DOWN);
                if (c >= 25) {
                    firstNumberBD = firstNumberBD.setScale(25, BigDecimal.ROUND_HALF_DOWN);
                    int cc = CountingMethod(firstNumberBD);
                    firstNumberBD = firstNumberBD.setScale(cc - 1, BigDecimal.ROUND_HALF_DOWN);
                }
                String s1 = firstNumberBD.toPlainString();
                tv1.setText(s1);
            } if (firstNumberBD.compareTo(new BigDecimal("1")) == 0) {
                // if firstNumberBD equal to 1
                String s1 = firstNumberBD.toPlainString();
                tv1.setText(s1);
            }
        }
    }

    public void onClickX2(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        BigDecimal X2 = new BigDecimal("0");
        if (firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                ResultBD.compareTo(BigDecimal.ZERO) == 0) {
            X2 = X2.add(firstNumberBD.multiply(firstNumberBD));
            int c = CountingMethod(X2);
            X2 = X2.setScale(c - 1,  BigDecimal.ROUND_HALF_DOWN);
            tv1.setText(X2.toPlainString());
            firstNumberBD = new BigDecimal("0");
            firstNumberBD = firstNumberBD.add(X2);
        }
    }

    public void onClickX3(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        BigDecimal X3 = new BigDecimal("0");
        if (firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                ResultBD.compareTo(BigDecimal.ZERO) == 0) {
            X3 = X3.add(firstNumberBD.multiply(firstNumberBD.multiply(firstNumberBD)));
            int c = CountingMethod(X3);
            X3 = X3.setScale(c - 1,  BigDecimal.ROUND_HALF_DOWN);
            tv1.setText(X3.toPlainString());
            firstNumberBD = new BigDecimal("0");
            firstNumberBD = firstNumberBD.add(X3);
        }
    }

    public void onClickFactorial(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        if (firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                ResultBD.compareTo(BigDecimal.ZERO) == 0 &&
                firstNumberBD.compareTo(new BigDecimal("1500")) != 1) {
            int c = CountingMethod(firstNumberBD);
            BigDecimal Fact = new BigDecimal("1");
            if (c > 1) {
                Toast.makeText(getApplicationContext(),
                        " Invalid input ", Toast.LENGTH_SHORT).show();
            }
            if (c == 1) {
                for (int i = 1; i <= firstNumberBD.longValue(); i ++) {
                    Fact = Fact.multiply(new BigDecimal(i));
                }
                int e = CountingMethod(Fact);
                Fact = Fact.setScale(e - 1,  BigDecimal.ROUND_HALF_DOWN);
                firstNumberBD = new BigDecimal("0");
                firstNumberBD = firstNumberBD.add(Fact);
                tv1.setText(firstNumberBD.toPlainString());
            }
        } else Toast.makeText(getApplicationContext(),
                " Range exceeded ", Toast.LENGTH_SHORT).show();
    }

    public void onClickButtonPiANDe(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        if (firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                ResultBD.compareTo(BigDecimal.ZERO) == 0 ||
                firstNumberBD.compareTo(BigDecimal.ZERO) == 0 &&
                        ResultBD.compareTo(BigDecimal.ZERO) == 0 ||
                firstNumberBD.compareTo(BigDecimal.ZERO) == 0 &&
                        ResultBD.compareTo(BigDecimal.ZERO) != 0) {
            switch (v.getId()) {
                case (R.id.buttonE) :
                    firstNumberBD = new BigDecimal("0");
                    firstNumberBD = firstNumberBD.add(new BigDecimal(Math.E));
                    break;
                case (R.id.buttonPi) :
                    firstNumberBD = new BigDecimal("0");
                    firstNumberBD = firstNumberBD.add(new BigDecimal(Math.PI));
                    break;
                default:
                    break;
            }
            firstNumberBD = firstNumberBD.setScale(25, BigDecimal.ROUND_HALF_DOWN);
            tv1.setText(firstNumberBD.toPlainString());
        }
    }

    public void onClickLogarithmE(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);

        if (ResultBD.compareTo(BigDecimal.ZERO) == 0) {
            if (firstNumberBD.compareTo(BigDecimal.ZERO) == 1 &&
                    firstNumberBD.compareTo(new BigDecimal("1")) != 0) {
                double bd = Double.parseDouble(firstNumberBD.toPlainString());
                bd = Math.log(bd);
                firstNumberBD = new BigDecimal(bd);
                int b = CountingMethod(firstNumberBD);
                if (b > 15) b = 15;
                firstNumberBD = firstNumberBD.setScale(b, BigDecimal.ROUND_HALF_DOWN);
                tv1.setText(firstNumberBD.toPlainString());
            }
            if (firstNumberBD.compareTo(new BigDecimal("1")) == 0) {
                firstNumberBD = new BigDecimal("0");
                tv1.setText(firstNumberBD.toPlainString());
            }
            if (firstNumberBD.compareTo(BigDecimal.ZERO) == -1 ||
                    firstNumberBD.compareTo(BigDecimal.ZERO) == 0) {
                Toast.makeText(getApplicationContext(), " Invalid input ",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onClickLg(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        if (firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                ResultBD.compareTo(BigDecimal.ZERO) == 0) {
            BigDecimal b = new BigDecimal(firstNumberBD.toPlainString());
            final int NUM_OF_DIGITS = 25;

            MathContext mc = new MathContext(NUM_OF_DIGITS, RoundingMode.HALF_EVEN);

            if (b.compareTo(BigDecimal.ZERO) == -1) {
                Toast.makeText(getApplicationContext(), " Invalid input ",
                        Toast.LENGTH_SHORT).show();
            }
            if (b.equals(BigDecimal.ZERO)) {
                firstNumberBD = new BigDecimal("0");
                firstNumberBD = firstNumberBD.add(BigDecimal.ZERO);
                int q = CountingMethod(firstNumberBD);
                firstNumberBD = firstNumberBD.setScale(q, BigDecimal.ROUND_HALF_DOWN);
                tv1.setText(firstNumberBD.toPlainString());
                return;
            }
            if (b.compareTo(BigDecimal.ZERO) == 1) {

                StringBuffer sb = new StringBuffer();
                int leftDigits = b.precision() - b.scale();
                sb.append(leftDigits - 1).append(".");

                int n = 0;
                while (n < NUM_OF_DIGITS) {
                    b = (b.movePointLeft(leftDigits - 1)).pow(10, mc);
                    leftDigits = b.precision() - b.scale();
                    sb.append(leftDigits - 1);
                    n++;
                }

                BigDecimal ret = new BigDecimal(sb.toString());
                ret = ret.round(new MathContext(ret.precision() - ret.scale() + NUM_OF_DIGITS,
                        RoundingMode.HALF_EVEN));
                firstNumberBD = new BigDecimal("0");
                firstNumberBD = firstNumberBD.add(ret);
                int a = CountingMethod(firstNumberBD);
                if (a > 25) a = 25;
                firstNumberBD = firstNumberBD.setScale(a, BigDecimal.ROUND_HALF_DOWN);
                tv1.setText(firstNumberBD.toPlainString());
            }
        }
    }

    public void onClickRand(View v){
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        if (ResultBD.compareTo(BigDecimal.ZERO) == 0) {
            Random rand = new Random();
            BigDecimal RND = new BigDecimal(rand.nextDouble());
            firstNumberBD = new BigDecimal("0");
            firstNumberBD = firstNumberBD.add(RND);
            int a = CountingMethod(firstNumberBD);
            if (a > 25) a = 25;
            firstNumberBD = firstNumberBD.setScale(a, BigDecimal.ROUND_HALF_DOWN);
            tv1.setText(firstNumberBD.toPlainString());
        }
    }

    public void onClickSqrt2(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        if (firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                ResultBD.compareTo(BigDecimal.ZERO) == 0) {
            int scale = 2;
            BigDecimal sqrt = new BigDecimal(1);
            sqrt.setScale(scale + 3, RoundingMode.FLOOR);
            BigDecimal store = new BigDecimal(firstNumberBD.toString());
            boolean first = true;
            do {
                if (!first)
                    store = new BigDecimal(sqrt.toString());
                else first = false;
                store.setScale(scale + 3, RoundingMode.FLOOR);
                sqrt = firstNumberBD.divide(store, 30, RoundingMode.FLOOR).add(store).divide(
                        BigDecimal.valueOf(2), 30, RoundingMode.FLOOR);
            } while (!store.equals(sqrt));
            firstNumberBD = new BigDecimal("0");
            firstNumberBD = firstNumberBD.add(sqrt);
            int b = CountingMethod(firstNumberBD);
            if (b > 50) b = 50;
            BigDecimal scale25 = new BigDecimal("0");
            scale25 = scale25.add(firstNumberBD.setScale(25, BigDecimal.ROUND_HALF_DOWN));
            if (scale25.compareTo(BigDecimal.ZERO) == 0) {
                firstNumberBD = firstNumberBD.setScale(b, BigDecimal.ROUND_HALF_DOWN);
            } else firstNumberBD = firstNumberBD.setScale(25, BigDecimal.ROUND_HALF_DOWN);
            tv1.setText(firstNumberBD.toPlainString());
        }
    }

    public void onClickSqrt3(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        if (firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                ResultBD.compareTo(BigDecimal.ZERO) == 0) {
            double firstDouble = Math.pow(firstNumberBD.doubleValue(), 1.0/3);
            firstNumberBD = new BigDecimal(firstDouble);
            int b = CountingMethod(firstNumberBD);
            if (b > 50) b = 50;
            BigDecimal scale30 = new BigDecimal("0");
            scale30 = scale30.add(firstNumberBD.setScale(25, BigDecimal.ROUND_HALF_DOWN));
            if (scale30.compareTo(BigDecimal.ZERO) == 0) {
                firstNumberBD = firstNumberBD.setScale(b, BigDecimal.ROUND_HALF_DOWN);
            } else firstNumberBD = firstNumberBD.setScale(25, BigDecimal.ROUND_HALF_DOWN);
            tv1.setText(firstNumberBD.toPlainString());
        }
    }

    public void onClick10X(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        if (firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                ResultBD.compareTo(BigDecimal.ZERO) == 0) {
            BigDecimal count = new BigDecimal("1");
            int c = CountingMethod(firstNumberBD);
            if (c == 1 && firstNumberBD.compareTo(new BigDecimal("501")) == -1 ) {
                for (int i = 1; i <= firstNumberBD.longValue(); i++) {
                    count = count.multiply(new BigDecimal("10"));
                }
                firstNumberBD = new BigDecimal("0");
                firstNumberBD = firstNumberBD.add(count);
            } else if (firstNumberBD.compareTo(new BigDecimal("201")) == -1 || c > 1) {
                double ten = 10;
                double result = Math.pow(ten, firstNumberBD.doubleValue());
                firstNumberBD = new BigDecimal(result);
                int b = CountingMethod(firstNumberBD);
                if (b > 11) b = 11;
                firstNumberBD = firstNumberBD.setScale(b, BigDecimal.ROUND_HALF_DOWN);
            } else Toast.makeText(getApplicationContext(),
                    " Range exceeded ", Toast.LENGTH_SHORT).show();
            if (firstNumberBD.compareTo(new BigDecimal("1000000000000000")) == 1) {
                tv1.setText(firstNumberBD.toString());
            } else tv1.setText(firstNumberBD.toPlainString());
        }
    }

    public void onClickNumber(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        if (ResultBD.compareTo(BigDecimal.ZERO) != 0 &&
                firstNumberBD.compareTo(BigDecimal.ZERO) == 0) {
            tv1.setText("");
        }
        switch(v.getId()) {
            case(R.id.button1):
                if (CommaMode == true && NumberPostComma >= 0) {
                    BigDecimal PostCommaValue = new BigDecimal("1");
                    BigDecimal Ten = new BigDecimal("10");
                    for (int i = 0; i <= NumberPostComma; i++) {
                        if (i != NumberPostComma) {
                            PostCommaValue = PostCommaValue.divide(Ten, NumberPostComma,
                                    BigDecimal.ROUND_HALF_UP);
                        }
                    }
                    PostCommaValue.setScale(NumberPostComma + 1, BigDecimal.ROUND_HALF_UP);
                    NumberPostComma += 1;
                    firstNumberBD = firstNumberBD.add(PostCommaValue);
                    String s1 = firstNumberBD.toString();
                    tv1.setText(s1);
                }
                if (CommaMode == false) {
                    tv1.append("1");
                    String s1 = tv1.getText().toString();
                    firstNumberBD = new BigDecimal(s1);
                }
                break;
            case(R.id.button2):
                if (CommaMode == true && NumberPostComma >= 0) {
                    BigDecimal PostCommaValue = new BigDecimal("2");
                    BigDecimal Ten = new BigDecimal("10");
                    for (int i = 0; i <= NumberPostComma; i++) {
                        if (i != NumberPostComma) {
                            PostCommaValue = PostCommaValue.divide(Ten, NumberPostComma,
                                    BigDecimal.ROUND_HALF_UP);
                        }
                    }
                    PostCommaValue.setScale(NumberPostComma + 1, BigDecimal.ROUND_HALF_UP);
                    NumberPostComma += 1;
                    firstNumberBD = firstNumberBD.add(PostCommaValue);
                    String s1 = firstNumberBD.toString();
                    tv1.setText(s1);
                }
                if (CommaMode == false) {
                    tv1.append("2");
                    String s1 = tv1.getText().toString();
                    firstNumberBD = new BigDecimal(s1);
                }
                break;
            case(R.id.button3):
                if (CommaMode == true && NumberPostComma >= 0) {
                    BigDecimal PostCommaValue = new BigDecimal("3");
                    BigDecimal Ten = new BigDecimal("10");
                    for (int i = 0; i <= NumberPostComma; i++) {
                        if (i != NumberPostComma) {
                            PostCommaValue = PostCommaValue.divide(Ten, NumberPostComma,
                                    BigDecimal.ROUND_HALF_UP);
                        }
                    }
                    PostCommaValue.setScale(NumberPostComma + 1, BigDecimal.ROUND_HALF_UP);
                    NumberPostComma += 1;
                    firstNumberBD = firstNumberBD.add(PostCommaValue);
                    String s1 = firstNumberBD.toString();
                    tv1.setText(s1);
                }
                if (CommaMode == false) {
                    tv1.append("3");
                    String s1 = tv1.getText().toString();
                    firstNumberBD = new BigDecimal(s1);
                }
                break;
            case(R.id.button4):
                if (CommaMode == true && NumberPostComma >= 0) {
                    BigDecimal PostCommaValue = new BigDecimal("4");
                    BigDecimal Ten = new BigDecimal("10");
                    for (int i = 0; i <= NumberPostComma; i++) {
                        if (i != NumberPostComma) {
                            PostCommaValue = PostCommaValue.divide(Ten, NumberPostComma,
                                    BigDecimal.ROUND_HALF_UP);
                        }
                    }
                    PostCommaValue.setScale(NumberPostComma + 1, BigDecimal.ROUND_HALF_UP);
                    NumberPostComma += 1;
                    firstNumberBD = firstNumberBD.add(PostCommaValue);
                    String s1 = firstNumberBD.toString();
                    tv1.setText(s1);
                }
                if (CommaMode == false) {
                    tv1.append("4");
                    String s1 = tv1.getText().toString();
                    firstNumberBD = new BigDecimal(s1);
                }
                break;
            case(R.id.button5):
                if (CommaMode == true && NumberPostComma >= 0) {
                    BigDecimal PostCommaValue = new BigDecimal("5");
                    BigDecimal Ten = new BigDecimal("10");
                    for (int i = 0; i <= NumberPostComma; i++) {
                        if (i != NumberPostComma) {
                            PostCommaValue = PostCommaValue.divide(Ten, NumberPostComma,
                                    BigDecimal.ROUND_HALF_UP);
                        }
                    }
                    PostCommaValue.setScale(NumberPostComma + 1, BigDecimal.ROUND_HALF_UP);
                    NumberPostComma += 1;
                    firstNumberBD = firstNumberBD.add(PostCommaValue);
                    String s1 = firstNumberBD.toString();
                    tv1.setText(s1);
                }
                if (CommaMode == false) {
                    tv1.append("5");
                    String s1 = tv1.getText().toString();
                    firstNumberBD = new BigDecimal(s1);
                }
                break;
            case(R.id.button6):
                if (CommaMode == true && NumberPostComma >= 0) {
                    BigDecimal PostCommaValue = new BigDecimal("6");
                    BigDecimal Ten = new BigDecimal("10");
                    for (int i = 0; i <= NumberPostComma; i++) {
                        if (i != NumberPostComma) {
                            PostCommaValue = PostCommaValue.divide(Ten, NumberPostComma,
                                    BigDecimal.ROUND_HALF_UP);
                        }
                    }
                    PostCommaValue.setScale(NumberPostComma + 1, BigDecimal.ROUND_HALF_UP);
                    NumberPostComma += 1;
                    firstNumberBD = firstNumberBD.add(PostCommaValue);
                    String s1 = firstNumberBD.toString();
                    tv1.setText(s1);
                }
                if (CommaMode == false) {
                    tv1.append("6");
                    String s1 = tv1.getText().toString();
                    firstNumberBD = new BigDecimal(s1);
                }
                break;
            case(R.id.button7):
                if (CommaMode == true && NumberPostComma >= 0) {
                    BigDecimal PostCommaValue = new BigDecimal("7");
                    BigDecimal Ten = new BigDecimal("10");
                    for (int i = 0; i <= NumberPostComma; i++) {
                        if (i != NumberPostComma) {
                            PostCommaValue = PostCommaValue.divide(Ten, NumberPostComma,
                                    BigDecimal.ROUND_HALF_UP);
                        }
                    }
                    PostCommaValue.setScale(NumberPostComma + 1, BigDecimal.ROUND_HALF_UP);
                    NumberPostComma += 1;
                    firstNumberBD = firstNumberBD.add(PostCommaValue);
                    String s1 = firstNumberBD.toString();
                    tv1.setText(s1);
                }
                if (CommaMode == false) {
                    tv1.append("7");
                    String s1 = tv1.getText().toString();
                    firstNumberBD = new BigDecimal(s1);
                }
                break;
            case(R.id.button8):
                if (CommaMode == true && NumberPostComma >= 0) {
                    BigDecimal PostCommaValue = new BigDecimal("8");
                    BigDecimal Ten = new BigDecimal("10");
                    for (int i = 0; i <= NumberPostComma; i++) {
                        if (i != NumberPostComma) {
                            PostCommaValue = PostCommaValue.divide(Ten, NumberPostComma,
                                    BigDecimal.ROUND_HALF_UP);
                        }
                    }
                    PostCommaValue.setScale(NumberPostComma + 1, BigDecimal.ROUND_HALF_UP);
                    NumberPostComma += 1;
                    firstNumberBD = firstNumberBD.add(PostCommaValue);
                    String s1 = firstNumberBD.toString();
                    tv1.setText(s1);
                }
                if (CommaMode == false) {
                    tv1.append("8");
                    String s1 = tv1.getText().toString();
                    firstNumberBD = new BigDecimal(s1);
                }
                break;
            case(R.id.button9):
                if (CommaMode == true && NumberPostComma >= 0) {
                    BigDecimal PostCommaValue = new BigDecimal("9");
                    BigDecimal Ten = new BigDecimal("10");
                    for (int i = 0; i <= NumberPostComma; i++) {
                        if (i != NumberPostComma) {
                            PostCommaValue = PostCommaValue.divide(Ten, NumberPostComma,
                                    BigDecimal.ROUND_HALF_UP);
                        }
                    }
                    PostCommaValue.setScale(NumberPostComma + 1, BigDecimal.ROUND_HALF_UP);
                    NumberPostComma += 1;
                    firstNumberBD = firstNumberBD.add(PostCommaValue);
                    String s1 = firstNumberBD.toString();
                    tv1.setText(s1);
                }
                if (CommaMode == false) {
                    tv1.append("9");
                    String s1 = tv1.getText().toString();
                    firstNumberBD = new BigDecimal(s1);
                }
                break;
            case(R.id.buttonZero):
                if (CommaMode == true && NumberPostComma >= 0) {
                    BigDecimal PostCommaValue = new BigDecimal("0");
                    BigDecimal Ten = new BigDecimal("10");
                    for (int i = 0; i <= NumberPostComma; i++) {
                        if (i != NumberPostComma) {
                            PostCommaValue = PostCommaValue.divide(Ten, NumberPostComma,
                                    BigDecimal.ROUND_HALF_UP);
                        }
                    }
                    PostCommaValue.setScale(NumberPostComma + 1, BigDecimal.ROUND_HALF_UP);
                    NumberPostComma += 1;
                    firstNumberBD = firstNumberBD.add(PostCommaValue);
                    String s1 = firstNumberBD.toString();
                    tv1.setText(s1);
                }
                if (CommaMode == false) {
                    tv1.append("0");
                    String s1 = tv1.getText().toString();
                    firstNumberBD = new BigDecimal(s1);
                }
                break;
        }
    }

    public void onClickArithmeticOperation(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        switch(v.getId()) {
            case(R.id.buttonDel):
                firstNumberBD = new BigDecimal("0");
                ResultBD = new BigDecimal("0");
                String s = "";
                tv1.setText(s);
                arithmeticOperation = '0';
                CommaMode = false;
                arithmeticOperationMode = false;
                NumberPostComma = 0;
                PostCommaValue = 0;
                Button buttonSu = (Button) findViewById(R.id.buttonSummarize);
                Button buttonSubs = (Button) findViewById(R.id.buttonSubtract);
                Button buttonMult = (Button) findViewById(R.id.buttonMultiply);
                Button buttonDivi = (Button) findViewById(R.id.buttonDivide);
                buttonSu.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonoperation));
                buttonSubs.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonoperation));
                buttonMult.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonoperation));
                buttonDivi.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonoperation));
                break;
            case(R.id.buttonSummarize):
                // Button "+" has pressed
                CommaMode = false;
                NumberPostComma = 0;
                PostCommaValue = 0;
                // The completion of previous operation
                if (arithmeticOperation != '+' && firstNumberBD.compareTo(BigDecimal.ZERO) != 0)
                    onPreviousOperationComplete(arithmeticOperation);
                // The previous operation is completed
                if (ResultBD.compareTo(new BigDecimal("0")) == 0 &&
                        firstNumberBD.compareTo(new BigDecimal("0")) != 0 &&
                        !arithmeticOperationMode) {
                    ResultBD = ResultBD.add(firstNumberBD);
                    firstNumberBD = new BigDecimal("0");
                    String s1 = ResultBD.toString();
                    arithmeticOperation = '+';
                    arithmeticOperationMode = true;

                    tv1.setText(s1);
                    Button buttonSummarize = (Button) findViewById(R.id.buttonSummarize);
                    buttonSummarize.setBackgroundColor(ContextCompat.getColor(this,
                            R.color.colorAmberDark));
                }
                if (ResultBD.compareTo(BigDecimal.ZERO) != 0 &&
                        firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                        arithmeticOperationMode) {
                    ResultBD = ResultBD.add(firstNumberBD);
                    firstNumberBD = new BigDecimal("0");
                    firstNumberBD = firstNumberBD.add(ResultBD);
                    ResultBD = new BigDecimal("0");
                    String s1 = firstNumberBD.toString();
                    tv1.setText(s1);
                    arithmeticOperation = '0';
                    arithmeticOperationMode = false;
                    Button buttonSummarize = (Button) findViewById(R.id.buttonSummarize);
                    buttonSummarize.setBackground(ContextCompat.getDrawable(this,
                            R.drawable.buttonoperation));
                }
                break;
            case(R.id.buttonSubtract):
                // Button "-" has pressed
                CommaMode = false;
                NumberPostComma = 0;
                PostCommaValue = 0;
                // The completion of previous operation
                if (arithmeticOperation != '-' && firstNumberBD.compareTo(BigDecimal.ZERO) != 0)
                    onPreviousOperationComplete(arithmeticOperation);
                // The previous operation is completed
                if (ResultBD.compareTo(BigDecimal.ZERO) == 0 &&
                        firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                        !arithmeticOperationMode) {
                    ResultBD = ResultBD.add(firstNumberBD);
                    firstNumberBD = new BigDecimal("0");
                    String s1 = ResultBD.toString();
                    arithmeticOperation = '-';
                    arithmeticOperationMode = true;
                    tv1.setText(s1);
                    Button buttonSubtract = (Button) findViewById(R.id.buttonSubtract);
                    buttonSubtract.setBackgroundColor(ContextCompat.getColor(this,
                            R.color.colorAmberDark));
                }
                if (ResultBD.compareTo(BigDecimal.ZERO) != 0 &&
                        firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                        arithmeticOperationMode) {
                    ResultBD = ResultBD.subtract(firstNumberBD);
                    firstNumberBD = new BigDecimal("0");
                    firstNumberBD = firstNumberBD.add(ResultBD);
                    ResultBD = new BigDecimal("0");
                    String s1 = firstNumberBD.toString();
                    tv1.setText(s1);
                    arithmeticOperation = '0';
                    arithmeticOperationMode = false;
                    Button buttonSubtract = (Button) findViewById(R.id.buttonSubtract);
                    buttonSubtract.setBackground(ContextCompat.getDrawable(this,
                            R.drawable.buttonoperation));
                }
                break;
            case(R.id.buttonMultiply):
                // Button "*" has pressed
                CommaMode = false;
                NumberPostComma = 0;
                PostCommaValue = 0;
                // The completion of previous operation
                if (arithmeticOperation != '*' && firstNumberBD.compareTo(BigDecimal.ZERO) != 0)
                    onPreviousOperationComplete(arithmeticOperation);
                // The previous operation is completed
                if (ResultBD.compareTo(BigDecimal.ZERO) == 0 &&
                        firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                        !arithmeticOperationMode) {
                    ResultBD = ResultBD.add(firstNumberBD);
                    firstNumberBD = new BigDecimal("0");
                    String s1 = ResultBD.toPlainString();
                    tv1.setText(s1);
                    arithmeticOperation = '*';
                    arithmeticOperationMode = true;
                    Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
                    buttonMultiply.setBackgroundColor(ContextCompat.getColor(this,
                            R.color.colorAmberDark));
                }
                if (ResultBD.compareTo(BigDecimal.ZERO) != 0 &&
                        firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                        arithmeticOperationMode) {
                    ResultBD = ResultBD.multiply(firstNumberBD);
                    int b = CountingMethod(ResultBD);
                    ResultBD = ResultBD.setScale(b - 1, BigDecimal.ROUND_HALF_DOWN);
                    firstNumberBD = new BigDecimal("0");
                    firstNumberBD = firstNumberBD.add(ResultBD);
                    ResultBD = new BigDecimal("0");
                    String s1 = firstNumberBD.toPlainString();
                    tv1.setText(s1);
                    arithmeticOperation = '0';
                    arithmeticOperationMode = false;
                    Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
                    buttonMultiply.setBackground(ContextCompat.getDrawable(this,
                            R.drawable.buttonoperation));
                }
                break;
            case(R.id.buttonDivide):
                // Button "/" has pressed
                CommaMode = false;
                NumberPostComma = 0;
                PostCommaValue = 0;
                // The completion of previous operation
                if (arithmeticOperation != '/' && firstNumberBD.compareTo(BigDecimal.ZERO) != 0)
                    onPreviousOperationComplete(arithmeticOperation);
                // The previous operation is completed
                if (ResultBD.compareTo(BigDecimal.ZERO) == 0 &&
                        firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                        !arithmeticOperationMode) {
                    String wswd = "";
                    wswd += arithmeticOperation;
                    Log.d("arithmeticOperation - ", wswd);
                    ResultBD = ResultBD.add(firstNumberBD);
                    firstNumberBD = new BigDecimal("0");
                    String s1 = ResultBD.toPlainString();
                    tv1.setText(s1);
                    arithmeticOperation = '/';
                    arithmeticOperationMode = true;
                    Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
                    buttonDivide.setBackgroundColor(ContextCompat.getColor(this,
                            R.color.colorAmberDark));
                }
                if (ResultBD.compareTo(BigDecimal.ZERO) != 0 &&
                        firstNumberBD.compareTo(BigDecimal.ZERO) != 0  &&
                        arithmeticOperationMode) {

                    ResultBD = ResultBD.divide(firstNumberBD, 500 ,BigDecimal.ROUND_HALF_DOWN);
                    firstNumberBD = new BigDecimal("0");
                    firstNumberBD = firstNumberBD.add(ResultBD);

                    int b = CountingMethod(firstNumberBD);
                    if (b > 500) b = 500;
                    if (b > 27) {
                        boolean CanWeSetScale = false;
                        int Scale = 0;
                        for (int i = 27; i <= 500; i += 5) {
                            BigDecimal scalefNDB = new BigDecimal("0");
                            scalefNDB = scalefNDB.add(firstNumberBD.setScale(i, BigDecimal.ROUND_HALF_DOWN));
                            if (scalefNDB.compareTo(BigDecimal.ZERO) == 0) {
                                CanWeSetScale = false;
                            } else {
                                CanWeSetScale = true;
                                Scale = i;
                                i = 501;
                            }
                        }
                        if (CanWeSetScale == false) {
                            firstNumberBD = firstNumberBD.setScale(b - 1, BigDecimal.ROUND_HALF_DOWN);
                        } else {
                            firstNumberBD = firstNumberBD.setScale(Scale, BigDecimal.ROUND_HALF_DOWN);
                        }
                    } else firstNumberBD = firstNumberBD.setScale(b - 1, BigDecimal.ROUND_HALF_DOWN);

                    String s1 = firstNumberBD.toPlainString();
                    tv1.setText(s1);

                    ResultBD = new BigDecimal("0");
                    arithmeticOperation = '0';
                    arithmeticOperationMode = false;
                    Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
                    buttonDivide.setBackground(ContextCompat.getDrawable(this,
                            R.drawable.buttonoperation));
                }
                break;
            default:
                break;
        }
    }

    public char onPreviousOperationComplete(char arithmeticOperation) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        arithmeticOperationMode = false;
        if (arithmeticOperation == '+') {
            ResultBD = ResultBD.add(firstNumberBD);
            firstNumberBD = new BigDecimal("0");
            firstNumberBD = firstNumberBD.add(ResultBD);
            ResultBD = new BigDecimal("0");
            String s1 = firstNumberBD.toString();
            tv1.setText(s1);
            Button buttonSum = (Button) findViewById(R.id.buttonSummarize);
            buttonSum.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonoperation));
        }
        if (arithmeticOperation == '-') {
            ResultBD = ResultBD.subtract(firstNumberBD);
            firstNumberBD = new BigDecimal("0");
            firstNumberBD = firstNumberBD.add(ResultBD);
            ResultBD = new BigDecimal("0");
            String s1 = firstNumberBD.toString();
            tv1.setText(s1);
            Button buttonSub = (Button) findViewById(R.id.buttonSubtract);
            buttonSub.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonoperation));
        }
        if (arithmeticOperation == '/') {
            if (firstNumberBD.compareTo(BigDecimal.ZERO) == 0) {
                Toast toast = Toast.makeText(getApplicationContext(), "Division by zero",
                        Toast.LENGTH_SHORT);
                toast.show();
                ResultBD = new BigDecimal("0");
            }
            if (firstNumberBD.compareTo(BigDecimal.ZERO) != 0)  {
                ResultBD = ResultBD.divide(firstNumberBD, 15, BigDecimal.ROUND_HALF_DOWN);
                int b = CountingMethod(ResultBD);
                ResultBD = ResultBD.setScale(b - 1, BigDecimal.ROUND_HALF_DOWN);
            }
            firstNumberBD = new BigDecimal("0");
            firstNumberBD = firstNumberBD.add(ResultBD);
            int b = CountingMethod(firstNumberBD);
            firstNumberBD = firstNumberBD.setScale(b , BigDecimal.ROUND_HALF_DOWN);
            ResultBD = new BigDecimal("0");
            String s1 = firstNumberBD.toString();
            tv1.setText(s1);
            Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
            buttonDivide.setBackground(ContextCompat.getDrawable(this, R.drawable.buttonoperation));
        }
        if (arithmeticOperation == '*') {
            ResultBD = ResultBD.multiply(firstNumberBD);
            firstNumberBD = new BigDecimal("0");
            firstNumberBD = firstNumberBD.add(ResultBD);
            int b = CountingMethod(firstNumberBD);
            firstNumberBD = firstNumberBD.setScale(b - 1, BigDecimal.ROUND_HALF_DOWN);
            ResultBD = new BigDecimal("0");
            String s1 = firstNumberBD.toString();
            tv1.setText(s1);
            Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
            buttonMultiply.setBackground(ContextCompat.getDrawable(this,
                    R.drawable.buttonoperation));
        }
        arithmeticOperation = '0';
        return arithmeticOperation;
    }

    public void OnClickPlusMinus(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        if (firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                ResultBD.compareTo(BigDecimal.ZERO) == 0) {
            firstNumberBD = firstNumberBD.negate();
            String s1 = firstNumberBD.toString();
            tv1.setText(s1);
        } if (firstNumberBD.compareTo(BigDecimal.ZERO) == 0 &&
                ResultBD.compareTo(BigDecimal.ZERO) != 0) {
            ResultBD = ResultBD.negate();
            String s1 = ResultBD.toString();
            tv1.setText(s1);
        }
    }

    public void onClickPercent(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        BigDecimal e500 = new BigDecimal("0.00000000000000000000000" +
                "00000000000000000000000000000000000000000000000000" +
                "00000000000000000000000000000000000000000000000000" +
                "00000000000000000000000000000000000000000000000000" +
                "00000000000000000000000000000000000000000000000000" +
                "00000000000000000000000000000000000000000000000000" +
                "00000000000000000000000000000000000000000000000000" +
                "00000000000000000000000000000000000000000000000000" +
                "00000000000000000000000000000000000000000000000000" +
                "00000000000000000000000000000000000000000000000000" +
                "000000000000000000000000001");
        int e500int = CountingMethod(e500);
        if (firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                ResultBD.compareTo(BigDecimal.ZERO) == 0) {
            int fNBD = CountingMethod(firstNumberBD);
            if (e500int > fNBD) {
                firstNumberBD = firstNumberBD.multiply(new BigDecimal("0.01"));
                String s1 = firstNumberBD.toString();
                tv1.setText(s1);
            }
        } if (firstNumberBD.compareTo(BigDecimal.ZERO) == 0 &&
                ResultBD.compareTo(BigDecimal.ZERO) != 0) {
            int RBD = CountingMethod(ResultBD);
            if (e500int > RBD) {
                ResultBD = ResultBD.multiply(new BigDecimal("0.01"));
                String s1 = ResultBD.toString();
                tv1.setText(s1);
            }
        }
    }

    public void onClickCButton(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        if (firstNumberBD.compareTo(BigDecimal.ZERO) != 0 &&
                ResultBD.compareTo(BigDecimal.ZERO) == 0) {
            String s2 = firstNumberBD.toPlainString();
            s2 = s2.substring(0, s2.length() - 1);
            if (s2.equals("-")) {
                firstNumberBD = new BigDecimal("0");
                CommaMode = false;
                tv1.setText("");
                return;
            }
            if (s2.equals("")) {
                firstNumberBD = new BigDecimal("0");
                CommaMode = false;
                tv1.setText("");
            }
            if (!s2.equals("")) {
                firstNumberBD = new BigDecimal(s2);
                if (firstNumberBD.compareTo(BigDecimal.ZERO) == 0) {
                    firstNumberBD = new BigDecimal("0");
                    CommaMode = false;
                    tv1.setText("");
                    NumberPostComma = 0;
                } if(firstNumberBD.compareTo(BigDecimal.ZERO) != 0) {
                    String s = firstNumberBD.toPlainString();
                    char[] arrayofs = s.toCharArray();
                    for (int i = arrayofs.length; i == arrayofs.length; i++) {
                        if (arrayofs[i - 1] == '.') {
                            NumberPostComma = 0;
                            CommaMode = false;
                            String s3 = firstNumberBD.toPlainString();
                            s3 = s3.substring(0, s3.length() - 1);
                            firstNumberBD = new BigDecimal(s3);
                        }
                    }
                    tv1.setText(firstNumberBD.toPlainString());
                }
                if (NumberPostComma >= 1)
                    NumberPostComma  -= 1;
                if (NumberPostComma == 0)
                    CommaMode = false;
            }
        }
    }

    public void onClickCommaButton(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        if (ResultBD.compareTo(BigDecimal.ZERO) == 0 || arithmeticOperation != '0') {
            String s1 = firstNumberBD.toString();
            tv1.setText(s1);
            boolean isCommaAlreadyExist = false;
            char[] s1array = s1.toCharArray();
            for (int i = 0; i < s1array.length; i++) {
                if (s1array[i] == '.')
                    isCommaAlreadyExist = true;
            }
            if (isCommaAlreadyExist == false) {
                CommaMode = true;
                NumberPostComma = 1;
                PostCommaValue = 0.d;
                tv1.append(".");
            }
        }
    }

    public int CountingMethod(BigDecimal Result) {
        String s = Result.toPlainString();
        char[] arrayofs = s.toCharArray();
        int lastvalueexcept0 = 0;
        int commaplace = 0;
        for (int i = 0; i < arrayofs.length; i++) {
            if (arrayofs[i] == '.')
                commaplace = i;
        }
        if (commaplace == 0) {
            commaplace = arrayofs.length + 1;
        }
        lastvalueexcept0 += commaplace + 1;
        for (int i = commaplace; i <= arrayofs.length; i++) {
            if (arrayofs[i-1] != '0')
                lastvalueexcept0 = i;
        }
        if (lastvalueexcept0 == commaplace)
            lastvalueexcept0 = s.length() - 1;
        if (lastvalueexcept0 - commaplace <= 2) {
            lastvalueexcept0 -= commaplace;
        }
        if (lastvalueexcept0 - commaplace > 2)
            lastvalueexcept0 -= commaplace;
        return lastvalueexcept0;
    }

    public void onClickEqualsButton(View v) {
        TextView tv1 = (TextView) findViewById(R.id.textV1);
        CommaMode = false;
        NumberPostComma = 0;
        PostCommaValue = 0;

        if (arithmeticOperationMode) {
            switch (arithmeticOperation) {
                case ('+'):
                    if (ResultBD.compareTo(BigDecimal.ZERO) != 0 ||
                            firstNumberBD.compareTo(BigDecimal.ZERO) != 0) {
                        ResultBD = ResultBD.add(firstNumberBD);
                        firstNumberBD = new BigDecimal("0");
                        firstNumberBD = firstNumberBD.add(ResultBD);
                        ResultBD = new BigDecimal("0");
                        arithmeticOperation = '0';
                        String s1 = firstNumberBD.toPlainString();
                        tv1.setText(s1);
                        Button buttonSummarize = (Button) findViewById(R.id.buttonSummarize);
                        buttonSummarize.setBackground(ContextCompat.getDrawable(this,
                                R.drawable.buttonoperation));
                    }
                    break;
                case ('-'):
                    if (ResultBD.compareTo(BigDecimal.ZERO) != 0 ||
                            firstNumberBD.compareTo(BigDecimal.ZERO) != 0) {
                        ResultBD = ResultBD.subtract(firstNumberBD);
                        firstNumberBD = new BigDecimal("0");
                        firstNumberBD = firstNumberBD.add(ResultBD);
                        ResultBD = new BigDecimal("0");
                        arithmeticOperation = '0';
                        String s1 = firstNumberBD.toPlainString();
                        tv1.setText(s1);
                        Button buttonSubtract = (Button) findViewById(R.id.buttonSubtract);
                        buttonSubtract.setBackground(ContextCompat.getDrawable(this,
                                R.drawable.buttonoperation));
                    }
                    break;
                case ('*'):
                    if (ResultBD.compareTo(BigDecimal.ZERO) != 0 ||
                            firstNumberBD.compareTo(BigDecimal.ZERO) != 0) {
                        ResultBD = ResultBD.multiply(firstNumberBD);
                        firstNumberBD = new BigDecimal("0");
                        firstNumberBD = firstNumberBD.add(ResultBD);
                        int b = CountingMethod(firstNumberBD);
                        firstNumberBD = firstNumberBD.setScale(b - 1, BigDecimal.ROUND_HALF_DOWN);
                        ResultBD = new BigDecimal("0");
                        arithmeticOperation = '0';
                        String s1 = firstNumberBD.toPlainString();
                        tv1.setText(s1);
                        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
                        buttonMultiply.setBackground(ContextCompat.getDrawable(this,
                                R.drawable.buttonoperation));
                    }
                    break;
                case ('/'):
                    if (ResultBD.compareTo(BigDecimal.ZERO) != 0 ||
                            firstNumberBD.compareTo(BigDecimal.ZERO) != 0) {
                        if (firstNumberBD.compareTo(BigDecimal.ZERO) == 0) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Division by zero", Toast.LENGTH_SHORT);
                            toast.show();
                            firstNumberBD = new BigDecimal("0");
                            ResultBD = new BigDecimal("0");
                            String s1 = "";
                            tv1.setText(s1);
                        }
                        if (firstNumberBD.compareTo(BigDecimal.ZERO) != 0) {
                            ResultBD = ResultBD.divide(firstNumberBD, 1000,
                                    BigDecimal.ROUND_HALF_DOWN);
                            firstNumberBD = new BigDecimal("0");
                            firstNumberBD = firstNumberBD.add(ResultBD);
                            ResultBD = new BigDecimal("0");

                            int b = CountingMethod(firstNumberBD);
                            if (b > 500) b = 500;
                            if (b > 20) {
                                boolean CanWeSetScale = false;
                                int Scale = 0;
                                for (int i = 20; i <= 500; i += 5) {
                                    BigDecimal scalefNDB = new BigDecimal("0");
                                    scalefNDB = scalefNDB.add(firstNumberBD.setScale(i, BigDecimal.ROUND_HALF_DOWN));
                                    if (scalefNDB.compareTo(BigDecimal.ZERO) == 0) {
                                        CanWeSetScale = false;
                                    } else {
                                        CanWeSetScale = true;
                                        Scale = i;
                                        i = 501;
                                    }
                                }
                                if (CanWeSetScale == false) {
                                    firstNumberBD = firstNumberBD.setScale(b - 1, BigDecimal.ROUND_HALF_DOWN);
                                } else {
                                    firstNumberBD = firstNumberBD.setScale(Scale, BigDecimal.ROUND_HALF_DOWN);
                                }
                            } else firstNumberBD = firstNumberBD.setScale(b - 1, BigDecimal.ROUND_HALF_DOWN);

                            String s1 = firstNumberBD.toPlainString();
                            tv1.setText(s1);
                        }
                        arithmeticOperation = '0';
                        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
                        buttonDivide.setBackground(ContextCompat.getDrawable(this,
                                R.drawable.buttonoperation));
                    }
                    break;
                default:
                    break;
            }
            arithmeticOperationMode = false;
        }
    }

}
